package data.analytics.smart.traffic.model.movement;

public class Direction {
	
	private	CardinalDirection direction;
	private CardinalDirection left;
	private CardinalDirection right;
	private CardinalDirection opposite;
	
	public Direction(CardinalDirection side, CardinalDirection left, CardinalDirection right,
			CardinalDirection opposite) {
		this.direction = side;
		this.left = left;
		this.right = right;
		this.opposite = opposite;
	}
	public CardinalDirection getDirection() {
		return direction;
	}
	public CardinalDirection getLeft() {
		return left;
	}
	public CardinalDirection getRight() {
		return right;
	}
	public CardinalDirection getOpposite() {
		return opposite;
	}
}
