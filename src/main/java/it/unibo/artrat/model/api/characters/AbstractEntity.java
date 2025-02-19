package it.unibo.artrat.model.api.characters;

import java.util.HashSet;
import java.util.Set;

import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Essential implementetion of an entity.
 * 
 * @author Samuele Trapani
 */
public abstract class AbstractEntity extends AbstractGameObject implements Entity {

    private final Set<Vector2d> speed = new HashSet<>();

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
        final Vector2d vel = new Vector2d(v.getX(), v.getY());
        speed.add(vel);

    }

    /**
     * Entity constructor passing direction (vector).
     * 
     * @param center center of the entity boundingbox
     * @param width  width of the entity boundingbox
     * @param height height of the entity boundingbox
     * @param v      direction
     */
    public AbstractEntity(final Point center, final double width, final double height, final Vector2d v) {
        super(center, width, height);
        final Vector2d vel = new Vector2d(v.getX(), v.getY());
        speed.add(vel);
    }

    /**
     * 
     * @param center center of the entity boundingbox
     * @param speed  direction
     */
    public AbstractEntity(final Point center, final Vector2d speed) {
        this(center, DEFAULT_SIZE, DEFAULT_SIZE, speed);
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
    public void update(final long delta) {
        System.out.println(this.getSpeed());
        System.out.println("SIZE: " + this.speed);
        this.setPosition(this.getPosition().sum(getSpeed().mul(delta * 0.1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2d getSpeed() {
        return speed.stream().reduce(new Vector2d(), Vector2d::summVector2d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final Vector2d v) {
        this.speed.clear();
        this.speed.add(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDirection(final Vector2d v) {
        this.speed.add(v);
    }

}
