package it.unibo.artrat.model.impl.market;

import it.unibo.artrat.model.api.market.Mission;

public class BaseMission extends AbstractMissionCreator{

    public BaseMission(String name, String descr, double reward){
        super(name, descr, reward);
    }

    @Override
    public boolean isDone(){
        return false;
    }
}