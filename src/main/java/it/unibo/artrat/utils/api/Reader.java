package it.unibo.artrat.utils.api;

import java.io.IOException;
import java.io.InputStream;

/**
 * An interface that models a reader allowing data to be loaded from a file.
 * 
 * @author Cristian Di Donato.
 */
public interface Reader {
    /**
     * Method that load all itemPath data.
     * 
     * @param itemPath file that contains all item.
     * @throws IOException if configPath not represent anything:
     */
    void setPath(InputStream itemPath) throws IOException;
}
