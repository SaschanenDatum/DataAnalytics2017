package data.analytics.smart.traffic.model;

public class CarLeavingEvent {

	private Direction leaveDirection;

	public CarLeavingEvent(Direction leaveDirection) {
		this.leaveDirection = leaveDirection;
	}

	public Direction getLeaveDirection() {
		return leaveDirection;
	}

	public void setLeaveDirection(Direction leaveDirection) {
		this.leaveDirection = leaveDirection;
	}
	
}
