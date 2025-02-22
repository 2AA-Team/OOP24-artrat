package it.unibo.artrat.model.api.missioncenter;

/**
 * 
 */
public interface MissionFactory {
    
    /**
     * 
     */
    void initialize();

    /**
     * 
     * @return
     */
    Mission houdini();

    /**
     * 
     * @return
     */
    Mission culturalBaggage();
}
