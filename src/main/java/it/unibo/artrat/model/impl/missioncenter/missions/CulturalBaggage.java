package it.unibo.artrat.model.impl.missioncenter.missions;

import it.unibo.artrat.model.impl.missioncenter.AbstractMission;

import it.unibo.artrat.model.api.characters.Player;


/**
 * @author Manuel Benagli
 */
public class CulturalBaggage extends AbstractMission {

    /**
     * @param name
     * @param desc
     */
    public CulturalBaggage(final String name, final String descr) {
        super(name, descr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone(Player passedPlayer) {
        if (passedPlayer.getInventory().getStoredItem().size() >= 4) {
            return true;
        }
        return false;
    }
}
