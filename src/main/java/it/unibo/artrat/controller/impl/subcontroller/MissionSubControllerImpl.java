package it.unibo.artrat.controller.impl.subcontroller;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.controller.api.subcontroller.MissionSubController;
import it.unibo.artrat.controller.impl.AbstractSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.missioncenter.Mission;

/**
 * MissionSubController implementation class.
 */
public class MissionSubControllerImpl extends AbstractSubController implements MissionSubController {
    private List<Mission> currentMissionsList = new ArrayList<>();

    /**
     * MissionSubController constructor.
     * @param mainController
     */
    public MissionSubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initMissionList() {
        this.currentMissionsList = new ArrayList<>(this.getModel().getMissions());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Mission> missionList() {
        return new ArrayList<>(currentMissionsList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone(final Mission missionToRedeem) {
        final boolean check = missionToRedeem.isMissionDone(this.getModel().getPlayer());
        final Model model = this.getModel();
        model.setMissions(new ArrayList<>(currentMissionsList));
        this.updateCentralizeModel(model);
        return check;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMissionName(final Mission passedMission) {
        return this.currentMissionsList.stream()
            .filter(m -> m.equals(passedMission))
            .map(Mission::getName)
            .findAny().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String showDescr(final Mission passedMission) {
        return this.currentMissionsList.stream()
            .filter(m -> m.equals(passedMission))
            .map(Mission::getText)
            .findAny().get();
    }
}
