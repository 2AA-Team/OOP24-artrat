package it.unibo.artrat.model.impl.missioncenter;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.missioncenter.Mission;

/**
 * Abstract mission class.
 * 
 * @author Manuel Benagli
 */
public abstract class AbstractMission implements Mission {
    private final String missionText;
    private final String name;

    /**
     * AbstractMission constructor.
     * 
     * @param name mission's name.
     * @param missionText mission's task to accomplish.
     */
    protected AbstractMission(final String name, final String missionText) {
        this.name = name;
        this.missionText = missionText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(){
        return this.missionText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean isMissionDone(Player passedPlayer);
}
