package data.analytics.smart.traffic.model.points;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data.analytics.smart.traffic.esper.EsperService;
import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.TrafficLight;
import data.analytics.smart.traffic.model.events.CarIncomingEvent;
import data.analytics.smart.traffic.model.events.CarLeavingEvent;
import data.analytics.smart.traffic.model.events.LightSwitchEvent;
import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Direction;

public class CrossRoad extends Point{

	private Map<CardinalDirection, List<Car>> waitinglist = new HashMap<>();

	private TrafficLight light = new TrafficLight(CardinalDirection.NORTH, 0);

	private Map<Direction, Point> connectingPoints = new HashMap<>();

	private double crossingTime;
	
	private EsperService service;

	public CrossRoad(String id, double crossingTime){
		super(id);
		this.crossingTime = crossingTime;
		waitinglist.put(CardinalDirection.EAST, new ArrayList<>());
		waitinglist.put(CardinalDirection.NORTH, new ArrayList<>());
		waitinglist.put(CardinalDirection.SOUTH, new ArrayList<>());
		waitinglist.put(CardinalDirection.WEST, new ArrayList<>());
		service = new EsperService(this);
		service.sendEvent(new LightSwitchEvent(CardinalDirection.NORTH, CardinalDirection.WEST));
	}
	//TODO add to Constructor or factory
	public Map<Direction, Point> getConnectingPoints() {
		return connectingPoints;
	}

	public void setConnectingPoints(Map<Direction, Point> connectingPoints) {
		this.connectingPoints = connectingPoints;
	}
	
	public void announceCar(CardinalDirection fromDirection, Car car){
		CarIncomingEvent event = new CarIncomingEvent(fromDirection, car);
		service.sendEvent(event);
	}
	
	public void announceLeaving(Direction leaveDirection, Car car){
		CarLeavingEvent event = new CarLeavingEvent(leaveDirection, car);
		service.sendEvent(event);
	}

	public Set<CardinalDirection> getKeyList(){
		return waitinglist.keySet();
	}

	public void incomingCar(CardinalDirection from, Car car){
	

		List<Car> carList = waitinglist.get(from);
		carList.add(car);
		waitinglist.put(from, carList);
		System.out.println("Car from " + from + "has to wait in line");
		if(light.isGreen(from)){
			System.out.println("Its already green");
			//TODO fix bug where this event aktivates the CarIncoming Listner again
//			this.service.sendEvent(new CarLeavingEvent(new Direction(from), car));
		}
	}

	public CardinalDirection getGreenSide(){
		return this.light.getGreenSide();
	}

	public void switchLight(CardinalDirection to){
		System.out.println("Switch light from " + light.getGreenSide() + "  to " + to );
		this.light.setGreenSide(to);
	}

	public void addCrossingPoint(Direction direction ,Point point){
		this.connectingPoints.put(direction, point);
	}

	public void carLeaves(CardinalDirection from, Car car){
		
			List<Car> carList = waitinglist.get(from);
			boolean isRemoved = carList.remove(car);
			if(isRemoved){
				try {
					Thread.currentThread().sleep((long) (this.crossingTime*1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				waitinglist.put(from, carList);
				System.out.println("Car leaves from " + from);
				System.out.println(carList.size()+ " Cars waiting to leave");
				if (this.connectingPoints.containsValue(car.getRoute().getNextPoint(this))) {
					car.getNextPoint();
				}
			}
	}

	public List<Car> getWaitingCars(CardinalDirection from){
		return this.waitinglist.get(from);
	}
}
