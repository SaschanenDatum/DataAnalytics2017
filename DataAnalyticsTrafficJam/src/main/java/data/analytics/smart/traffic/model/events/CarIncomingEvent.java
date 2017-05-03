package data.analytics.smart.traffic.model.events;

import data.analytics.smart.traffic.model.movement.CardinalDirection;

public class CarIncomingEvent {

	private CardinalDirection fromDirection;
	
	public CarIncomingEvent(CardinalDirection fromDirection) {
		this.fromDirection = fromDirection;
	}

	public CardinalDirection getFromDirection() {
		return fromDirection;
	}

	public void setFromDirection(CardinalDirection fromDirection) {
		this.fromDirection = fromDirection;
	}
	
	
}
