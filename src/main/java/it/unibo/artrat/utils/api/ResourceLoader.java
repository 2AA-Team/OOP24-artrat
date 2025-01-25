package it.unibo.artrat.utils.api;

import java.io.IOException;

/**
 * ResourceLoader is responsible for fetching and loading data.
 * 
 * @author Matteo Tonelli
 */
public interface ResourceLoader {

    /**
     * Get the configuration object.
     * 
     * @param conf config file
     * @return configuration object
     */
    Object getConfig(String conf);

    /**
     * method that load all configPath data.
     * 
     * @param configPath
     *                   if configPath not represent anything:
     * @throws IOException
     */
    void setConfigPath(String configPath) throws IOException;

}
