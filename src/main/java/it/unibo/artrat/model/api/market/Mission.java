package it.unibo.artrat.model.api.market;

/**
 * Interface which represents missions.
 */
public interface Mission {

    /**
     * s.
     */
    enum MissionCategory {
        /**
         * s
         */
        TIMECHALLENGE, 
        /**
         * s
        */
        GATHERING
    };

    /**
     * 
     * @return s.
     */
    boolean isDone();

    /**
     * 
     * @return s.
     */
    String getDescr();

    /**
     * 
     * @return s.
     */
    String getName();

    /**
     * 
     * @return
     */
    double getReward();
    //T getCategory();      mi dice a che categoria appartiene, se Stealth, base eccetera
}
