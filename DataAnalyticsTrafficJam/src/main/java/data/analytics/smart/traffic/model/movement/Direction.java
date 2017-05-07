package data.analytics.smart.traffic.model.movement;

public class Direction {
	
	private	CardinalDirection direction;
	private CardinalDirection left;
	private CardinalDirection right;
	private CardinalDirection opposite;
	
	public Direction(CardinalDirection direction) {
		this.direction = direction;

		switch (this.direction) {
			case NORTH:
				this.left = CardinalDirection.WEST;
				this.right = CardinalDirection.EAST;
				this.opposite = CardinalDirection.SOUTH;
				break;
			case EAST:
				this.left = CardinalDirection.NORTH;
				this.right = CardinalDirection.SOUTH;
				this.opposite = CardinalDirection.WEST;
				break;
			case SOUTH:
				this.left = CardinalDirection.EAST;
				this.right = CardinalDirection.WEST;
				this.opposite = CardinalDirection.NORTH;
				break;
			case WEST:
				this.left = CardinalDirection.SOUTH;
				this.right = CardinalDirection.NORTH;
				this.opposite = CardinalDirection.EAST;
				break;
		}
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
