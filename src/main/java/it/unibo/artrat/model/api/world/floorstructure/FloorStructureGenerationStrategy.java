package it.unibo.artrat.model.api.world.floorstructure;

import java.util.List;

public interface FloorStructureGenerationStrategy {

    List<List<Boolean>> generate(final int size);
}
