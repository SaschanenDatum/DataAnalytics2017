package data.analytics.smart.traffic.esper;

import com.espertech.esper.client.*;
import com.espertech.esper.client.annotation.Priority;
import com.espertech.esper.event.bean.BeanEventBean;
import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.events.CarIncomingEvent;
import data.analytics.smart.traffic.model.events.CarLeavingEvent;
import data.analytics.smart.traffic.model.events.CarWaitingEvent;
import data.analytics.smart.traffic.model.events.LightSwitchEvent;
import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Direction;
import data.analytics.smart.traffic.model.points.CrossRoad;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EsperService {
	private final String DIRECTION_PATH = "data.analytics.smart.traffic.model.movement.CardinalDirection.";
	private EPServiceProvider enigne;
	private CrossRoad road;

	public EsperService(CrossRoad road){
		this.road = road;
		this.enigne = EPServiceProviderManager.getProvider(road.getId());
		ConfigurationOperations configuration = enigne.getEPAdministrator().getConfiguration();
		configuration.addEventType(CarIncomingEvent.class);
		configuration.addEventType(LightSwitchEvent.class);
		configuration.addEventType(CarLeavingEvent.class);
		configuration.addEventType(CarWaitingEvent.class);
		this.addUpdateStatemens();
		this.addContext();
	}
	public EPStatement createStatement(String statement){
		return enigne.getEPAdministrator().createEPL(statement);
	}

	public void addListener(EPStatement statement, UpdateListener listener){
		statement.addListener(listener);
	}

	public void sendEvent(Object object){
		this.enigne.getEPRuntime().sendEvent(object);
	}
	
	
	private void addUpdateStatemens(){
		String updateCar ="select * from CarIncomingEvent";
		this.addListener(this.createStatement(updateCar), (newData, oldData)->{
			for (int i = 0; i < newData.length; i++) {
				CarIncomingEvent car =(CarIncomingEvent) newData[i].getUnderlying();
				road.incomingCar(car.getFromDirection(), car.getIncomingCar());
				//TODO find a better bugfix this is more like a big hammer
				if(road.getGreenSide().equals(car.getFromDirection())){
					car.getIncomingCar().getNextPoint();
				}
			}
		});


		// Event Nø1 - avoiding traffic jam by forcing light to switch
		//
		// This is the interesting listener for the automated update of the traffic light
		// The EPL Statement has to cover the following requirements:
		// [WEST<->EAST] Init Event for incoming N->S & S->N cars as detection events
		// [NORTH<->SOUTH] Init Event for incoming W->E & E->W cars as detection events
		// TODO: We need to use the recent unique consumption mode since we always only care for the latest light switch
		//
		// Example: When the light switched to WEST<->EAST, we only care about the incoming cars from the direction
		// 			where the cars could cause a traffic jam moving into the next crossroad. If that happens the
		//			traffic light should be forced to switch lights immediately to avoid the block of the other cr.
		//
		// There is just one more problem at the moment.
		// TODO: The Mustererkenner has to be reloaded for each inititaor event. --> LightSwitchEvent

		String fiveCarsWaitingPattern = "select ci[0] from pattern [ every [5] ci=CarWaitingEvent where timer:within(30 sec)]"; // ...].win:time(30)
		this.addListener(this.createStatement(fiveCarsWaitingPattern), (newData, oldData)->{
			for (int i = 0; i < newData.length; i++) {
				HashMap<String, Object> events = (HashMap) newData[i].getUnderlying();
				BeanEventBean beb = (BeanEventBean) events.get("ci[0]");
				CarWaitingEvent cwe = (CarWaitingEvent) beb.getUnderlying();
				System.out.println("## WARNING! 5 Cars waiting at crossroad" + cwe.getCrossRoad().getId() + " in Queue [" + cwe.getWaitingQueue().toString() + "]!\n## Traffic Jam Possible! Triggering LightSwitchEvent!");
				Direction actual = new Direction(cwe.getWaitingQueue());
				road.earlyLightSwitch(actual.getLeft());
			}
		});

		String updateLight = "select * from LightSwitchEvent";
		this.addListener(this.createStatement(updateLight), (newData, oldData)->{
			for (int i = 0; i < newData.length; i++) {
				System.out.println("LightSwitch Detected");
				LightSwitchEvent event = (LightSwitchEvent) newData[i].getUnderlying();
				this.road.switchLight(event.getTo());
			}
		});

		String leave = "select * from CarLeavingEvent";
		this.addListener(this.createStatement(leave), (newData, oldData)->{
			for (int i = 0; i < newData.length; i++) {
				CarLeavingEvent event =  (CarLeavingEvent) newData[i].getUnderlying();
				this.road.carLeaves(event.getLeaveDirection().getDirection(), event.getCar(), event.getIterator());
			}
		});
	}

	private void addContext(){
		String context = "create context CarByDirection partition by fromDirection from CarIncomingEvent";
		this.enigne.getEPAdministrator().createEPL(context);
		for (CardinalDirection direction : road.getKeyList()) {

			String query = "(fromDirection = "+DIRECTION_PATH+ direction+")";
			String additionalQuery = "->(CarIncomingEvent("+query+"))";
			String contextStatement = "context CarByDirection select * from pattern[every "
					+ "car = CarIncomingEvent"+query+additionalQuery+additionalQuery+additionalQuery+additionalQuery
					+"where timer:within(30 seconds)]";
			this.addListener(this.createStatement(contextStatement), (newData, oldData)->{
				for (int i = 0; i < newData.length; i++) {
					CarIncomingEvent event = (CarIncomingEvent) newData[i].get("car");
					CardinalDirection fromDirection = event.getFromDirection();
					road.earlyLightSwitch(fromDirection);
				}
			});
		}
	}
}
