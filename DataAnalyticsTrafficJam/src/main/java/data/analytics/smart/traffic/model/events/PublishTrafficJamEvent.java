package data.analytics.smart.traffic.model.events;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.points.Point;

public class PublishTrafficJamEvent {

	private Point fromPoint;
	
	private Point toPoint;

	private Car car;
	
	public PublishTrafficJamEvent(Point from, Point to, Car car) {
		this.fromPoint = from;
		this.toPoint = to;
		this.car = car;
	}

	public Point getFromPoint() {
		return fromPoint;
	}

	public Point getToPoint() {
		return toPoint;
	}

	public Car getCar() {
		return car;
	}
	
}
