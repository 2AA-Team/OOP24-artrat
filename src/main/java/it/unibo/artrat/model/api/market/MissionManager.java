package it.unibo.artrat.model.api.market;

import java.util.List;

/**
 * The mission manager interface is used to handle all the missions.
 */
public interface MissionManager {

    /**
     * @return all the mission sorted in base of their reward.
    */
    List<Mission> sortRewardMission();

    /**
     * 
     * @return d
     */
    List<Mission> filterMission();
}
