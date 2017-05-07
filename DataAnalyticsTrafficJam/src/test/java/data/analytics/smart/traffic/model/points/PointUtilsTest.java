package data.analytics.smart.traffic.model.points;

import data.analytics.smart.traffic.model.movement.CardinalDirection;
import data.analytics.smart.traffic.model.movement.Direction;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static data.analytics.smart.traffic.model.points.PointUtils.getIncomingDirection;

/**
 * Created by johannesdato on 07.05.17.
 */
public class PointUtilsTest {
    @Test
    public void testGetIncomingDirection() throws Exception {
        CrossRoad one = new CrossRoad("1", 5.0);
        CrossRoad two = new CrossRoad("2", 5.0);

        Map<Direction, Point> connectingPoints1 = new HashMap<>();
        connectingPoints1.put(new Direction(CardinalDirection.EAST), two);
        connectingPoints1.put(new Direction(CardinalDirection.WEST), null);
        connectingPoints1.put(new Direction(CardinalDirection.NORTH), null);
        connectingPoints1.put(new Direction(CardinalDirection.SOUTH), null);

        one.setConnectingPoints(connectingPoints1);

        Map<Direction, Point> connectingPoints2 = new HashMap<>();
        connectingPoints2.put(new Direction(CardinalDirection.EAST), null);
        connectingPoints2.put(new Direction(CardinalDirection.WEST), one);
        connectingPoints2.put(new Direction(CardinalDirection.NORTH), null);
        connectingPoints2.put(new Direction(CardinalDirection.SOUTH), null);

        two.setConnectingPoints(connectingPoints2);

        CardinalDirection result = getIncomingDirection(one, two);

        Assert.assertEquals(CardinalDirection.WEST, result);
    }

}