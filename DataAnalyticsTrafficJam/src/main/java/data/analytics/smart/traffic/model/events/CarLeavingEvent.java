package data.analytics.smart.traffic.model.events;

import java.util.Iterator;

import com.espertech.esper.client.annotation.Priority;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.movement.Direction;

public class CarLeavingEvent {

	private Direction leaveDirection;
	private Car car;
	private Iterator<Car> iterator;

	public CarLeavingEvent(Direction leaveDirection, Car car, Iterator<Car> iterator) {
		this.leaveDirection = leaveDirection;
		this.car = car;
		this.iterator = iterator;
	}

	public Direction getLeaveDirection() {
		return leaveDirection;
	}

	public Car getCar() {
		return car;
	}
	public Iterator<Car> getIterator(){
		return iterator;
	}
	
	
	
}
