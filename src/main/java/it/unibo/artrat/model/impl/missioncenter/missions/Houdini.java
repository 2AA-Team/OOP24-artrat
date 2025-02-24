package it.unibo.artrat.model.impl.missioncenter.missions;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.impl.missioncenter.AbstractMission;


/**
 * Houdini mission.
 * 
 * @author Manuel Benagli
 */
public class Houdini extends AbstractMission {
    private static final double COINS = 10;

    /**
     * @param name mission's name.
     * @param descr mission's goal to achieve.
     */
    public Houdini(final String name, final String descr) {
        super(name, descr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone(final Player passedPlayer) {
        if (passedPlayer.getCoin().getCurrentAmount() > COINS ) {
            return true;
        }
        return false;
    }
}
