package points;

import data.analytics.smart.traffic.model.movement.Direction;

public class StartOrEndPoint extends Point{

	private Direction direction;
	public StartOrEndPoint(String id, Direction direction) {
		super(id);
		this.direction = direction;
	}
	
	public Direction geDirection(){
		return this.direction;
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof StartOrEndPoint){
			StartOrEndPoint p = (StartOrEndPoint) obj;
			if(p.getId().equals(this.getId())){
				return true;
			}
		}
		return false;
	}
}
