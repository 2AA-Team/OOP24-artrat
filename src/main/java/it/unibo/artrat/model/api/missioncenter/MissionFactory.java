package it.unibo.artrat.model.api.missioncenter;

/**
 * MissionFactory interface.
 */
public interface MissionFactory {

    /**
     * This method initializes
     */
    void initialize();

    /**
     * @return a new mission instance, houdini.
     */
    Mission houdini();

    /**
     * @return a new mission instance, culturalBaggage.
     */
    Mission culturalBaggage();
}
