package data.analytics.smart.traffic.model;

public class TrafficLight {

	private Direction greenSide;
	
	private int minGreenTime;

	public TrafficLight(Direction greenSide, int minGreenTime) {
		this.greenSide = greenSide;
		this.minGreenTime = minGreenTime;
	}

	public Direction getGreenSide() {
		return greenSide;
	}

	public void setGreenSide(Direction greenSide) {
		this.greenSide = greenSide;
	}

	public int getMinGreenTime() {
		return minGreenTime;
	}

	public void setMinGreenTime(int minGreenTime) {
		this.minGreenTime = minGreenTime;
	}
	
	public boolean isGreen(Direction side){
		if(checkNortSout(side) && checkNortSout(greenSide)){
			return true;
		}
		if(checkEastWest(side) && checkEastWest(greenSide)){
			return true;
		}
		return false;
	}

	private boolean checkEastWest(Direction side) {
		return side.equals(Direction.EAST)|| side.equals(Direction.WEST);
	}

	private boolean checkNortSout(Direction side) {
		return side.equals(Direction.NORTh) || side.equals(Direction.SOUTH);
	}
	
}
