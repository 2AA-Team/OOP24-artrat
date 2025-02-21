package it.unibo.artrat.controller.api.subcontroller;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.market.Mission;

/**
 * 
 */
public interface MissionSubController extends SubController {

    /**
     * 
     */
    void initMissionList();
 
    /**
     * 
     * @return
     */
    List<Item> redeemableMissions();
    
    /**
     * 
     * @param missionToRedeem
     * @return
     */
    boolean redeemMission(final Mission missionToRedeem);
}
