package data.analytics.smart.traffic;

import java.util.LinkedHashSet;
import java.util.Set;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.TrafficLigthTimer;
import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Direction;
import data.analytics.smart.traffic.model.movement.Route;
import data.analytics.smart.traffic.model.movement.RouteFactory;
import data.analytics.smart.traffic.model.points.CrossRoad;
import data.analytics.smart.traffic.model.points.StartOrEndPoint;

public class TrafficControl {

	public static void main(String[] args) {

		//Creation of directions
		CardinalDirection east = CardinalDirection.EAST;
		CardinalDirection north = CardinalDirection.NORTH;
		CardinalDirection west = CardinalDirection.WEST;
		CardinalDirection south = CardinalDirection.SOUTH;
		Direction northDirection = new Direction(north);
		Direction southDirection = new Direction(south);
		Direction eastDirection = new Direction(east);
		Direction westDirection = new Direction(west);
		Set<Direction> directions =  new LinkedHashSet<>();
		//TODO add direction to set
		//creation of points
		StartOrEndPoint a = new StartOrEndPoint("A", eastDirection);
		StartOrEndPoint b = new StartOrEndPoint("B", northDirection);
		StartOrEndPoint c = new StartOrEndPoint("C", northDirection);
		StartOrEndPoint d = new StartOrEndPoint("D", westDirection);
		StartOrEndPoint e = new StartOrEndPoint("E", westDirection);
		StartOrEndPoint f = new StartOrEndPoint("F", southDirection);
		StartOrEndPoint g = new StartOrEndPoint("G", southDirection);
		StartOrEndPoint h = new StartOrEndPoint("h", eastDirection);
		
		CrossRoad crossRoad1 = new CrossRoad("1", 0.4);
		CrossRoad crossRoad2 = new CrossRoad("4", 0.4);
		crossRoad2.earlyLightSwitch(CardinalDirection.WEST);
		crossRoad1.addCrossingPoint(northDirection, crossRoad2);
		crossRoad2.addCrossingPoint(southDirection, crossRoad1);
		crossRoad2.addCrossingPoint(northDirection, g);
//		CrossRoad crossRoad3 = new CrossRoad("3", 0.4);
//		CrossRoad crossRoad4 = new CrossRoad("4", 0.4);

		Route ag = RouteFactory.createRoute(a, g, crossRoad1, crossRoad2);
		Car car0 = new Car(a, g, ag, 0);
		car0.getNextPoint();

		Car car1 = new Car(a, b, ag, 1);
		Car car2 = new Car(a, b, ag, 2);
		Car car3 = new Car(a, b, ag, 3);
		Car car4 = new Car(a, b, ag, 4);
		Car car5 = new Car(a, b, ag, 5);
//		Car car6 = new Car(a, b, ag, 6);
//
		car1.getNextPoint();
		car2.getNextPoint();
		car3.getNextPoint();
		car4.getNextPoint();
		car5.getNextPoint();
//		car6.getNextPoint();

	}
	
}
