package it.unibo.artrat.model.api.world.floorstructure;

import java.util.List;

/**
 * strategy to describe floor strutcure of rooms generation.
 * 
 * @author Matteo Tonelli
 */
public interface FloorStructureGenerationStrategy {

    /**
     * generate the floor structure.
     * 
     * @param size of the floor
     * @return a kind of matrix as a list of boolean list (true = valid room)
     */
    List<List<Boolean>> generate(int size);
}
