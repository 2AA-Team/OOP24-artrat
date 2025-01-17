package it.unibo.artrat.model.api.characters;

/**
 * Interface that rappresents every "alive" object.
 */
public interface Entity {

    /**
     * Movement handling.
     */
    void move();

    /**
     * Usefull method for objects interaction.
     */
    void interact();
}
