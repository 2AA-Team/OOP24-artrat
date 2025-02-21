package it.unibo.artrat.model.impl.market;

import it.unibo.artrat.model.api.market.MissionType;


/**
 * @author Manuel Benagli
 */
public class Houdini extends AbstractMission {

    /**
     * 
     * @param descr
     * @param reward
     * @param missionType
     */
    public Houdini(final String descr, final double reward, MissionType missionType) {
        super(descr, reward, missionType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone() {
        
        
        return true;
    }
}
