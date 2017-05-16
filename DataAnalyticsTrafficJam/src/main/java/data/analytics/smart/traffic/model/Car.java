package data.analytics.smart.traffic.model;

import static data.analytics.smart.traffic.model.points.PointUtils.getIncomingDirection;

import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Route;
import data.analytics.smart.traffic.model.points.CrossRoad;
import data.analytics.smart.traffic.model.points.Point;

public class Car {

	private int number;

	private Point startPoint;
	
	private Point endPoint;
	
	private Point currentPoint;
	
	private Route route;

	public Car(Point startPoint, Point endPoint, Route route, int number) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.currentPoint = startPoint;
		this.route = route;
		this.number = number;
	}

	public int getNumber() {
		return number;
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

	public synchronized void getNextPoint(){

		Point nextPointOnRoute = this.route.getNextPoint(this.currentPoint);
		CardinalDirection incomingDirection = getIncomingDirection(currentPoint, nextPointOnRoute);
		
		this.currentPoint = nextPointOnRoute;

		if(currentPoint instanceof CrossRoad){
			CrossRoad road = (CrossRoad)this.currentPoint;
			road.announceCar(incomingDirection, this);
		}else if(route.getEndPoint().equals(currentPoint)){
			System.out.println("###################Car " + this.number + " reached destination " +currentPoint.getId());
		}
	}
	
	/**
	 * Checks for another Route, if there is none it does nothing else the current route will be overwriten.
	 */
	public synchronized void checkForNewRoute(){
		Route alternative = this.route.calculateAlternativeRoute(this.currentPoint);
		if(alternative != null){
			this.route = alternative;
		}
	}
}
