package it.unibo.artrat.model.impl.missioncenter.missions;

import it.unibo.artrat.model.impl.missioncenter.AbstractMission;
import it.unibo.artrat.view.api.MainView;


/**
 * @author Manuel Benagli
 */
public class Houdini extends AbstractMission {
    private MainView view;

    /**
     * @param name
     * @param descr
     * @param reward
     * @param missionType
     */
    public Houdini(final String name, final String descr, final double reward) {
        super(name, descr, reward);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone() {
        
        return true;
    }
}
