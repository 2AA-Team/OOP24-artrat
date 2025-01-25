package it.unibo.artrat.model.api;

/**
 * GameObject represents the base class for any physical element in the game.
 */
public interface GameObject {

    /**
     * updates the object data.
     * 
     * @param delta delta time
     */
    void update(int delta);

    /**
     * reloads the object view.
     */
    void redraw();
}
