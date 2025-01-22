package it.unibo.artrat.model.api.market;

/**
 * Interface which represents missions.
 */
public interface Mission {
    
    enum MissionCategory{  //la categoria p
        TIMECHALLENGE, GATHERING, STEALTH
    };

    boolean isDone();
    String getDescr();
    String getName();
    double getReward();
    //T getCategory();      mi dice a che categoria appartiene, se Stealth, base eccetera
}
