package it.unibo.artrat.model.impl.missioncenter.missions;

import it.unibo.artrat.model.impl.missioncenter.AbstractMission;

import it.unibo.artrat.model.api.characters.Player;


/**
 * CulturalBaggage mission.
 * 
 * @author Manuel Benagli
 */
public class CulturalBaggage extends AbstractMission {

    /**
     * @param name mission's name.
     * @param desc missions's goal to achieve.
     */
    public CulturalBaggage(final String name, final String descr) {
        super(name, descr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone(final Player passedPlayer) {
        if (passedPlayer.getInventory().getStoredItem().size() >= 4) {
            return true;
        }
        return false;
    }
}
