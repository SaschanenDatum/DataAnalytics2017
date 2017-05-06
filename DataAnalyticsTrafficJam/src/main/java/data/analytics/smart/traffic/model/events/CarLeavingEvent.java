package data.analytics.smart.traffic.model.events;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.movement.Direction;

public class CarLeavingEvent {

	private Direction leaveDirection;
	private Car car;

	public CarLeavingEvent(Direction leaveDirection, Car car) {
		this.leaveDirection = leaveDirection;
		this.car = car;
	}

	public Direction getLeaveDirection() {
		return leaveDirection;
	}

	public Car getCar() {
		return car;
	}
	
	
	
}
