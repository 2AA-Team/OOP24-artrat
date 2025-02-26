package it.unibo.artrat.utils.api;

import java.util.Set;

/**
 * MissionReader base interface.
 * 
 * @author Manuel Benagli
 */
public interface MissionReader extends Reader {

    /**
     * Reading mission's name.
     * 
     * @param nameOfMission the mission.
     * @return The name of the mission.
     */
    String getName(String nameOfMission);

    /**
     * Reading mission's description.
     * 
     * @param nameOfMission the name of the mission from the file.
     * @return The description of the mission.
     */
    String getDescription(String nameOfMission);

    /**
     * A set of String of all missions name.
     * 
     * @return the names of all missions.
     */
    Set<String> getAllMissionName();
}
