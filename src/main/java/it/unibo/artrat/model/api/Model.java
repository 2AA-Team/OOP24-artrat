package it.unibo.artrat.model.api;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.market.Market;
import it.unibo.artrat.model.api.world.Floor;

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
     * @param player the passed player.
     */
    void setPlayer(Player player);

    /**
     * Method which obtains a copy of the current state of the market.
     * 
     * @return a copy of the current market
     */
    Market getMarket();

    /**
     * Method which updaets the current market instance with the new passed.
     * 
     * @param market the passed market.
     */
    void setMarket(Market market);

    /**
     * A method that permit to obtain a copy of the current floor.
     * 
     * @return a copy of current floor.
     */
    Floor getFloor();

    /**
     * A method that permit to change the current floor istance with the new
     * passed.
     * 
     * @param floor the passed floor.
     */
    void setFloor(Floor floor);

}
