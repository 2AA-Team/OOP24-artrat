package it.unibo.artrat.model.api;

import it.unibo.artrat.model.api.characters.Player;

/**
 * An interface to rapresent the model.
 */
public interface Model {
    /**
     * A method that permit to obtain a copy of the current player.
     * 
     * @return a copy of current player.
     */
    Player getPlayer();

    /**
     * A method that permit to change the current player istance with the new
     * passed.
     * 
     * @param player
     */
    void setPlayer(Player player);

}
