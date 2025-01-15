package it.unibo.artrat.utils;

/**
 * ResourceLoader is responsible for fetching and loading data.
 */
public interface ResourceLoader {

    /**
     * Get the configuration object.
     * 
     * @param conf config file
     * @return configuration object
     */
    Object getConfig(String conf);

}
