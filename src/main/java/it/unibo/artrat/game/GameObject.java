package it.unibo.artrat.game;

/**
 * GameObject represents the base class for any physical element in the game.
 */
public interface GameObject {

    /**
     * updates the object data.
     */
    void update();

    /**
     * reloads the object view.
     */
    void redraw();
}
