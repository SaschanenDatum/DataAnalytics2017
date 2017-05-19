package data.analytics.smart.traffic.esper;

public final class CarEsperFactory {
	
	public static CarEsper carControl;
	
	public static CarEsper getCarControl(){
		if(carControl == null){
			carControl = new CarEsper();
		}
		return carControl;
	}
	

}
