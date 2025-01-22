package it.unibo.artrat.model.impl.market;

import it.unibo.artrat.model.api.market.Mission;

/**
 * An abstract mission class, used to respect DRY princible.
 */
public abstract class AbstractMissionCreator implements Mission{
    private final String name;
    private final String descr;
    private final double reward;
   // private final T category;

    public AbstractMissionCreator(String name, String descr, double reward){
        this.name = name;
        this.descr = descr;
        this.reward = reward;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public String getDescr(){
        return this.descr;
    }

    @Override
    public double getReward(){
        return this.reward;
    }


    public abstract boolean isDone();

}