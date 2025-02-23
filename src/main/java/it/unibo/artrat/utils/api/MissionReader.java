package it.unibo.artrat.utils.api;

import java.util.Set;

/**
 * 
 */
public interface MissionReader extends Reader {

    /**
     *
     * @param nameOfMission the mission.
     * @return The name of the mission.
     */
    String getName(String nameOfMission);

    /**
     * 
     * @param nameOfMission the name of the mission from the file.
     * @return The description of the mission.
     */
    String getDescription(String nameOfMission);

    /**
     * 
     * @param nameOfMission the mission from the file.
     * @return The reward of the mission.
     */
    double getReward(String nameOfMission);

    /**
     * 
     * @return the names of all missions.
     */
    Set<String> getAllMissionName();    
}
