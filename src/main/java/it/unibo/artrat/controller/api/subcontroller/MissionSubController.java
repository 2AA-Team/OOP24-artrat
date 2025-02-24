package it.unibo.artrat.controller.api.subcontroller;

import java.util.List;
import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.missioncenter.Mission;

/**
 * MissionSubController interface.
 */
public interface MissionSubController extends SubController {

    /**
     * This method initializes the list of all the missions.
     * The list is read by MissionReader as soon as we enter in MissionCenterSubPanel.
     */
    void initMissionList();
 
    /**
     * 
     * @return a list of all the missions to achieve.
     */
    List<Mission> redeemableMissions();

    /**
     * 
     * @param missionToRedeem mission to redeem.
     * @return true if the mission is completed, false otherwise.
     */
    boolean redeemMission(final Mission missionToRedeem);

    /**
     * 
     * @param passedMission the passed mission.
     * @return a Stirng which shows the passed mission's name.
     */
    String getMissionName(final Mission passedMission);
    
    /**
     * 
     * @param passedMission the passed mission.
     * @return a String which shows the passed mission's description.
     */
    String showDescr(final Mission passedMission);
}

