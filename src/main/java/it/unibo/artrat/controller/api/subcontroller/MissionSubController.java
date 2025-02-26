package it.unibo.artrat.controller.api.subcontroller;

import java.util.List;
import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.missioncenter.Mission;

/**
 * MissionSubController interface.
 * 
 * @author Manuel Benagli
 */
public interface MissionSubController extends SubController {

    /**
     * This method initializes the list of all the missions.
     * The list is read by MissionReader as soon as we enter in MissionCenterSubPanel.
     */
    void initMissionList();
 
    /**
     * missionList, a list of mission read by MissionReader.
     * 
     * @return a list of all the missions to achieve.
     */
    List<Mission> missionList();

    /**
     * isMissionDone method.
     * 
     * @param missionToRedeem mission to redeem.
     * @return true if the mission is completed, false otherwise.
     */
    boolean isMissionDone(Mission missionToRedeem);

    /**
     * getMissionName method.
     * 
     * @param passedMission the passed mission.
     * @return a Stirng which shows the passed mission's name.
     */
    String getMissionName(Mission passedMission);

    /**
     * showDescr method.
     * 
     * @param passedMission the passed mission.
     * @return a String which shows the passed mission's description.
     */
    String getMissionDescr(Mission passedMission);
}
