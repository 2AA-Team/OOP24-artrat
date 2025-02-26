package it.unibo.artrat.model.api.missioncenter;

import it.unibo.artrat.model.api.characters.Player;

/**
 * Mission base interface.
 * 
 * @author Manuel Benagli
 */
public interface Mission {

    /**
     * A method which gets the mission's name.
     * 
     * @return the name of the read mission.
     */
    String getName();

    /**
     * A method which gets the mission's description.
     * 
     * @return The task to accomplish.
     */
    String getText();

    /**
     * Method which gets the mission's current status.
     * 
     * @return true if the mission is done, false otherwise.
     */
    boolean getStatus();

    /**
     * This method sets mission's status to false to true.
     * 
     * @param stat a boolean (true, if the mission is done).
     */
    void setStatus(boolean stat);

    /**
     * This method is used to check mission's status.
     * 
     * @param passedPlayer the player and everything connected to him (for example coins and inventory).
     * @return true if the mission is achieved, false otherwise.
     */
    boolean isMissionDone(Player passedPlayer);
}
