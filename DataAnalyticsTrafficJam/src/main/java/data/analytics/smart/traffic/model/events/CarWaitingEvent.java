package data.analytics.smart.traffic.model.events;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.points.CrossRoad;

/**
 * Created by johannesdato on 14.05.17.
 */
public class CarWaitingEvent {
    private Car waitingCar;
    private CrossRoad crossRoad;
    private CardinalDirection waitingQueue;

    public CarWaitingEvent(Car waitingCar, CrossRoad crossRoad, CardinalDirection waitingQueue) {
        this.waitingCar = waitingCar;
        this.crossRoad = crossRoad;
        this.waitingQueue = waitingQueue;
    }

    public Car getWaitingCar() {
        return waitingCar;
    }

    public CrossRoad getCrossRoad() {
        return crossRoad;
    }

    public CardinalDirection getWaitingQueue() {
        return waitingQueue;
    }
}
