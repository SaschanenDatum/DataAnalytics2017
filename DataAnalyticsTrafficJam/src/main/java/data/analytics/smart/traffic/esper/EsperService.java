package data.analytics.smart.traffic.esper;

import java.util.Iterator;
import java.util.List;

import com.espertech.esper.client.ConfigurationOperations;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.events.CarIncomingEvent;
import data.analytics.smart.traffic.model.events.LightSwitchEvent;
import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.points.CrossRoad;

public class EsperService {
	private final String DIRECTION_PATH = "data.analytics.smart.traffic.model.Direction.";
	private EPServiceProvider enigne = EPServiceProviderManager.getDefaultProvider();
	private CrossRoad road; 

	public EsperService(CrossRoad road){
		this.road = road;
		ConfigurationOperations configuration = enigne.getEPAdministrator().getConfiguration();
		configuration.addEventType(CarIncomingEvent.class);
		configuration.addEventType(LightSwitchEvent.class);
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
			}
		});
		String updateLight = "select * from LightSwitchEvent";
		this.addListener(this.createStatement(updateLight), (newData, oldData)->{
			for (int i = 0; i < newData.length; i++) {
				LightSwitchEvent event = (LightSwitchEvent) newData[i].getUnderlying();
				this.road.switchLight(event.getTo());
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
					sendEvent(new LightSwitchEvent(road.getGreenSide(), fromDirection));
					List<Car> cars =road.getWaitingCars(fromDirection);
					
					Iterator<Car> it = cars.iterator();
					while (it.hasNext()) {
						Car currentCar = (Car) it.next();
						road.carLeaves(fromDirection, currentCar);
					}
				}
			});
		}
	}
}
