package it.unibo.artrat.model.impl.missioncenter.missions;

import it.unibo.artrat.model.impl.missioncenter.AbstractMission;

import it.unibo.artrat.model.api.characters.Player;


/**
 * @author Manuel Benagli
 */
public class CulturalBaggage extends AbstractMission {
    private static final double COINS = 9999;
    private Player player;

    /**
     * @param name
     * @param desc
     * @param price
     * @param missionType
     */
    public CulturalBaggage(final String name, final String descr, final double reward) {
        super(name, descr, reward);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone() {
        if(player.getCoin().getCurrentAmount() == COINS ) {
            player.increaseCoins(COINS);
            return true;
        }
        return false;
    }
}
