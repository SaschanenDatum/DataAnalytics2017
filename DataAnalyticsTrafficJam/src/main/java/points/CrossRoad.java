package points;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import data.analytics.smart.traffic.model.TrafficLight;
import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Direction;

public class CrossRoad extends Point{
	
	private Map<CardinalDirection, Integer> waitinglist = new HashMap<>();
	
	private TrafficLight light = new TrafficLight(CardinalDirection.NORTH, 0);
	
	private Map<Direction, Point> connectingPoints = new HashMap<>();
	
	private double crossingTime;
	
	public CrossRoad(String id, double crossingTime){
		super(id);
		this.crossingTime = crossingTime;
		waitinglist.put(CardinalDirection.EAST, 0);
		waitinglist.put(CardinalDirection.NORTH, 0);
		waitinglist.put(CardinalDirection.SOUTH, 0);
		waitinglist.put(CardinalDirection.WEST, 0);
	}
	//TODO add to Constructor or factory
	public Map<Direction, Point> getConnectingPoints() {
		return connectingPoints;
	}

	public void setConnectingPoints(Map<Direction, Point> connectingPoints) {
		this.connectingPoints = connectingPoints;
	}



	public Set<CardinalDirection> getKeyList(){
		return waitinglist.keySet();
	}
	
	public void incomingCar(CardinalDirection from){
		if(light.isGreen(from)){
			System.out.println("Car from " +from + "passes Crossroad");
		}else{
			Integer integer = waitinglist.get(from);
			waitinglist.put(from, integer.intValue()+1);
			System.out.println("Car from " + from + "has to wait in line");
		}
	}
	
	public CardinalDirection getGreenSide(){
		return this.light.getGreenSide();
	}
	
	public void switchLight(CardinalDirection to){
		System.out.println("Switch light from " + light.getGreenSide() + "to" + to );
		this.light.setGreenSide(to);
	}

	public void addCrossingPoint(Direction direction ,Point point){
		this.connectingPoints.put(direction, point);
	}
	
	public void carLeaves(CardinalDirection from){
		int waiting = waitinglist.get(from).intValue();
		if(waiting > 0){
			System.out.println("Car leaves from " + from);
			waiting--;
			System.out.println(waiting+ " Cars waiting to leave");
			waitinglist.put(from, waiting);
		}
		
	}
	
	public int getWaitingCars(CardinalDirection from){
		return this.waitinglist.get(from).intValue();
	}
}
