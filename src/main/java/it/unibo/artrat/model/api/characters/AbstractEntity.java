package it.unibo.artrat.model.api.characters;

import it.unibo.artrat.model.api.AbstractGameObject;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Essential implementetion of an entity.
 */
public abstract class AbstractEntity extends AbstractGameObject implements Entity {
    private final Vector2d speed;

    /**
     * Entity constructor.
     * 
     * @param p      entity position
     * @param radius entity bounding box radius
     */
    public AbstractEntity(final Point p, final double radius) {
        this(p, radius, new Vector2d());
    }

    /**
     * Entity constructor passing direction (vector).
     * 
     * @param p      bounding box center
     * @param radius bounding box radius
     * @param v      direction
     */
    public AbstractEntity(final Point p, final double radius, final Vector2d v) {
        super(p, radius);
        this.speed = v;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interact() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final int delta) {
        this.setPosition(this.getPosition().sum(speed.mul(delta)));
    }

}
