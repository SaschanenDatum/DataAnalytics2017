package data.analytics.smart.traffic.model;

import data.analytics.smart.traffic.model.movement.Direction;
import data.analytics.smart.traffic.model.points.CrossRoad;

public class TrafficLigthTimer implements Runnable{

	

	private int lightTimer;
	private CrossRoad parentRoad;

	public TrafficLigthTimer(int lightTimer, CrossRoad parentRoad){
		this.lightTimer = lightTimer;
		this.parentRoad = parentRoad;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			try {
				Thread.sleep(lightTimer*1000);
				this.parentRoad.switchFromTimer(new Direction(parentRoad.getGreenSide()).getLeft());
			} catch (InterruptedException e) {
				System.err.println("Early Ligth Switch detected");
			}
		}
	}
}
