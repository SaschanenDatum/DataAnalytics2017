package data.analytics.smart.traffic.model.events;

import data.analytics.smart.traffic.model.movement.CardinalDirection;

public class LightSwitchEvent {

	private CardinalDirection from;
	
	private CardinalDirection to;
	
	public LightSwitchEvent(CardinalDirection from, CardinalDirection to) {
		this.from = from;
		this.to = to;
	}

	public CardinalDirection getFrom() {
		return from;
	}

	public void setFrom(CardinalDirection from) {
		this.from = from;
	}

	public CardinalDirection getTo() {
		return to;
	}

	public void setTo(CardinalDirection to) {
		this.to = to;
	}
	
}
