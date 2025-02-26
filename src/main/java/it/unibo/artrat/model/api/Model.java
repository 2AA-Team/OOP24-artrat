package it.unibo.artrat.model.api;

import java.util.List;

import it.unibo.artrat.model.api.market.Market;
import it.unibo.artrat.model.api.missioncenter.Mission;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.characters.characters.Player;

/**
 * An interface to rapresent the model.
 */
public interface Model {
    /**
     * A method that permits to obtain a copy of the current player.
     * 
     * @return a copy of current player.
     */
    Player getPlayer();

    /**
     * A method that permits to change the current player istance with the new
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
     * A method that obtains a copy of the current state of mision center.
     * 
     * @return a list of missions, which will be used in MissionCenter.
     */
    List<Mission> getMissions();

    /**
     * A method that permit to obtain a copy of the current floor.
     * 
     * @return a copy of current floor.
     */
    Floor getFloor();

    /**
     * A method that permits to change the current floor istance with the new
     * passed.
     * 
     * @param floor the passed floor.
     */
    void setFloor(Floor floor);

    /**
     * A method that permits to change the current list of missions istance with the
     * new passed.
     * 
     * @param passedMissions the new list of missions.
     */
    void setMissions(List<Mission> passedMissions);
}
