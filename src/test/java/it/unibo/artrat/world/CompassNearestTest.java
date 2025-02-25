package it.unibo.artrat.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.impl.CompassNearest;
import it.unibo.artrat.utils.impl.Point;

class CompassNearestTest {

    @Test
    void testCompassFullList() {
        final double delta = 0.001;
        final double expected = 0.785;
        CompassNearest compass;
        Supplier<List<Point>> north = () -> Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(-1, -1),
                new Point(0, 3));
        final Supplier<Point> center = () -> new Point();
        compass = new CompassNearest(center, north);
        assertEquals(expected, compass.calculateAngle(), delta); // angle to (1,1)
        north = () -> Arrays.asList(
                new Point(-1, -1),
                new Point(2, 2),
                new Point(0, 3));
        compass = new CompassNearest(center, north);
        assertNotEquals(expected, compass.calculateAngle(), delta); // angle to (-1,-1)

    }

    @Test
    void testGetEmptySupplier() {
        final CompassNearest compass;
        final Supplier<List<Point>> north = Arrays::asList;
        Supplier<Point> center = () -> new Point();
        compass = new CompassNearest(center, north);
        assertEquals(0, compass.calculateAngle());
        center = () -> null;
        final CompassNearest compass2 = new CompassNearest(center, north);
        assertThrows(NullPointerException.class, () -> {
            compass2.calculateAngle();
        });
    }
}
