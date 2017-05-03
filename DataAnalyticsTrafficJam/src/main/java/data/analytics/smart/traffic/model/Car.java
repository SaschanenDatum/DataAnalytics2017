package data.analytics.smart.traffic.model;

import data.analytics.smart.traffic.model.movement.Route;
import points.Point;

public class Car {

	private Point startPoint;
	
	private Point endPoint;
	
	private Point currentPoint;
	
	private Route route;

	public Car(Point startPoint, Point endPoint, Route route) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.currentPoint = startPoint;
		this.route = route;
	}

	public Point getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(Point currentPoint) {
		this.currentPoint = currentPoint;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void getNextPoint(){
		Point nextPoint = this.route.getNextPoint(this.currentPoint);
		if(nextPoint == null){
			System.out.println("FINISH");
		}
		this.currentPoint = nextPoint;
	}
}
