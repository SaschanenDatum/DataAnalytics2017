package data.analytics.smart.traffic;

import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Direction;

public class TrafficControl {

	public static void main(String[] args) {

		CardinalDirection east = CardinalDirection.EAST;
		CardinalDirection north = CardinalDirection.NORTH;
		CardinalDirection west = CardinalDirection.WEST;
		CardinalDirection south = CardinalDirection.SOUTH;
		Direction northDirection = new Direction(north, east, west, south);
		Direction southDirection = new Direction(south, west, east, north);
		Direction eastDirection = new Direction(east, south, north, west);
		Direction westDirection = new Direction(west, north, south, east);
		//TODO Create Routes
	}

}
