package data.analytics.smart.traffic.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CrossRoad extends Point{
	
	private Map<CardinalDirection, Integer> waitinglist = new HashMap<>();
	
	private TrafficLight light = new TrafficLight(CardinalDirection.NORTH, 0);
	
	public CrossRoad(String id){
		super(id);
		waitinglist.put(CardinalDirection.EAST, 0);
		waitinglist.put(CardinalDirection.NORTH, 0);
		waitinglist.put(CardinalDirection.SOUTH, 0);
		waitinglist.put(CardinalDirection.WEST, 0);
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
