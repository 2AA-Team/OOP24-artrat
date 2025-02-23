package it.unibo.artrat.model.api.missioncenter;

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
     * @return
     */
    double getReward();

    /**
     * 
     */
    boolean isMissionDone();
}
