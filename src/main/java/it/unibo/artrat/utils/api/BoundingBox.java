package it.unibo.artrat.utils.api;

import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * Bounding box interface used to create different boxes for game object bounds.
 */
public interface BoundingBox {

    /**
     * Check circles collision.
     * 
     * @param box box to check collision with
     * @return true if boundingbox is colliding false otherwise.
     */
    boolean isColliding(BoundingBoxImpl box);

    /**
     * Set the center of the bounding box.
     * 
     * @param center center point
     */
    public void setCenter(final Point center);

    /**
     * Get bottom right corner.
     * 
     * @return Bottom right bounding box corner
     */
    public Point getBottomRight();

    /**
     * Get box center.
     * 
     * @return Center point of the bounding box.
     */
    public Point getCenter();

    /**
     * Get top left corner.
     * 
     * @return top left bounding box corner
     */
    public Point getTopLeft();

    /**
     * Get box width.
     * 
     * @return rectangle width
     */
    public double getWidth();

    /**
     * Get rectangle height.
     * 
     * @return rectangle height
     */
    public double getHeight();
}
