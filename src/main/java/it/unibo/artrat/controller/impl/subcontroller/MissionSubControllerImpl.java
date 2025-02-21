package it.unibo.artrat.controller.impl.subcontroller;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.controller.api.subcontroller.MissionSubController;
import it.unibo.artrat.controller.impl.AbstractSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.model.api.market.Mission;

/**
 * 
 */
public class MissionSubControllerImpl extends AbstractSubController implements MissionSubController{
    private List<Mission> currentMissionsList = new ArrayList<>();

    /**
     * 
     * @param mainController
     */
    public MissionSubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
    }
    
    /**
     * 
     */
    @Override
    public void initMissionList() {
      this.currentMissionsList = new ArrayList<>(this.getModel().getMarket().getMissionList());
    }

    /**
     * 
     */
    @Override
    public List<Mission> redeemableMissions() {
        return new ArrayList<>(currentMissionsList);
    }

    /**
     * 
     */
    @Override
    public boolean redeemMission(final Mission missionToRedeem) { 
        return true;
    }
}
