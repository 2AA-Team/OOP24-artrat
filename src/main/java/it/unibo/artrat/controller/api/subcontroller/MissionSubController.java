package it.unibo.artrat.controller.api.subcontroller;

import java.util.List;
import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.missioncenter.Mission;

/**
 * MissionSubController interface.
 */
public interface MissionSubController extends SubController {

    /**
     * 
     */
    void initMissionList();
 
    /**
     * 
     * @return all the missions to redeem.
     */
    List<Mission> redeemableMissions();
    
    /**
     * 
     * @param missionToRedeem mission to redeem.
     * @return
     */
    boolean redeemMission(final Mission missionToRedeem);

    /**
     * 
     * @param passedMission
     * @return
     */
    double getMissionReward(final Mission passedMission);

    String getMissionName(final Mission passedMission);
    
    String showDescr(final Mission passedMission);
}

