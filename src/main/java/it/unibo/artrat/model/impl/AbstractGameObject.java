package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * Abstract class that implements default instructions.
 * 
 * @author Samuele Trapani
 */
public abstract class AbstractGameObject implements GameObject {
    /**
     * Default bounding box size.
     */
    public static final double DEFAULT_SIZE = 0.5;
    private final BoundingBoxImpl hitBox;

    /**
     * Abstract GameObject constructor without parameters.
     */
    public AbstractGameObject() {
        this.hitBox = new BoundingBoxImpl(new Point(0, 0), new Point(0, 0));
    }

    /**
     * Abstract GameObject constructor.
     * 
     * @param topLeft     top left corner of the game object's bounding box
     * @param bottomRight bottom right corner of the game object's bounding box
     */
    public AbstractGameObject(final Point topLeft, final Point bottomRight) {
        this.hitBox = new BoundingBoxImpl(topLeft, bottomRight);
    }

    /**
     * Abstract GameObject constructor.
     * 
     * @param center center of the game object's bounding box
     * @param width  width of the game object's bounding box
     * @param height height of the game object's bounding box
     */
    public AbstractGameObject(final Point center, final double width, final double height) {
        this.hitBox = new BoundingBoxImpl(center, width, height);
    }

    /**
     * Abstract GameObject constructor.
     * 
     * @param center center of the game object's bounding box
     */
    public AbstractGameObject(final Point center) {
        this.hitBox = new BoundingBoxImpl(center, DEFAULT_SIZE, DEFAULT_SIZE);
    }

    /**
     * Get current position.
     * 
     * @return current game object position
     */
    @Override
    public Point getPosition() {
        return this.hitBox.getCenter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point position) {
        this.hitBox.setCenter(position);
    }

    /**
     * moved the current game object position.
     * 
     * @param x x position to moved
     * @param y y position to moved
     */
    public void movedPosition(final int x, final int y) {
        this.hitBox.setCenter(new Point(hitBox.getCenter().getX() + x, hitBox.getCenter().getY() + y));
    }

    /**
     * Get current bounding box.
     * 
     * @return bounding box
     */
    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBoxImpl(this.hitBox.getTopLeft(), this.hitBox.getBottomRight());
    }
}
