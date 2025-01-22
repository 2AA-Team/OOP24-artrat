package it.unibo.artrat.model.api.market;

import java.util.List;
import java.util.Locale.Category;

import it.unibo.artrat.model.api.market.Mission.MissionCategory;
import it.unibo.artrat.model.impl.market.AdvancedMission;
import it.unibo.artrat.model.impl.market.BaseMission;

/**
 * The mission manager interface is used to handle all the missions.
 */
public interface MissionManager {

    /**
     * @return all the mission sorted in base of their reward.
    */
    List<Mission> sortRewardMission();            //non so se usare mission, o Abstractmission o Advanced/base mission

    /**
     * 
     * @return all the missions sorted by creasing difficulty\  
     */
    List<AdvancedMission> sortDifficultyMission();
    
    List<AdvancedMission> reverseSortDifficultyMission();

    /**
     * @param category a specific category
     * @return  missions filtered in base of the selected category.
     */
    List<AdvancedMission> filterCategory(MissionCategory category);

    List<BaseMission> filterBaseMissions();

    List<AdvancedMission> filterAdvancedMissions();
    

}
