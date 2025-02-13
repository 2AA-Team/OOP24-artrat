package it.unibo.artrat.model.api.characters;

import it.unibo.artrat.model.api.AbstractGameObject;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Essential implementetion of an entity.
 */
public abstract class AbstractEntity extends AbstractGameObject implements Entity {
    private Vector2d speed = new Vector2d();

    /**
     * Entity constructor.
     * 
     * @param topLeft     top left corner entity boundingbox
     * @param bottomRight bottom right corner entity boundingbox
     */
    public AbstractEntity(final Point topLeft, final Point bottomRight) {
        this(topLeft, bottomRight, new Vector2d());
    }

    /**
     * Entity constructor passing direction (vector).
     * 
     * @param topLeft     top left corner entity boundingbox
     * @param bottomRight bottom right corner entity boundingbox
     * @param v           direction
     */
    public AbstractEntity(final Point topLeft, final Point bottomRight, final Vector2d v) {
        super(topLeft, bottomRight);
        this.speed.setX(v.getX());
        this.speed.setY(v.getY());
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2d getSpeed() {
        return new Vector2d(this.speed.getX(), this.speed.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final Vector2d v) {
        this.speed = new Vector2d(v.getX(), v.getY());
    }

}
