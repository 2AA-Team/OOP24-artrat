package it.unibo.artrat.model.api.world;

import java.io.IOException;

/**
 * interface for the floor.
 */
public interface Floor {

    /**
     * generate a pseudo-random floor as a room matrix.
     * 
     * @throws IOException if room generation file doesn't exist
     */
    void generateFloorSet() throws IOException;

}
