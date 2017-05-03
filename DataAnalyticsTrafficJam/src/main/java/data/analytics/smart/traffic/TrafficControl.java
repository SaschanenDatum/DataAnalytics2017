package data.analytics.smart.traffic;

import data.analytics.smart.traffic.esper.EsperService;
import data.analytics.smart.traffic.model.CarIncomingEvent;
import data.analytics.smart.traffic.model.CrossRoad;
import data.analytics.smart.traffic.model.Direction;
import data.analytics.smart.traffic.model.CardinalDirection;
import data.analytics.smart.traffic.model.LightSwitchEvent;

public class TrafficControl {

	public static void main(String[] args) {
		CrossRoad road = new CrossRoad("4", 0);
		EsperService service = new EsperService(road);
		CarIncomingEvent event = new CarIncomingEvent(CardinalDirection.EAST);
		for (int i = 0; i <5; i++) {
			service.sendEvent(event);
		}
		CardinalDirection east = CardinalDirection.EAST;
		CardinalDirection north = CardinalDirection.NORTH;
		CardinalDirection west = CardinalDirection.WEST;
		CardinalDirection south = CardinalDirection.SOUTH;
		Direction northDirection = new Direction(north, east, west, south);
		Direction southDirection = new Direction(south, west, east, north);
		Direction eastDirection = new Direction(east, south, north, west);
		Direction westDirection = new Direction(west, north, south, east);
		//TODO Create Routes
	}

}
