package it.unibo.artrat.model.api;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.market.Market;
/**
 * An interface to rapresent the model.
 */
public interface Model {
    /**
     * A method that permit to obtain a copy of the current player.
     * @return a copy of current player.
     */
    Player getPlayer();

    /**
     * A method that permit to change the current player istance with the new passed.
     * @param player
     */
    void setPlayer(Player player);

    /**
     * Method which obtains a copy of the current state of the market
     * @return a copy of the current market
     * @autor Manuel Benagli
     */
    Market getMarket();

    /**
     * Method which updaets the current market instance with the new passed
     * @param market
     * @autor Manuel Benagli
     */
    void setMarket(Market market);

}
