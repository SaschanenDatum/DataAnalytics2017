package data.analytics.smart.traffic.model.points;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data.analytics.smart.traffic.esper.EsperService;
import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.TrafficLight;
import data.analytics.smart.traffic.model.TrafficLigthTimer;
import data.analytics.smart.traffic.model.events.CarIncomingEvent;
import data.analytics.smart.traffic.model.events.CarLeavingEvent;
import data.analytics.smart.traffic.model.events.CarWaitingEvent;
import data.analytics.smart.traffic.model.events.LightSwitchEvent;
import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Direction;

public class CrossRoad extends Point{

	private Map<CardinalDirection, List<Car>> waitinglist = new HashMap<>();

	private TrafficLight light = new TrafficLight(CardinalDirection.NORTH, 90);

	private Map<Direction, Point> connectingPoints = new HashMap<>();

	private double crossingTime;

	private EsperService service;
	
	private Thread thread;

	private TrafficLigthTimer timer1; 

	public CrossRoad(String id, double crossingTime){
		super(id);
		this.crossingTime = crossingTime;
		waitinglist.put(CardinalDirection.EAST, new ArrayList<>());
		waitinglist.put(CardinalDirection.NORTH, new ArrayList<>());
		waitinglist.put(CardinalDirection.SOUTH, new ArrayList<>());
		waitinglist.put(CardinalDirection.WEST, new ArrayList<>());
		service = new EsperService(this);
		timer1 = new TrafficLigthTimer(this.light.getMinGreenTime(), this);
		thread = new Thread(timer1);
		thread.start();
		//service.sendEvent(new LightSwitchEvent(CardinalDirection.NORTH, CardinalDirection.WEST));
	}
	//TODO add to Constructor or factory
	public Map<Direction, Point> getConnectingPoints() {
		return connectingPoints;
	}

	public void setConnectingPoints(Map<Direction, Point> connectingPoints) {
		this.connectingPoints = connectingPoints;
	}

	public void announceCar(CardinalDirection fromDirection, Car car){
		System.out.println(String.format("CR%s: Car %s arrives at CR %s", this.id,car.getNumber(), this.id));
		CarIncomingEvent event = new CarIncomingEvent(fromDirection, car);
		service.sendEvent(event);
	}

	public void announceLeaving(Direction leaveDirection, Car car, Iterator<Car> iterator){
		CarLeavingEvent event = new CarLeavingEvent(leaveDirection, car, iterator);
		service.sendEvent(event);
	}

	public Set<CardinalDirection> getKeyList(){
		return waitinglist.keySet();
	}

	public synchronized void incomingCar(CardinalDirection from, Car car){
		
		System.out.println(String.format("CR%s: Car %s arrived", this.id, car.getNumber()));
		if(light.isGreen(from)){
			System.out.println("CR" + this.id + ": Its already green");
			//TODO fix bug where this event aktivates the CarIncoming Listner again
			//			this.announceLeaving(new Direction(from), car);
		}else{
			List<Car> carList = waitinglist.get(from);
			carList.add(car);
			waitinglist.put(from, carList);
			System.out.println("CR" + this.id + ": Car " + car.getNumber() + " has to wait in line");
			//FIXME BUg who duplicates the car incoming event
						service.sendEvent(new CarWaitingEvent(this, from));
		}
	}

	public CardinalDirection getGreenSide(){
		return this.light.getGreenSide();
	}


	public synchronized void switchLight(CardinalDirection to){
		System.out.println("CR" + this.id + ": switch light from " + light.getGreenSide() + " to " + to );
		this.light.setGreenSide(to);
		List<Car> leavingCars = waitinglist.get(to);
		Iterator<Car> iterator = leavingCars.iterator();
		while (iterator.hasNext()) {
			this.announceLeaving(new Direction(to), iterator.next(), iterator);
		}
	}
	
	public void earlyLightSwitch(CardinalDirection to){
		this.thread.interrupt();
		this.switchLight(to);
		this.thread = new Thread(timer1);
		thread.start();
	}

	public synchronized void switchFromTimer(CardinalDirection to){
		this.service.sendEvent(new LightSwitchEvent(this.getGreenSide(), to));
	}

	public void addCrossingPoint(Direction direction ,Point point){
		this.connectingPoints.put(direction, point);
	}

	public synchronized void carLeaves(CardinalDirection from, Car car, Iterator<Car> iterator){

		List<Car> carList = waitinglist.get(from);
		iterator.remove();
			try {
				Thread.currentThread().sleep((long) (this.crossingTime*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			waitinglist.put(from, carList);
			System.out.println("CR" + this.id + ": Car " + car.getNumber() + " leaves from " + from);
			System.out.println("CR" + this.id + ": " + carList.size()+ " Cars waiting to leave");
			car.getNextPoint();
	}

	public List<Car> getWaitingCars(CardinalDirection from){
		return this.waitinglist.get(from);
	}
}
