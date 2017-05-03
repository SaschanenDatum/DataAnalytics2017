package data.analytics.smart.traffic.model;

import java.util.ArrayList;
import java.util.List;

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
	
}
