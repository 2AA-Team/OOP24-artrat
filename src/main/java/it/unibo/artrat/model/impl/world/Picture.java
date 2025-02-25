package it.unibo.artrat.model.impl.world;

import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.utils.impl.Point;

/**
 * game object to represents picture.
 * 
 * @author Matteo Tonelli
 */
public class Picture extends AbstractGameObject implements Collectable {

    private final double price;

    /**
     * constructor that specify center and puts hitbox at 1.
     * 
     * @param x     x coordinate
     * @param y     y coordinate
     * @param price value of the picture
     */
    public Picture(final double x, final double y, final double price) {
        super(new Point(x, y), 1, 1);
        this.price = price;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long delta) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice() {
        return this.price;
    }

}
