package data.analytics.smart.traffic.model.points;

public abstract class Point {

	protected String id;
	
	public Point(String id){
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
}
