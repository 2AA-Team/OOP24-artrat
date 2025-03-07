package it.unibo.artrat.utils.api;

import java.io.IOException;
import java.io.InputStream;

/**
 * ResourceLoader is responsible for fetching and loading data.
 * 
 * @param <I> input type
 * @param <O> output type
 */
public interface ResourceLoader<I, O> {

    /**
     * Get the configuration object.
     * 
     * @param conf config file
     * @return configuration object
     */
    O getConfig(I conf);

    /**
     * method that load all configPath data.
     * 
     * @param configPath path of the config file
     * @throws IOException if configPath not represent anything
     */
    void setConfigPath(InputStream configPath) throws IOException;

    /**
     * get the clone of resource loader.
     * 
     * @return the clone of the resource loader
     */
    ResourceLoader<I, O> getClone();
}
