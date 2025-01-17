package it.unibo.artrat.model.api.characters;

/**
 * Class that rappresents enemy.
 */
public interface Enemy extends Entity {
    /**
     * If player gets too close to enemies will be followed.
     */
    void follow();

    /**
     * Capture the player when very closer.
     */
    void capture();
}
