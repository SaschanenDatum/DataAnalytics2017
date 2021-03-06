package data.analytics.smart.traffic.esper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.espertech.esper.client.ConfigurationOperations;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.events.CarEntersSystemEvent;
import data.analytics.smart.traffic.model.events.PublishSolveEvent;
import data.analytics.smart.traffic.model.events.PublishTrafficJamEvent;
import data.analytics.smart.traffic.model.movement.Route;
import data.analytics.smart.traffic.model.points.Point;

public class CarEsper {
	private EPServiceProvider control = EPServiceProviderManager.getProvider("CarControl");
	private Map<Point, Point> blockedParts = new HashMap<>();
	private Set<Car> carsInSystem = new HashSet<>();
	
	protected CarEsper(){
		ConfigurationOperations configuration = control.getEPAdministrator().getConfiguration();
		configuration.addEventType(CarEntersSystemEvent.class);
		configuration.addEventType(PublishTrafficJamEvent.class);
		configuration.addEventType(PublishSolveEvent.class);
		this.createUpdateListener();
		this.createTrafficJamPattern();
	}

	public EPStatement createStatement(String statement){
		return control.getEPAdministrator().createEPL(statement);
	}

	public void addListener(EPStatement statement, UpdateListener listener){
		statement.addListener(listener);
	}

	public void sendEvent(Object object){
		this.control.getEPRuntime().sendEvent(object);
	}
	
	private void addRoute(Route route, Car car){
		for (Point point : blockedParts.keySet()) {
			if(checkIfBlocked(route, point)){
				System.err.println(point.getId() + " is blocked! " + car + "will search for a different way.");
				car.setRoute(route.calculateAlternativeRoute(point));
			};
		}
		car.getNextPoint();
	}
	
	private void redirectCars(){
		for (Point point : this.blockedParts.keySet()) {
			Iterator<Car> iterator = carsInSystem.iterator();
			while(iterator.hasNext()){
				Car car = iterator.next();
				
				if(car.isFinish()){
					iterator.remove();
					continue;
				}
				if(this.checkIfBlocked(car.getRoute(), point)){
					car.setRoute(car.getRoute().calculateAlternativeRoute(point));
				}
			}
		}
	}

	private boolean checkIfBlocked(Route route, Point point) {
		if(route.getCrossRoads().contains(point)){
			if(blockedParts.get(point).equals(route.getNextPoint(point))){
				return true;
			}
		}
		return false;
	}
	
	private void createTrafficJamPattern(){
		//gets the first and the second published Jam (pOne and pTwo) if the Points are the same.
		String trafficJamPattern = "select * from pattern [every pOne=PublishTrafficJamEvent ->pTwo=PublishTrafficJamEvent(fromPoint = pOne.fromPoint and toPoint = pOne.toPoint) where timer:within(1 min)].win:time(90 seconds)";
		this.addListener(this.createStatement(trafficJamPattern), (newData, olData)->{
			PublishTrafficJamEvent firstNotice =  (PublishTrafficJamEvent) newData[0].get("pOne");
			PublishTrafficJamEvent secNotice = (PublishTrafficJamEvent) newData[0].get("pTwo");
			//send Report
			String message = String.format("Traffic Jam Warning between: %s and %s reported by %s and confirmed from %s", firstNotice.getToPoint().getId(), firstNotice.getFromPoint().getId(), firstNotice.getCar(), secNotice.getCar());
			System.err.println(message);
			//add to map
			this.blockedParts.put(firstNotice.getFromPoint(), firstNotice.getToPoint());
			this.redirectCars();
		});
		//unblock Route
		String unJamPattern = "Select * from pattern [every jam=PublishTrafficJamEvent -> unJam = PublishSolveEvent(fromPoint = jam.fromPoint and toPoint = jam.toPoint and car.number = jam.car.number)]";
		this.addListener(this.createStatement(unJamPattern), (newData, oldData)->{
			PublishSolveEvent event = (PublishSolveEvent) newData[0].get("unJam");
			System.out.println(event.getFromPoint().getId() + "-> " + event.getToPoint().getId()+ "unblocked");
			this.blockedParts.remove(event.getFromPoint());
		});
	}
	
	private void createUpdateListener(){
		String statement = "Select * from CarEntersSystemEvent";
		this.addListener(this.createStatement(statement), (newData, oldData)->{
			if(newData[0] != null){
				CarEntersSystemEvent event = (CarEntersSystemEvent) newData[0].getUnderlying();
				System.out.println("New Car in System: " + event.getCar());
				this.addRoute(event.getRoute(), event.getCar());
			}
		});
	}

}
