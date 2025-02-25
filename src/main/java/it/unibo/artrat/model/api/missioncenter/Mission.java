package it.unibo.artrat.model.api.missioncenter;

import it.unibo.artrat.model.api.characters.Player;

/**
 * Mission base interface.
 * 
 * @author Manuel Benagli
 */
public interface Mission {

    /**
     * @return the name of the read mission.
     */
    String getName();

    /**
     * @return The task to accomplish.
     */
    String getText();

    /**
     * 
     * @return true if the mission is done, false otherwise.
     */
    boolean isStatusDone();

    /**
     * 
     * @param stat a boolean (true, if the mission is done).
     */
    void setStatus(boolean stat);

    /**
     * @param passedPlayer the player and everything connected to him (for example coins and inventory).
     * @return true if the mission is achieved.
     */
    boolean isMissionDone(Player passedPlayer);
}
