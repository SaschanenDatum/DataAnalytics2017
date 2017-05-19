package data.analytics.smart.traffic.model.events;

import com.espertech.esper.client.annotation.Priority;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.points.CrossRoad;

/**
 * Created by johannesdato on 14.05.17.
 */
public class CarWaitingEvent {
    private CrossRoad crossRoad;
    private CardinalDirection waitingQueue;
    private Car car;

    public CarWaitingEvent(CrossRoad crossRoad, CardinalDirection waitingQueue, Car car) {
        this.crossRoad = crossRoad;
        this.waitingQueue = waitingQueue;
		this.car = car;
    }

    public CrossRoad getCrossRoad() {
        return crossRoad;
    }

    public CardinalDirection getWaitingQueue() {
        return waitingQueue;
    }

	public Car getCar() {
		return car;
	}
}
