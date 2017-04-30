package data.analytics.smart.traffic.model;

public class CarIncomingEvent {

	private Direction fromDirection;
	
	public CarIncomingEvent(Direction fromDirection) {
		this.fromDirection = fromDirection;
	}

	public Direction getFromDirection() {
		return fromDirection;
	}

	public void setFromDirection(Direction fromDirection) {
		this.fromDirection = fromDirection;
	}
	
	
}
