package data.analytics.smart.traffic.model;

import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Direction;

public class TrafficLight {

	private CardinalDirection greenSide;
	
	private int minGreenTime;

	public TrafficLight(CardinalDirection greenSide, int minGreenTime) {
		this.greenSide = greenSide;
		this.minGreenTime = minGreenTime;
	}

	public CardinalDirection getGreenSide() {
		return greenSide;
	}

	public void setGreenSide(CardinalDirection greenSide) {
		this.greenSide = greenSide;
	}

	public int getMinGreenTime() {
		return minGreenTime;
	}

	public void setMinGreenTime(int minGreenTime) {
		this.minGreenTime = minGreenTime;
	}

	public boolean isGreen(CardinalDirection side){
		if(checkNortSout(side) && checkNortSout(greenSide)){
			return true;
		}
		if(checkEastWest(side) && checkEastWest(greenSide)){
			return true;
		}
		return false;
	}

	private boolean checkEastWest(CardinalDirection side) {
		return side.equals(CardinalDirection.EAST)|| side.equals(CardinalDirection.WEST);
	}

	private boolean checkNortSout(CardinalDirection side) {
		return side.equals(CardinalDirection.NORTH) || side.equals(CardinalDirection.SOUTH);
	}
	
}
