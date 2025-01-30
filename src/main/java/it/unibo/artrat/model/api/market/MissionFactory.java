package it.unibo.artrat.model.api.market;

import it.unibo.artrat.model.impl.market.AdvancedMission;
import it.unibo.artrat.model.impl.market.BaseMission;

/** 
 *  An interface used to create new missions
 *  Every mission has its own difficulty, an higher one gives more points
 */
public interface MissionFactory {

    BaseMission createBaseMission();        //returns a base mission
    AdvancedMission createAdvancedMission();            //returns an advanced mission
    /*
    TimeChallengeMission createtimeChallengeMission();
    GatheringMission createGatheringMission();
    StealthMission createStealthMission();
    */

    enum MissionCategory{  //la categoria p
        TIMECHALLENGE, GATHERING, STEALTH
    };

}
