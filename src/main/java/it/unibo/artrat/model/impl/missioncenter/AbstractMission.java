package it.unibo.artrat.model.impl.missioncenter;

import it.unibo.artrat.model.api.missioncenter.Mission;
import it.unibo.artrat.model.impl.characters.characters.Player;

/**
 * Abstract mission class.
 * 
 * @author Manuel Benagli
 */
public abstract class AbstractMission implements Mission {
    private final String missionText;
    private final String name;
    private boolean status;

    /**
     * AbstractMission constructor.
     * 
     * @param name        mission's name.
     * @param missionText mission's task to accomplish.
     * @param status      true if mission's done, false otherwise.
     */
    protected AbstractMission(final String name, final String missionText, final boolean status) {
        this.name = name;
        this.missionText = missionText;
        this.status = status;
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
    public boolean isStatusDone() {
        return this.status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(final boolean stat) {
        this.status = stat;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return this.missionText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean isMissionDone(Player passedPlayer);
}
