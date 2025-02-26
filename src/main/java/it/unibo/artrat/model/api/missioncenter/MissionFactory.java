package it.unibo.artrat.model.api.missioncenter;

import java.io.IOException;

/**
 * MissionFactory interface.
 * 
 * @author Manuel Benagli
 */
public interface MissionFactory {

    /**
     * This method initializes MissionFactory using MissionReader.
     * 
     * @throws IOException IOException.
     */
    void initialize() throws IOException;

    /**
     * TheRatOfWallStreet mission.
     * 
     * @return a new mission instance, houdini.
     */
    Mission theRatOfWallStreet();

    /**
     * CulturalBaggage mission.
     * 
     * @return a new mission instance, culturalBaggage.
     */
    Mission culturalBaggage();

    /**
     * RatRace mission.
     * 
     * @return a new mission instance, ratRace
     */
    Mission ratRace();
}
