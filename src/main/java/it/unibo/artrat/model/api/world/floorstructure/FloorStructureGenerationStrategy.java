package it.unibo.artrat.model.api.world.floorstructure;

import java.util.List;

public interface FloorStructureGenerationStrategy {

    public List<List<Boolean>> generate(int size);
}
