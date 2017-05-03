package data.analytics.smart.traffic.model;

public class StartOrEndPoint extends Point{

	private CardinalDirection direction;
	public StartOrEndPoint(String id, CardinalDirection direction) {
		super(id);
		this.direction = direction;
	}
	
	public CardinalDirection geDirection(){
		return this.direction;
	}
	

}
