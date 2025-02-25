package it.unibo.artrat.model.impl.missioncenter.missions;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.impl.missioncenter.AbstractMission;

/**
 * RatRace class.
 */
public class RatRace extends AbstractMission {
    private static final double MAX_SPEED = 0.02;
    private static final double EPSILON = 1e-6;

    /**
     * @param name   mission's name.
     * @param desc   mission's description.
     * @param status mission's status.
     */
    public RatRace(final String name, final String desc, final boolean status) {
        super(name, desc, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone(final Player passedPlayer) {
        if (!this.isStatusDone() && (Math.abs(MAX_SPEED - passedPlayer.getVelocity()) < EPSILON)) {
            this.setStatus(true);
        }
        return isStatusDone();
    }
}
