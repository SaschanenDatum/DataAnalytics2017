package data.analytics.smart.traffic.model.movement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.analytics.smart.traffic.model.points.CrossRoad;
import data.analytics.smart.traffic.model.points.Point;

public class Route {

	private Point startPoint;
	
	private Point endPoint;
	
	private List<CrossRoad> crossRoads = new ArrayList<>();
	
	private Map<Point, Route> alternatives = new HashMap<>();
	
	public Route(Point startPoint, Point endPoint, List<CrossRoad> crossRoads) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.crossRoads = crossRoads;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public List<CrossRoad> getCrossRoads() {
		return crossRoads;
	}
	
	
	public void addAlternaitve(Point startCrossRoad, Route alternative){
		this.alternatives.put(startCrossRoad, alternative);
	}
	
	/**
	 * Looks if there is another route from this point.
	 * @param currentPoint the point where the care currently is. Should be a crossroad.
	 * @return another route. 
	 */
	public Route calculateAlternativeRoute(Point currentPoint){
		return this.calculateAlternativeRoute(currentPoint);
	}
	
	public void addCrossRaod(CrossRoad road){
		this.crossRoads.add(road);
	}
	public Point getNextPoint(Point currentPoint){
	
		int index = this.crossRoads.indexOf(currentPoint)+1;
		if(index > crossRoads.size()-1){
			return endPoint;
		}else{
			return crossRoads.get(index);
		}
	}
	
}
