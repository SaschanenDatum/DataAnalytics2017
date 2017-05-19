package data.analytics.smart.traffic.model;

import static data.analytics.smart.traffic.model.points.PointUtils.getIncomingDirection;

import data.analytics.smart.traffic.esper.CarEsper;
import data.analytics.smart.traffic.esper.CarEsperFactory;
import data.analytics.smart.traffic.model.events.CarEntersSystemEvent;
import data.analytics.smart.traffic.model.events.PublishSolveEvent;
import data.analytics.smart.traffic.model.events.PublishTrafficJamEvent;
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

	private boolean finish = false;
	
	private boolean jamed;
	
	private CarEsper control = CarEsperFactory.getCarControl();

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
	
	public boolean isFinish(){
		return this.finish;
	}
	
	public void setJamed(boolean jamed){
		this.jamed = jamed;
	}
	
	public void reportJam(Point from, Point to){
		this.control.sendEvent(new PublishTrafficJamEvent(from, to, this));
	}
	
	public void enterSystem(){
		this.control.sendEvent(new CarEntersSystemEvent(getRoute(), this));
	}

	public synchronized void getNextPoint(){

		if(finish){
			return;
		}
		Point nextPointOnRoute = this.route.getNextPoint(this.currentPoint);
		CardinalDirection incomingDirection = getIncomingDirection(currentPoint, nextPointOnRoute);
		if(this.jamed){
			this.control.sendEvent(new PublishSolveEvent(this, currentPoint, nextPointOnRoute));
			this.jamed = false;
		}
		this.currentPoint = nextPointOnRoute;
		if(currentPoint instanceof CrossRoad){
			CrossRoad road = (CrossRoad)this.currentPoint;
			road.announceCar(incomingDirection, this);
		}else if(route.getEndPoint().equals(currentPoint)){
			System.out.println("###################Car " + this.number + " reached destination " +currentPoint.getId());
			finish  = true;
		}
	}
	
	public String toString(){
		return "Car " + this.number;
	}
}
