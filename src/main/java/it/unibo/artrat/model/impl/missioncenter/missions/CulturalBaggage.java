package it.unibo.artrat.model.impl.missioncenter.missions;

import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.model.impl.missioncenter.AbstractMission;

/**
 * CulturalBaggage mission.
 * 
 * @author Manuel Benagli
 */
public class CulturalBaggage extends AbstractMission {

    /**
     * CulturalBaggage constructor.
     * 
     * @param name   mission's name.
     * @param desc   missions's goal to achieve.
     * @param status mission's status (true if done, false otherwise).
     */
    public CulturalBaggage(final String name, final String desc, final boolean status) {
        super(name, desc, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone(final Player passedPlayer) {
        if (!this.isStatusDone() && passedPlayer.getInventory().getStoredItem().size() >= 4) {
            setStatus(true);
        }
        return isStatusDone();
    }
}
