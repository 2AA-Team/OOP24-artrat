package it.unibo.artrat.utils.api;

import it.unibo.artrat.utils.impl.BoundingBoxImpl;

/**
 * Bounding box interface used to create different boxes for game object bounds.
 */
public interface BoundingBox {

    /**
     * Collision detection.
     * 
     * @param box game object bounding box
     * @return
     */
    boolean isColliding(final BoundingBoxImpl box);
}
