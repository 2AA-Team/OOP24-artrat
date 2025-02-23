package it.unibo.artrat.controller.impl.subcontroller;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.controller.api.subcontroller.MissionSubController;
import it.unibo.artrat.controller.impl.AbstractSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.model.api.missioncenter.Mission;

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
        this.currentMissionsList = new ArrayList<>(this.getModel().getMissionCenter().getMissionList());
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
        return false;
    }

        /**
     * {@inheritDoc}
     */
    @Override
    public String getMissionName(final Mission passedMission) {
        return this.currentMissionsList.stream()
            .filter(m -> m.equals(passedMission))
            .map(Mission::getName)
            .findAny().get().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String showDescr(final Mission passedMission) {
        return this.currentMissionsList.stream()
                .filter(m -> m.equals(passedMission))
                .map(Mission::getText)
                .findAny().get().toString();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double getMissionReward(final Mission passedMission) {
        return this.getModel().getMissionCenter().getMissionList().stream()
                .filter(m -> m.equals(passedMission))
                .map(Mission::getReward)
                .findAny().get();
    }
}
