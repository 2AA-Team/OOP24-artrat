package it.unibo.artrat.model.api.market;

/**
 * Mission base interface.
 * @author Manuel Benagli
 */
public interface Mission {

    /**
     * 
     * @return
     */
    String getText();

    /**
     * 
     * @return
     */
    double getReward();
    
    /**
     * 
     * @return the type of a mission.
     */
    MissionType getMissionType();

    /**
     * 
     */
    boolean isMissionDone();
}
