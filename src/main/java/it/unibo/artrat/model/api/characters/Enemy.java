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
     * Capture the player when very closer.
     */
    void capture();

    /**
     * Get the current FOV.
     * 
     * @return FOV bounding box
     */
    public BoundingBox getFieldOfView();

    /**
     * Check if the enemy is following the player.
     * 
     * @return true if enemy is following, false otherwise.
     */
    public boolean isFollowing();

    /**
     * Trigger the enemy.
     * 
     * @param follow trigger
     */
    public void trigger(boolean follow);

    /**
     * Set a new FOV.
     * 
     * @param fov field of view
     */
    public void setFieldOfView(BoundingBox fov);
}
