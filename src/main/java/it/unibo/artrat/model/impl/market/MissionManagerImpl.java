package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.artrat.model.api.market.Mission;
import it.unibo.artrat.model.api.market.Mission.MissionCategory;
import it.unibo.artrat.model.api.market.MissionManager;

/**
 * Mission manager class carries out all the common management logics of a market with a large quantity 
 * of missions with different categories, difficulties and rewards.
 * @author Manuel Benagli
 */
public class MissionManagerImpl implements MissionManager{
    private final List<AbstractMissionCreator> missions;

    public MissionManagerImpl(){
        this.missions = new ArrayList<>();
    }

    /**
     * @return all the mission sorted in base of their reward.
    */
    @Override
    public List<Mission> sortRewardMission(){
        return missions.stream()
            .sorted(Comparator.comparing(Mission::getReward))
            .collect(Collectors.toList());
    }

    /**
     * @return all the missions sorted by creasing difficulty 
     */
    @Override
    public List<AdvancedMission> sortDifficultyMission(){
        return this.missions.stream()
            .filter(el -> el instanceof AdvancedMission)
            .map(el -> (AdvancedMission) el)            //essendo AdvancedMission un elemento dell'abstract si può fare, vedere i test
            .sorted(Comparator.comparing(AdvancedMission::getDifficulty))
            .collect(Collectors.toList());
    }

    /**
     * @return all the missions sorted by decreasing difficulty
     */
    @Override
    public List<AdvancedMission> reverseSortDifficultyMission(){
        return this.missions.stream()
            .filter(el -> el instanceof AdvancedMission)
            .map(el -> (AdvancedMission) el)
            .sorted(Comparator.comparing(AdvancedMission::getDifficulty).reversed())
            .collect(Collectors.toList());
    } 

    // IL FILTRAGGIO SI PUO' GENERALIZZARE
    @Override
    public List<BaseMission> filterBaseMissions(){
        return this.missions.stream()
            .filter(el -> el instanceof BaseMission)
            .map(el -> (BaseMission) el)
            .collect(Collectors.toList());
    }

    //PER ADVANCE MISSION POI AGGIUNGERE IL FATTO CHE AD OGNI CLICK TI FILTRA CATEGORIE DIVERSE (opzionale mettere una tendina)
    @Override
    public List<AdvancedMission> filterAdvancedMissions(){
        return this.missions.stream()
            .filter(el -> el instanceof AdvancedMission)
            .map(el -> (AdvancedMission) el)
            .collect(Collectors.toList());
    }

    @Override       //forse questa no, si fa prima a filtrare le advanced ad ogni click una categoria diversa forse
    public List<AdvancedMission> filterCategory(MissionCategory category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filterCategory'");
    }
    
}

