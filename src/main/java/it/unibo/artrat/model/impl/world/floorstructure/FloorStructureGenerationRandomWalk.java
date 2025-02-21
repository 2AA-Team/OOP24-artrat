package it.unibo.artrat.model.impl.world.floorstructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import it.unibo.artrat.model.api.world.floorstructure.FloorStructureGenerationStrategy;

public class FloorStructureGenerationRandomWalk implements FloorStructureGenerationStrategy {

    private final int startX;
    private final int startY;
    private final Random RANDOM = new Random();

    /**
     * Constructor to define the start room.
     * 
     * @param x start room x coordinate
     * @param y start room y coordinate
     */
    public FloorStructureGenerationRandomWalk(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<Boolean>> generate(int size) {
        List<List<Boolean>> main = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            main.add(new ArrayList<>(Collections.nCopies(size, false)));
        }
        int x = startX;
        int y = startY;
        main.get(x).set(y, true);
        int steps = size * size / 2;
        ;
        for (int i = 0; i < steps; i++) {
            int direction = RANDOM.nextInt(4); // 0 = up, 1 = down, 2 = left, 3 = right

            switch (direction) {
                case 0:
                    if (y > 0)
                        y--;
                    break;
                case 1:
                    if (y < size - 1)
                        y++;
                    break;
                case 2:
                    if (x > 0)
                        x--;
                    break;
                case 3:
                    if (x < size - 1)
                        x++;
                    break;
            }
            main.get(x).set(y, true);
        }

        return main;
    }
}
