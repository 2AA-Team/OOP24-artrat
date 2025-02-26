package it.unibo.artrat.model.api;

import java.util.List;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.missioncenter.Mission;
import it.unibo.artrat.model.api.shop.Shop;
import it.unibo.artrat.model.api.world.Floor;

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
     * Method which obtains a copy of the current state of the shop.
     * 
     * @return a copy of the current shop.
     */
    Shop getShop();

    /**
     * Method which updaets the current shop instance with the new passed.
     * 
     * @param shop the passed shop.
     */
    void setShop(Shop shop);

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
     * A method that permits to change the current floor istance with the new passed.
     * 
     * @param floor the passed floor.
     */
    void setFloor(Floor floor);

    /**
     * A method that permits to change the current list of missions istance with the new passed.
     * 
     * @param passedMissions the new list of missions.
     */
    void setMissions(List<Mission> passedMissions);
}
