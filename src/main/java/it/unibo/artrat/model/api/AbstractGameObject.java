package it.unibo.artrat.model.api;

import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * Abstract class that implements default instructions.
 */
public abstract class AbstractGameObject implements GameObject {
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
     * @param bottomLeft bottomm left corner of the game object's bounding box
     * @param topRight   top right corner of the game object's bounding box
     */
    public AbstractGameObject(final Point bottomLeft, final Point topRight) {
        this.hitBox = new BoundingBoxImpl(bottomLeft, topRight);
    }

    /**
     * Get current positio.
     * 
     * @return current game object position
     */
    public Point getPosition() {
        return this.hitBox.getCenter();
    }

    /**
     * Set the current game object position.
     * 
     * @param position current position
     */
    public void setPosition(final Point position) {
        this.hitBox.setCenter(position);
    }

    @Override
    public void update(final int delta) {

    }

}
