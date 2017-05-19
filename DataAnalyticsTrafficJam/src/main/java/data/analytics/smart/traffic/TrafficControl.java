package data.analytics.smart.traffic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public static void main(String[] args) throws IOException {

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
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Wich mode?");
		String mode = reader.readLine();
		if("1".equals(mode)){
			
			CrossRoad crossRoad1 = new CrossRoad("1", 10);
			Route route1 = RouteFactory.createRoute(a, b, crossRoad1);
			Route route2 = RouteFactory.createRoute(b, c, crossRoad1);
			Route route3 = RouteFactory.createRoute(c, d, crossRoad1);
			Route route4 = RouteFactory.createRoute(d, a, crossRoad1);
			crossRoad1.addCrossingPoint(westDirection, a);
			crossRoad1.addCrossingPoint(eastDirection, c);
			crossRoad1.addCrossingPoint(southDirection, b);
			crossRoad1.addCrossingPoint(northDirection, d);
			System.out.println("############ Start of Demo 1 ############");
			Car car1 = new Car(c, d, route3, 1);
			Car car0 = new Car(b, c, route2, 2);
//			try {
//				Thread.sleep(90*1000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
			Car car3 = new Car(a, b, route1, 3);
			car1.getNextPoint();
			car0.getNextPoint();
			car3.getNextPoint();
			for (int i = 4; i < 14; i++) {
				Car car2 = new Car(d, a, route4, i);
				car2.getNextPoint();
			}
			
		}
		if("2".equals(mode)){
			CrossRoad crossRoad1 = new CrossRoad("1", 10);
			crossRoad1.addCrossingPoint(westDirection, a);
			crossRoad1.addCrossingPoint(southDirection, b);
			
			CrossRoad crossRoad2 = new CrossRoad("2", 5);
			crossRoad2.addCrossingPoint(westDirection, crossRoad1);
			crossRoad2.addCrossingPoint(eastDirection, d);
			crossRoad2.addCrossingPoint(southDirection, c);
			
			CrossRoad crossRoad3 = new CrossRoad("3", 20);
			crossRoad3.addCrossingPoint(southDirection, crossRoad2);
			crossRoad3.addCrossingPoint(eastDirection, e);
			crossRoad3.addCrossingPoint(northDirection, f);
			
			CrossRoad crossRoad4 = new CrossRoad("4", 15);
			crossRoad4.addCrossingPoint(westDirection, h);
			crossRoad4.addCrossingPoint(northDirection, g);
			crossRoad4.addCrossingPoint(eastDirection, crossRoad3);
			crossRoad4.addCrossingPoint(southDirection, crossRoad1);
			
			crossRoad1.addCrossingPoint(eastDirection, crossRoad2);
			crossRoad1.addCrossingPoint(northDirection, crossRoad4);
			crossRoad2.addCrossingPoint(northDirection, crossRoad3);
			crossRoad3.addCrossingPoint(westDirection, crossRoad4);
			
			Route route1 = RouteFactory.createRoute(e, a, crossRoad3, crossRoad2, crossRoad1);
			Route altRoute1 = RouteFactory.createRoute(e, a, crossRoad3, crossRoad2, crossRoad1);
			route1.addAlternaitve(crossRoad3, altRoute1);
			
			Route route2 = RouteFactory.createRoute(c, b, crossRoad2, crossRoad1);
			Route route3 = RouteFactory.createRoute(b, h, crossRoad1, crossRoad4);
			System.out.println("### Demo 2 ###");
			Car startCar = new Car(e, a, route1, 0);
			Car blockCar = new Car(a, h, route3, 1);
			startCar.enterSystem();
			blockCar.enterSystem();
			for (int i = 2; i < 6; i++) {
				Car car = new Car(c, b, route2, i);
				car.enterSystem();
			}
			Car testCar = new Car(e, a, route1, 7);
			testCar.enterSystem();
			Car finalCar = new Car(e, a, route1, 8);
			
			
		}
		
		
	}
	
}
