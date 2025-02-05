package it.unibo.artrat.model.api.characters;

import it.unibo.artrat.model.api.AbstractGameObject;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Essential implementetion of an entity.
 */
public abstract class AbstractEntity extends AbstractGameObject implements Entity {
    private final Vector2d speed = new Vector2d();

    /**
     * Entity constructor.
     * 
     * @param bottomLeft bottom left corner entity boundingbox
     * @param topRight   top right corner entity boundingbox
     */
    public AbstractEntity(final Point bottomLeft, final Point topRight) {
        this(bottomLeft, topRight, new Vector2d());
    }

    /**
     * Entity constructor passing direction (vector).
     * 
     * @param bottomLeft bottom left corner entity boundingbox
     * @param topRight   top right corner entity boundingbox
     * @param v          direction
     */
    public AbstractEntity(final Point bottomLeft, final Point topRight, final Vector2d v) {
        super(bottomLeft, topRight);
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

}
