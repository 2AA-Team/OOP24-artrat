package it.unibo.artrat.model.impl.world;

import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.utils.impl.Point;

/**
 * class that represents wall gameobject.
 * 
 * @author Matteo Tonelli
 */
public class Wall extends AbstractGameObject {

    /**
     * constructor that specify center and puts hitbox at 1.
     * 
     * @param x x coordinate
     * @param y y coordinate
     */
    public Wall(final double x, final double y) {
        super(new Point(x, y), 1, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long delta) {
    }

}
