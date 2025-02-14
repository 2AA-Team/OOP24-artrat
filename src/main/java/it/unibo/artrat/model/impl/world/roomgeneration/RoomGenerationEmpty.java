package it.unibo.artrat.model.impl.world.roomgeneration;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.GameObjectFactoryImpl;

/**
 * An implementation of RoomGenerationStrategy.
 */
public class RoomGenerationEmpty implements RoomGenerationStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> generateRoomSet(final int size) {
        final GameObjectFactory factory = new GameObjectFactoryImpl();
        return IntStream.range(0, size)
                .boxed()
                .flatMap(i -> IntStream.range(0, size)
                        .filter(j -> i == 0 || j == 0 || i == size - 1 || j == size - 1)
                        .mapToObj(j -> factory.getWall(i, j)))
                .collect(Collectors.toSet());
    }

}
