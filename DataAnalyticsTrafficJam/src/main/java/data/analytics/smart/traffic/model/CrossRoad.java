package data.analytics.smart.traffic.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CrossRoad {
	
	private Map<Direction, Integer> waitinglist = new HashMap<>();
	
	private TrafficLight light = new TrafficLight(Direction.NORTh, 0);
	
	public CrossRoad(){
		waitinglist.put(Direction.EAST, 0);
		waitinglist.put(Direction.NORTh, 0);
		waitinglist.put(Direction.SOUTH, 0);
		waitinglist.put(Direction.WEST, 0);
	}
	
	public Set<Direction> getKeyList(){
		return waitinglist.keySet();
	}
	
	public void incomingCar(Direction from){
		if(light.isGreen(from)){
			System.out.println("Car from " +from + "passes Crossroad");
		}else{
			Integer integer = waitinglist.get(from);
			waitinglist.put(from, integer.intValue()+1);
			System.out.println("Car from " + from + "has to wait in line");
		}
	}
	
	public Direction getGreenSide(){
		return this.light.getGreenSide();
	}
	
	public void switchLight(Direction to){
		System.out.println("Switch light from " + light.getGreenSide() + "to" + to );
		this.light.setGreenSide(to);
	}
	
	public void carLeaves(Direction from){
		int waiting = waitinglist.get(from).intValue();
		if(waiting > 0){
			System.out.println("Car leaves from " + from);
			waiting--;
			System.out.println(waiting+ " Cars waiting to leave");
			waitinglist.put(from, waiting);
		}
		
	}
	
	public int getWaitingCars(Direction from){
		return this.waitinglist.get(from).intValue();
	}
}
