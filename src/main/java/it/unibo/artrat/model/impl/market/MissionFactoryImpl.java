package it.unibo.artrat.model.impl.market;

import it.unibo.artrat.model.api.market.Gathering;
import it.unibo.artrat.model.api.market.Mission;
import it.unibo.artrat.model.api.market.MissionFactory;
import it.unibo.artrat.model.api.market.Stealth;
import it.unibo.artrat.model.api.market.TimeChallenge;

public class MissionFactoryImpl implements MissionFactory{
    
    @Override
    public AdvancedMission createAdvancedMission(){
        return null;
    }

    @Override
    public BaseMission createBaseMission(){
        return new BaseMission();
    }

    @Override
    public TimeChallengeMission createtimeChallengeMission() {
        return new TimeChallengeMission();
    }

    @Override
    public GatheringMission createGatheringMission() {
       return new GatheringMission();
    }

    @Override
    public StealthMission createStealthMission() {
        return new StealthMission();
    }
}
