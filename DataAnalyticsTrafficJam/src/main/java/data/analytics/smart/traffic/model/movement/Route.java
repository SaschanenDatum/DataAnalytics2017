package data.analytics.smart.traffic.model.movement;

import java.util.ArrayList;
import java.util.List;

import points.CrossRoad;
import points.Point;

public class Route {

	private Point startPoint;
	
	private Point endPoint;
	
	//TODO Create Stack or somthing simellar
	private List<CrossRoad> crossRoads = new ArrayList<>();
	
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
	
	public void addCrossRaod(CrossRoad road){
		this.crossRoads.add(road);
	}
	public Point getNextPoint(Point currentPoint){
	
		if(currentPoint.equals(endPoint)){
			return null;
		}
		//TODO get Value from Crossroad Stack
		return null;
	}
	
}
