package data.analytics.smart.traffic;

import java.util.ArrayList;
import java.util.List;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Direction;
import data.analytics.smart.traffic.model.movement.Route;
import data.analytics.smart.traffic.model.points.CrossRoad;
import data.analytics.smart.traffic.model.points.StartOrEndPoint;

public class TrafficControl {

	public static void main(String[] args) {

		CardinalDirection east = CardinalDirection.EAST;
		CardinalDirection north = CardinalDirection.NORTH;
		CardinalDirection west = CardinalDirection.WEST;
		CardinalDirection south = CardinalDirection.SOUTH;
		Direction northDirection = new Direction(north);
		Direction southDirection = new Direction(south);
		Direction eastDirection = new Direction(east);
		Direction westDirection = new Direction(west);
		//TODO Create Routes
		StartOrEndPoint a = new StartOrEndPoint("A", eastDirection);
		StartOrEndPoint b = new StartOrEndPoint("B", southDirection);
		StartOrEndPoint c = new StartOrEndPoint("c", westDirection);
		StartOrEndPoint d = new StartOrEndPoint("d", northDirection);
		
		CrossRoad crossRoad = new CrossRoad("1", 10.4);
		List<CrossRoad> crossRoads = new ArrayList<>();
		crossRoads.add(crossRoad);
		Route ab = new Route(a, b, crossRoads);
		Car car = new Car(a, b, ab);
		car.getNextPoint();
	}
	
}
