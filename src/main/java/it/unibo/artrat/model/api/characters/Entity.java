package it.unibo.artrat.model.api.characters;

import it.unibo.artrat.model.api.GameObject;

/**
 * Interface that rappresents every "alive" object.
 */
public interface Entity extends GameObject {

    /**
     * Movement handling.
     */
    void move();

    /**
     * Usefull method for objects interaction.
     */
    void interact();
}
