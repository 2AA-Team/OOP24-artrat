package it.unibo.artrat.model.impl.market;

import it.unibo.artrat.model.api.market.MissionType;

/**
 * @author Manuel Benagli
 */
public class CulturalBaggage extends AbstractMission {
    
    /**
     * 
     * @param desc
     * @param price
     * @param missionType
     */
    public CulturalBaggage(final String descr, final double reward, final MissionType missionType) {
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
