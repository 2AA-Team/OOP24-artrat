package it.unibo.artrat.model.api.market;

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
