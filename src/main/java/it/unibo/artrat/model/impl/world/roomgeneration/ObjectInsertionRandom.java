package it.unibo.artrat.model.impl.world.roomgeneration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import it.unibo.artrat.model.api.world.roomgeneration.ObjectInsertionStrategy;
import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.utils.impl.Point;

/**
 * random insertion method.
 * 
 * @param <O> type of the object to add
 */
public class ObjectInsertionRandom<O> implements ObjectInsertionStrategy<O> {

    private static final Random RANDOM = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<O> insertMultipleObject(
            final Set<AbstractGameObject> baseRoom,
            final int roomSize,
            final int addNumber,
            final BiFunction<Integer, Integer, O> factored) {
        final Set<O> newObjects = new HashSet<>();
        boolean exit = false;
        final List<Point> freePoints = getFreePoint(baseRoom, roomSize);
        while (newObjects.size() < addNumber && !exit) {
            if (!freePoints.isEmpty()) {
                final Point tmp = freePoints.get(RANDOM.nextInt(freePoints.size()));
                newObjects.add(factored.apply((int) tmp.getX(), (int) tmp.getY()));
                freePoints.remove(tmp);
            } else {
                exit = true;
            }
        }
        return newObjects;
    }

    /**
     * method used to search for free points in a room.
     * 
     * @param baseRoom base room set
     * @param roomSize room size
     * @return a modificable list of free Point
     */
    private List<Point> getFreePoint(final Set<AbstractGameObject> baseRoom, final int roomSize) {
        return new ArrayList<>(IntStream.rangeClosed(0, roomSize - 1)
                .boxed()
                .flatMap(x -> IntStream.rangeClosed(0, roomSize - 1)
                        .mapToObj(y -> new Point(x, y)))
                .filter((x) -> !baseRoom.stream().anyMatch((o) -> o.getPosition().equals(x)))
                .toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInsertionStrategy<O> cloneStrategy() {
        return new ObjectInsertionRandom<>();
    }

}
