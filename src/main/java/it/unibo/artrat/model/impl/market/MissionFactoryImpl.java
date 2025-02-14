package it.unibo.artrat.model.impl.market;

import it.unibo.artrat.model.api.market.Mission;
import it.unibo.artrat.model.api.market.MissionFactory;

public class MissionFactoryImpl implements MissionFactory{
    
    @Override
    public AdvancedMission createAdvancedMission(){
        return null;
    }

    @Override
    public BaseMission createBaseMission(){
        return null; //new BaseMission();
    }
}
