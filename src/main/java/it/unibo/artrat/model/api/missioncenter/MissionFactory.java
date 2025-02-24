package it.unibo.artrat.model.api.missioncenter;

import java.io.IOException;

/**
 * MissionFactory interface.
 */
public interface MissionFactory {

    /**
     * This method initializes MissionFactory using MissionReader.
     * @throws IOException
     */
    void initialize() throws IOException;

    /**
     * @return a new mission instance, houdini.
     */
    Mission theRatOfWallStreet();

    /**
     * @return a new mission instance, culturalBaggage.
     */
    Mission culturalBaggage();

    /**
     * 
     * @return a new mission instance, ratRace
     */
    Mission ratRace();
}
