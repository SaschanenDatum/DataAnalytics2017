package data.analytics.smart.traffic.model;

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
	
	
}
