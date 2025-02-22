package it.unibo.artrat.model.impl.missioncenter;

import it.unibo.artrat.model.api.missioncenter.MissionType;

/**
 * @author Manuel Benagli
 */
public class CulturalBaggage extends AbstractMission {
    
    /**
     * @param name
     * @param desc
     * @param price
     * @param missionType
     */
    public CulturalBaggage(final String name, final String descr, final double reward, final MissionType missionType) {
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
