package data.analytics.smart.traffic.model.movement;

import java.util.ArrayList;
import java.util.List;

import data.analytics.smart.traffic.model.points.CrossRoad;
import data.analytics.smart.traffic.model.points.Point;

public final class RouteFactory {
	
	public static Route createRoute(Point start, Point end, CrossRoad... args){
		List<CrossRoad> roads = new ArrayList<>();
		for (int i = 0; i < args.length; i++) {
			roads.add(args[i]);
		}
		return new Route(start, end, roads);
	}
}
