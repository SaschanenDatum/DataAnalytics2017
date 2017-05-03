package data.analytics.smart.traffic.model;

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
