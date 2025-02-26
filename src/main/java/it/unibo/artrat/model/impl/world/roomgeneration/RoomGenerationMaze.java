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
import it.unibo.artrat.utils.impl.Point;

/**
 * room generation based on DFS with backtracking.
 */
public class RoomGenerationMaze implements RoomGenerationStrategy {

    private final GameObjectFactory factory = new GameObjectFactoryImpl();
    private static final Random RANDOM = new Random();
    private Set<GameObject> maze = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> generateRoomSet(final int size) {
        final Set<GameObject> border = new RoomGenerationEmpty().generateRoomSet(size);
        final Set<GameObject> allFilledSet = fullFilled(size).collect(Collectors.toSet());
        maze = allFilledSet.stream()
                .filter(x -> x.getPosition().getX() % 2 == 0 || x.getPosition().getY() % 2 == 0)
                .collect(Collectors.toSet());
        final List<Point> toVisit = allFilledSet.stream()
                .filter(x -> !maze.contains(x))
                .map(GameObject::getPosition)
                .collect(Collectors.toList());
        if (!toVisit.isEmpty()) {
            final Point currentPoint = toVisit.get(RANDOM.nextInt(toVisit.size()));
            createMaze(toVisit, currentPoint);
        }
        return Stream.concat(maze.stream(), border.stream()).collect(Collectors.toSet());
    }

    private void createMaze(final List<Point> toVisit, final Point currentPoint) {
        Point currentPointcopy = currentPoint;
        if (currentPointcopy == null) {
            currentPointcopy = toVisit.remove(RANDOM.nextInt(toVisit.size()));
        }
        final List<Point> stack = new java.util.ArrayList<>();
        stack.add(currentPointcopy);
        while (!stack.isEmpty()) {
            final Point tmp = stack.get(stack.size() - 1);
            final List<Point> neighbours = toVisit.stream()
                    .filter(p -> p.getEuclideanDistance(tmp) == 2)
                    .collect(Collectors.toList());
            if (neighbours.isEmpty()) {
                stack.remove(stack.size() - 1);
            } else {
                final Point next = neighbours.get(RANDOM.nextInt(neighbours.size()));
                toVisit.remove(next);
                final int midX = (int) (next.getX() + tmp.getX()) / 2;
                final int midY = (int) (next.getY() + tmp.getY()) / 2;
                maze.removeIf(x -> x.getPosition().equals(new Point(midX, midY)));
                stack.add(next);
            }
        }
    }

    private Stream<GameObject> fullFilled(final int size) {
        return IntStream.range(1, size - 1)
                .boxed()
                .flatMap(i -> IntStream.range(1, size - 1)
                        .mapToObj(j -> factory.getWall(i, j)));
    }
}
