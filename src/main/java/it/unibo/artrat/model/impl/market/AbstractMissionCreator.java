package it.unibo.artrat.model.impl.market;

import it.unibo.artrat.model.api.market.Mission;

/**
 * An abstract mission class, used to respect DRY princible.
 * @author Manuel Benagli
 */
public abstract class AbstractMissionCreator implements Mission {
    private final String name;
    private final String descr;
    private final double reward;

    /**
    * @param name n
    * @param descr d
    * @param reward r
    */
    public AbstractMissionCreator(final String name, final String descr, final double reward) {
        this.name = name;
        this.descr = descr;
        this.reward = reward;
    }

    /**
     * s.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * s.
     */
    @Override
    public String getDescr() {
        return this.descr;
    }

    /**
     * s.
     */
    @Override
    public double getReward() {
        return this.reward;
    }

    /**
     * s.
     */
    @Override
    public abstract boolean isDone();

}
