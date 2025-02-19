package it.unibo.artrat.model.impl.world.roomgeneration;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.model.impl.GameObjectFactoryImpl;
import it.unibo.artrat.utils.impl.Point;

public class RoomGenerationMaze implements RoomGenerationStrategy {

    private final GameObjectFactory factory = new GameObjectFactoryImpl();
    private static final Random RANDOM = new Random();
    private Set<AbstractGameObject> maze;

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> generateRoomSet(final int size) {
        Set<AbstractGameObject> border = new RoomGenerationEmpty().generateRoomSet(size);
        // Converte lo stream in un Set per riutilizzarlo più volte
        Set<AbstractGameObject> allFilledSet = fullFilled(size).collect(Collectors.toSet());
        // Filtra per creare il labirinto (muri esterni)
        maze = allFilledSet.stream()
                .filter(x -> x.getPosition().getX() % 2 == 0 || x.getPosition().getY() % 2 == 0)
                .collect(Collectors.toSet());

        List<Point> visited = allFilledSet.stream()
                .filter(x -> !maze.contains(x))
                .map(AbstractGameObject::getPosition)
                .collect(Collectors.toList());

        if (!visited.isEmpty()) {
            Point currentPoint = visited.get(RANDOM.nextInt(visited.size()));
            createMaze(visited, currentPoint);
        }
        return Stream.concat(maze.stream(), border.stream()).collect(Collectors.toSet());
    }

    private void createMaze(List<Point> visited, Point currentPoint) {
        if (visited.isEmpty()) {
            return;
        }
        if (currentPoint == null) {
            currentPoint = visited.remove(RANDOM.nextInt(visited.size()));
        }
        List<Point> stack = new java.util.ArrayList<>();
        stack.add(currentPoint);
        while (!stack.isEmpty()) {
            Point tmp = stack.get(stack.size() - 1);
            List<Point> neighbours = visited.stream()
                    .filter(p -> p.getDistance(tmp) == 2)
                    .collect(Collectors.toList());
            if (neighbours.isEmpty()) {
                stack.remove(stack.size() - 1);
            } else {
                Point next = neighbours.get(RANDOM.nextInt(neighbours.size()));
                visited.remove(next);
                int midX = (int) (next.getX() + tmp.getX()) / 2;
                int midY = (int) (next.getY() + tmp.getY()) / 2;
                maze.removeIf(x -> x.getPosition().equals(new Point(midX, midY)));
                stack.add(next);
            }
        }
    }

    private Stream<AbstractGameObject> fullFilled(int size) {
        return IntStream.range(1, size - 1)
                .boxed()
                .flatMap(i -> IntStream.range(1, size - 1)
                        .mapToObj(j -> factory.getWall(i, j)));
    }
}
