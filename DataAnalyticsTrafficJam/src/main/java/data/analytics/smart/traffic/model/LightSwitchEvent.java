package data.analytics.smart.traffic.model;

public class LightSwitchEvent {

	private Direction from;
	
	private Direction to;
	
	public LightSwitchEvent(Direction from, Direction to) {
		this.from = from;
		this.to = to;
	}

	public Direction getFrom() {
		return from;
	}

	public void setFrom(Direction from) {
		this.from = from;
	}

	public Direction getTo() {
		return to;
	}

	public void setTo(Direction to) {
		this.to = to;
	}
	
}
