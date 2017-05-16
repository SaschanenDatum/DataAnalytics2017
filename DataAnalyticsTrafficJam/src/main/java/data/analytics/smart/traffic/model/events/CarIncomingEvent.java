package data.analytics.smart.traffic.model.events;

import com.espertech.esper.client.annotation.Priority;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.movement.CardinalDirection;

public class CarIncomingEvent {

	private CardinalDirection fromDirection;
	private Car incomingCar;
	
	public CarIncomingEvent(CardinalDirection fromDirection, Car incomingCar) {
		this.fromDirection = fromDirection;
		this.incomingCar = incomingCar;
	}

	public CardinalDirection getFromDirection() {
		return fromDirection;
	}

	public Car getIncomingCar() {
		return incomingCar;
	}
	
	
}
