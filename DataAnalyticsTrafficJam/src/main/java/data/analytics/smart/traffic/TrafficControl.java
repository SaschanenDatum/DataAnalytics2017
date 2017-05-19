package data.analytics.smart.traffic;

import java.util.LinkedHashSet;
import java.util.Set;

import data.analytics.smart.traffic.esper.CarEsper;
import data.analytics.smart.traffic.esper.CarEsperFactory;
import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.events.PublishSolveEvent;
import data.analytics.smart.traffic.model.events.PublishTrafficJamEvent;
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
		
		System.out.println("############ Start of Demo 1 ############");
		CrossRoad crossRoad1 = new CrossRoad("1", 10);
		Route route1 = RouteFactory.createRoute(a, b, crossRoad1);
		Route route2 = RouteFactory.createRoute(b, c, crossRoad1);
		Route route3 = RouteFactory.createRoute(c, d, crossRoad1);
		Route route4 = RouteFactory.createRoute(d, a, crossRoad1);
		crossRoad1.addCrossingPoint(westDirection, a);
		crossRoad1.addCrossingPoint(eastDirection, c);
		crossRoad1.addCrossingPoint(southDirection, b);
		crossRoad1.addCrossingPoint(northDirection, d);
		
	
		Car car1 = new Car(c, d, route3, 1);
		Car car0 = new Car(b, c, route2, 2);
		Car car3 = new Car(a, b, route1, 3);
		car1.getNextPoint();
		car0.getNextPoint();
		car3.getNextPoint();
		for (int i = 4; i < 14; i++) {
			Car car2 = new Car(d, a, route4, i);
			car2.getNextPoint();
		}
		
		
		
	}
	
}
