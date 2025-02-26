package it.unibo.artrat.model.impl.missioncenter.missions;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.impl.missioncenter.AbstractMission;

/**
 * Houdini mission.
 * 
 * @author Manuel Benagli
 */
public class TheRatOfWallStreet extends AbstractMission {
    private static final double COINS = 10.00;

    /**
     * TheRatOfWallStreet constructor.
     * 
     * @param name mission's name.
     * @param desc mission's goal to achieve.
     * @param status true if the mission's done, false otherwise.
     */
    public TheRatOfWallStreet(final String name, final String desc, final boolean status) {
        super(name, desc, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone(final Player passedPlayer) {
        if (!this.getStatus() && passedPlayer.getCoin().getCurrentAmount() >= COINS) {
            this.setStatus(true);
        }
        return getStatus();
    }
}
