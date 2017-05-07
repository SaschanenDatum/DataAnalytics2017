package data.analytics.smart.traffic.model.points;

import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Direction;

import java.util.Map;
import java.util.Set;

/**
 * Created by johannesdato on 07.05.17.
 *
 * Util Class for convenience Methods for the Point API
 */
public class PointUtils {

    public static CardinalDirection getIncomingDirection(Point outgoing, Point incoming) {

        CardinalDirection outgoingCardinalDirection, incomingCardinalDirection;

        // Point one is a StartOrEndPoint
        if(outgoing instanceof StartOrEndPoint) {
            StartOrEndPoint outgoingIsStartOrEnd = (StartOrEndPoint) outgoing;
            incomingCardinalDirection = outgoingIsStartOrEnd.getDirection().getOpposite();
            return incomingCardinalDirection;
        }

        // Point two is a StartOrEndPoint
        if(incoming instanceof StartOrEndPoint) {
            //TODO: Maybe implement with a nice exception
            return null;
        }

        if(outgoing instanceof CrossRoad && incoming instanceof CrossRoad) {
            CrossRoad outgoingCrossroad = (CrossRoad) outgoing;
            CrossRoad incomingCrossroad = (CrossRoad) incoming;

            Map<Direction, Point> outgoingConnectionPoints = outgoingCrossroad.getConnectingPoints();
            if(outgoingConnectionPoints.containsValue(incomingCrossroad)) {
                Set<Direction> keys = outgoingConnectionPoints.keySet();
                for(Direction direction : keys) {
                    Point p = outgoingConnectionPoints.get(direction);
                    if(p.equals(incomingCrossroad)) {
                        return direction.getOpposite();
                    }
                }
            }

        }

        // Everything else
        return null;
    }
}
