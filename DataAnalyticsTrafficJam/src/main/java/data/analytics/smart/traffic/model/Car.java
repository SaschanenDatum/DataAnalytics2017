package data.analytics.smart.traffic.model;

import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Direction;
import data.analytics.smart.traffic.model.movement.Route;
import data.analytics.smart.traffic.model.points.CrossRoad;
import data.analytics.smart.traffic.model.points.Point;

import static data.analytics.smart.traffic.model.points.PointUtils.getIncomingDirection;

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

		Point nextPointOnRoute = this.route.getNextPoint(this.currentPoint);
		// Autos müssen wissen aus welcher Richtung sie in eine neue CrossRoad fahren
		CardinalDirection incomingDirection = getIncomingDirection(currentPoint, nextPointOnRoute);

		// Hole den nächsten Punkt von der Route
		this.currentPoint = nextPointOnRoute;

		if(currentPoint instanceof CrossRoad){
			CrossRoad road = (CrossRoad)this.currentPoint;
			road.announceCar(incomingDirection, this);
		}
		if(currentPoint.equals(route.getEndPoint())){
			System.out.println("Car reached destination");
		}
	}
}
