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

    private Set<Vector2d> speed = new HashSet<>();
    private double velocity = 0.01;

    public AbstractEntity(Point center, double width, double height) {
        super(center, width, height);

    }

    public AbstractEntity(Point center) {
        super(center);
    }

    /**
     * Entity constructor.
     * 
     * @param topLeft     top left corner entity boundingbox
     * @param bottomRight bottom right corner entity boundingbox
     */
    public AbstractEntity(final Point topLeft, final Point bottomRight) {
        this(topLeft, bottomRight, new HashSet<>());
    }

    /**
     * Entity constructor passing direction (vector).
     * 
     * @param topLeft     top left corner entity boundingbox
     * @param bottomRight bottom right corner entity boundingbox
     * @param v           direction
     */
    public AbstractEntity(final Point topLeft, final Point bottomRight, final Set<Vector2d> v) {
        super(topLeft, bottomRight);
        this.speed = new HashSet<>(v);

    }

    /**
     * Entity constructor passing direction (vector).
     * 
     * @param center center of the entity boundingbox
     * @param width  width of the entity boundingbox
     * @param height height of the entity boundingbox
     * @param v      direction
     */
    public AbstractEntity(final Point center, final double width, final double height, final Set<Vector2d> v) {
        super(center, width, height);
        this.speed = new HashSet<>(v);
    }

    /**
     * 
     * @param center center of the entity boundingbox
     * @param speed  direction
     */
    public AbstractEntity(final Point center, final Set<Vector2d> speed) {
        this(center, DEFAULT_SIZE, DEFAULT_SIZE, speed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        // final Random rd = new Random();
        // final var dir = rd.nextInt(Directions.values().length);
        // final Vector2d v = Directions.values()[dir].vector();
        // this.setSpeed(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long delta) {
        this.setPosition(this.getPosition().sum(calculateSpeed().mul(delta * getVelocity())));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDirection(final Vector2d v) {
        this.speed.remove(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2d calculateSpeed() {
        return speed.stream().reduce(new Vector2d(), Vector2d::summVector2d).normalize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Vector2d> getSpeed() {
        return new HashSet<>(this.speed);
    }

    @Override
    public double getVelocity() {
        return this.velocity;
    }

    @Override
    public void setVelocity(final double vel) {
        this.velocity = vel;
    }
}
