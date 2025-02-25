package it.unibo.artrat.model.impl;

import java.util.List;
import java.util.function.Supplier;

import it.unibo.artrat.utils.impl.Point;

/**
 * compass to aim at the nearest point.
 * 
 * @author Matteo Tonelli
 */
public class CompassNearest extends AbstractCompass {

    private final Supplier<Point> center;
    private final Supplier<List<Point>> north;

    /**
     * compass constructor.
     * 
     * @param center supplier to get center of rotation
     * @param north  supplier to get north
     */
    public CompassNearest(final Supplier<Point> center, final Supplier<List<Point>> north) {
        this.north = north;
        this.center = center;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Point getNorth() {
        return north.get().stream().min((a, b) -> {
            return Double.compare(a.getEuclideanDistance(getCenter()), b.getEuclideanDistance(getCenter()));
        }).orElseGet(center);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Point getCenter() {
        return center.get();
    }

}
