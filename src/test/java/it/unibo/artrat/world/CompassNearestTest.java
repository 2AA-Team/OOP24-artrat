package it.unibo.artrat.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.impl.CompassNearest;
import it.unibo.artrat.utils.impl.Point;

class CompassNearestTest {

    private CompassNearest compass;
    private Supplier<Point> center;
    private Supplier<List<Point>> north;

    @BeforeEach
    void setUp() {
        center = () -> new Point(0, 0);

    }

    @Test
    void testCompassFullList() {
        north = () -> Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(-1, -1),
                new Point(0, 3));
        compass = new CompassNearest(center, north);
        assertEquals(0.785, compass.calculateAngle(), 0.001); // angle to (1,1)
        north = () -> Arrays.asList(
                new Point(-1, -1),
                new Point(2, 2),
                new Point(0, 3));
        compass = new CompassNearest(center, north);
        assertNotEquals(0.785, compass.calculateAngle(), 0.001); // angle to (-1,-1)

    }

    @Test
    void testGetEmptySupplier() {
        north = () -> Arrays.asList();
        compass = new CompassNearest(center, north);
        assertEquals(0, compass.calculateAngle());
        center = () -> null;
        assertEquals(0, compass.calculateAngle());
    }
}