package it.unibo.artrat.controller.api.subcontroller;

import java.util.List;
import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.market.Mission;

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
}
