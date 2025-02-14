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
    void setCenter(Point center);

    /**
     * Get bottom right corner.
     * 
     * @return Bottom right bounding box corner
     */
    Point getBottomRight();

    /**
     * Get box center.
     * 
     * @return Center point of the bounding box.
     */
    Point getCenter();

    /**
     * Get top left corner.
     * 
     * @return top left bounding box corner
     */
    Point getTopLeft();

    /**
     * Get box width.
     * 
     * @return rectangle width
     */
    double getWidth();

    /**
     * Get rectangle height.
     * 
     * @return rectangle height
     */
    double getHeight();
}
