package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import it.unibo.artrat.model.api.market.Mission;
import it.unibo.artrat.model.api.market.MissionManager;

/**
 * Mission manager class carries out all the common management logics of a market with a large quantity 
 * of missions with different categories, difficulties and rewards.
 * @author Manuel Benagli
 */
public class MissionManagerImpl implements MissionManager {
    private final List<AbstractMissionCreator> missions;

    /**
     * 
     */
    public MissionManagerImpl() {
        this.missions = new ArrayList<>();
    }

    /**
     * @return all the mission sorted in base of their reward.
    */
    @Override
    public List<Mission> sortRewardMission() {
        return missions.stream()
            .sorted(Comparator.comparing(Mission::getReward))
            .collect(Collectors.toList());
    }

    /**
     * 
     */
    @Override
    public List<Mission> filterMission() {
        throw new UnsupportedOperationException("Unimplemented method 'filterMission'");
    }
}

