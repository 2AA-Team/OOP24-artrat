package it.unibo.artrat.model.api.characters;

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
}
