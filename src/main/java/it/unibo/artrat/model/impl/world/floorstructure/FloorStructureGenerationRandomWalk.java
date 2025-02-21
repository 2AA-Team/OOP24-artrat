package it.unibo.artrat.model.impl.world.floorstructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import it.unibo.artrat.model.api.world.floorstructure.FloorStructureGenerationStrategy;
import it.unibo.artrat.utils.impl.Point;

public class FloorStructureGenerationRandomWalk implements FloorStructureGenerationStrategy {

    private final int startX;
    private final int startY;
    private final static Random RANDOM = new Random();

    /**
     * Constructor to define the start room.
     * 
     * @param x start room x coordinate
     * @param y start room y coordinate
     */
    public FloorStructureGenerationRandomWalk(final int x, final int y) {
        this.startX = x;
        this.startY = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<Boolean>> generate(final int size) {
        final List<Point> directions = List.of(
                new Point(1, 0),
                new Point(-1, 0),
                new Point(0, 1),
                new Point(0, -1));
        final List<List<Boolean>> main = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            main.add(new ArrayList<>(Collections.nCopies(size, false)));
        }
        int x = startX;
        int y = startY;
        main.get(x).set(y, true);
        final int steps = size * size / 2;
        for (int i = 0; i < steps; i++) {
            Point direction;
            do {
                direction = directions.get(RANDOM.nextInt(directions.size()));
            } while (x + direction.getX() < 0 || y + direction.getY() < 0 || x + direction.getX() >= size
                    || y + +direction.getY() >= size);
            x += direction.getX();
            y += direction.getY();
            main.get(x).set(y, true);
        }
        return main;
    }
}
