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

		CarEsper esper = CarEsperFactory.getCarControl();
		esper.sendEvent(new PublishTrafficJamEvent(crossRoad1, crossRoad2, new Car(a, d, null, 1)));
		System.out.println("First one");
		esper.sendEvent(new PublishTrafficJamEvent(crossRoad2, crossRoad1, new Car(a, d, null, 1)));
		System.err.println("No Match");
		esper.sendEvent(new PublishTrafficJamEvent(crossRoad1, crossRoad2, new Car(a, d, null, 1)));
		System.out.println("Match");
		esper.sendEvent(new PublishSolveEvent(new Car(a, d, null, 1), crossRoad1, crossRoad2));
	}
	
}
