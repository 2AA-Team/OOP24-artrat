package it.unibo.artrat.model.api.missioncenter;

import it.unibo.artrat.model.api.characters.Player;

/**
 * Mission base interface.
 * @author Manuel Benagli
 */
public interface Mission {

    /**
     * 
     * @return
     */
    String getName();

    /**
     * 
     * @return
     */
    String getText();


    /**
     * 
     */
    boolean isMissionDone(Player passedPlayer);
}
