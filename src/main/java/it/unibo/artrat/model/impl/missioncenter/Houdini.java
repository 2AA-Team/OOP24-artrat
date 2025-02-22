package it.unibo.artrat.model.impl.missioncenter;

import it.unibo.artrat.model.api.missioncenter.MissionType;


/**
 * @author Manuel Benagli
 */
public class Houdini extends AbstractMission {

    /**
     * @param name
     * @param descr
     * @param reward
     * @param missionType
     */
    public Houdini(final String name, final String descr, final double reward, MissionType missionType) {
        super(name, descr, reward, missionType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone() {
        
        
        return true;
    }
}
