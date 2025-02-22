package it.unibo.artrat.model.api.missioncenter;

import java.util.List;

public interface MissionCenter {
    
    /**
     * 
     * @return a list of all the missions.
     */
    List<Mission> getMissionList();

    /**
     * 
     */
    void setMissionList(List<Mission> missions);

    /**
     * 
     * @param passedMission
     * @return
     */
    boolean redeemMission(Mission passedMission);

    /**
     * 
     */
    void initMissionCenter();
}
