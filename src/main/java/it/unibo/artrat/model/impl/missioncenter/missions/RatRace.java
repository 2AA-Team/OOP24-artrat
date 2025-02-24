package it.unibo.artrat.model.impl.missioncenter.missions;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.impl.missioncenter.AbstractMission;

/**
 * RatRace class.
 */
public class RatRace extends AbstractMission {
    private static final double MAX_SPEED = 0.02;
    
    /**
     * @param name mission's name.
     * @param desc mission's description.
     * @param status mission's status.
     */
    public RatRace(final String name, final String desc, final boolean status) {
        super(name,desc,status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone(final Player passedPlayer) {
        if (!this.getStatus()) {
            if (passedPlayer.getVelocity() == MAX_SPEED) {
                this.setStatus(true);
            }
        }
        return getStatus();
    }
}
