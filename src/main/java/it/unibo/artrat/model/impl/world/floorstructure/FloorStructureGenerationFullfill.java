package it.unibo.artrat.model.impl.world.floorstructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.artrat.model.api.world.floorstructure.FloorStructureGenerationStrategy;

public class FloorStructureGenerationFullfill implements FloorStructureGenerationStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<Boolean>> generate(int size) {
        List<List<Boolean>> main = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            main.add(new ArrayList<>(Collections.nCopies(size, true)));
        }
        return main;
    }

}
