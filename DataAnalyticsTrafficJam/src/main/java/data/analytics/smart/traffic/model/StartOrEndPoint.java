package data.analytics.smart.traffic.model;

public class StartOrEndPoint extends Point{

	private Direction direction;
	public StartOrEndPoint(String id, Direction direction) {
		super(id);
		this.direction = direction;
	}
	
	public Direction geDirection(){
		return this.direction;
	}
	

}
