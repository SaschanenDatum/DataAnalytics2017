package data.analytics.smart.traffic;

import data.analytics.smart.traffic.esper.EsperService;
import data.analytics.smart.traffic.model.CarIncomingEvent;
import data.analytics.smart.traffic.model.CrossRoad;
import data.analytics.smart.traffic.model.Direction;
import data.analytics.smart.traffic.model.LightSwitchEvent;

public class TrafficControl {

	public static void main(String[] args) {
		CrossRoad road = new CrossRoad();
		EsperService service = new EsperService(road);
		CarIncomingEvent event = new CarIncomingEvent(Direction.EAST);
		for (int i = 0; i <5; i++) {
			service.sendEvent(event);
		}
	}

}
