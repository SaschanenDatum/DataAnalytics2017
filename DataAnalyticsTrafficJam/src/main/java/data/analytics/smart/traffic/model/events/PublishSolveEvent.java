package data.analytics.smart.traffic.model.events;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.points.Point;

public class PublishSolveEvent {

	private Car car;
	
	private Point fromPoint;
	
	private Point toPoint;
	
	public PublishSolveEvent(Car car, Point fromPoint, Point toPoint) {
		this.car = car;
		this.fromPoint = fromPoint;
		this.toPoint = toPoint;
	}

	public Car getCar() {
		return car;
	}

	public Point getFromPoint() {
		return fromPoint;
	}

	public Point getToPoint() {
		return toPoint;
	}
	
}
