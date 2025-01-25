package it.unibo.artrat.utils.api;

import it.unibo.artrat.utils.impl.BoundingBoxImpl;

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
}
