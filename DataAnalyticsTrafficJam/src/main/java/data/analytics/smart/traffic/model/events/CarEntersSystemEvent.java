package data.analytics.smart.traffic.model.events;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.movement.Route;

public class CarEntersSystemEvent {

	private Route route;
	
	private Car car;

	public CarEntersSystemEvent(Route route, Car car) {
		this.route = route;
		this.car = car;
	}

	public Route getRoute() {
		return route;
	}

	public Car getCar() {
		return car;
	}
	
	
}
