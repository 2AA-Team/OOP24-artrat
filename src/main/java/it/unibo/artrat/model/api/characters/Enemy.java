package it.unibo.artrat.model.api.characters;

import it.unibo.artrat.utils.api.BoundingBox;

/**
 * Class that rappresents enemy.
 * 
 * @author Samuele Trapani
 */
public interface Enemy extends Entity {
    /**
     * If player gets too close to enemies will be followed.
     * 
     * @param p player
     */
    void follow(Player p);

    /**
     * Get the current FOV.
     * 
     * @return FOV bounding box
     */
    BoundingBox getFieldOfView();

    /**
     * Set a new FOV.
     * 
     * @param fov field of view
     */
    void setFieldOfView(BoundingBox fov);
}
