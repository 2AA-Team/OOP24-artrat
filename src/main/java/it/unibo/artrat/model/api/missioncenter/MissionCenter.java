package it.unibo.artrat.model.api.missioncenter;

import java.util.List;

/**
 * MissionCenter interface.
 */
public interface MissionCenter {

    /**
     * @return a list of all the missions.
     */
    List<Mission> getMissionList();

    /**
     * Initializes the list of all the missions read by MissionReader.
     * It adds my missions (created using the private method createMission) in my list.
     * It will be used in initComponents method, inside MissionCenterSubPanel.
     */
    void initMissionCenter();
}
