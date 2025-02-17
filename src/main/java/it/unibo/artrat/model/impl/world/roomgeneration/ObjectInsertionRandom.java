package it.unibo.artrat.model.impl.world.roomgeneration;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;
import it.unibo.artrat.model.api.world.roomgeneration.ObjectInsertionStrategy;
import it.unibo.artrat.model.impl.AbstractGameObject;

/**
 * random insertion method.
 * 
 * @param <O>
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
        while (newObjects.size() < addNumber && !exit) {
            final int x = RANDOM.nextInt(1, roomSize - 1);
            final int y = RANDOM.nextInt(1, roomSize - 1);

            if (baseRoom.stream().noneMatch((o) -> {
                return ((int) o.getPosition().getX()) == x && ((int) o.getPosition().getY()) == y;
            })) {
                newObjects.add(factored.apply(x, y));
            } else {
                exit = true;
            }
        }
        return newObjects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInsertionStrategy<O> cloneStrategy() {
        return new ObjectInsertionRandom<>();
    }

}
