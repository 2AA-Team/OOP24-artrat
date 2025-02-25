package it.unibo.artrat.utils.api;

import java.util.Set;

/**
 * MissionReader base interface.
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
     * @return the names of all missions.
     */
    Set<String> getAllMissionName();
}
