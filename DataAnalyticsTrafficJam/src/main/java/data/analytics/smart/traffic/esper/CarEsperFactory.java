package data.analytics.smart.traffic.esper;

public final class CarEsperFactory {
	
	private static CarEsper carControl;
	
	public static CarEsper getCarControl(){
		if(carControl == null){
			carControl = new CarEsper();
		}
		return carControl;
	}
	

}
