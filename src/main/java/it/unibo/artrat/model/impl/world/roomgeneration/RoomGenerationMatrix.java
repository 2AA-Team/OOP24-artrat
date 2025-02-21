package it.unibo.artrat.model.impl.world.roomgeneration;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.GameObjectFactoryImpl;

/**
 * room generation based on a matrix calculation.
 * useful for generating a room with block-shaped obstacles
 */
public class RoomGenerationMatrix implements RoomGenerationStrategy {

    private static final Random RANDOM = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> generateRoomSet(final int size) {
        final GameObjectFactory factory = new GameObjectFactoryImpl();
        final List<Boolean> probabilities = List.of(
                true, true, true, true, false);
        final Set<Integer> column = new HashSet<>();
        final Set<Integer> rows = new HashSet<>();
        while (column.isEmpty() || rows.isEmpty()) {
            for (int i = 1; i < size - 1; i++) {
                if (probabilities.get(RANDOM.nextInt(probabilities.size()))) {
                    column.add(i);
                }
                if (probabilities.get(RANDOM.nextInt(probabilities.size()))) {
                    rows.add(i);
                }
            }
        }
        final Stream<GameObject> border = new RoomGenerationEmpty().generateRoomSet(size).stream();
        final Stream<GameObject> matrix = IntStream.range(0, size - 2).filter(rows::contains)
                .boxed()
                .flatMap(i -> IntStream.range(0, size - 2).mapToObj(j -> factory.getWall(i, j))
                        .filter(y -> column.contains((int) y.getPosition().getY())));
        return Stream.concat(border, matrix).collect(Collectors.toSet());
    }
}
