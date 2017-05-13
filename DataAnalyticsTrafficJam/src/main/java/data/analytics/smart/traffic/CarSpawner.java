package data.analytics.smart.traffic;

import java.util.concurrent.Callable;

import data.analytics.smart.traffic.model.Car;
import data.analytics.smart.traffic.model.movement.Route;

public class CarSpawner implements Runnable{

	private Route route;
	private int waitingTime;

	public CarSpawner(Route route, int waitingTime) {
		this.route = route;
		this.waitingTime = waitingTime;
		
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				Thread.sleep(waitingTime*1000);
				Car car = new Car(route.getStartPoint(), route.getEndPoint(), route);
				System.out.println("Car arrives from " + route.getStartPoint());
				car.getNextPoint();
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
