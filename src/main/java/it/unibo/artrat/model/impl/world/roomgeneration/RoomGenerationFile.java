package it.unibo.artrat.model.impl.world.roomgeneration;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import it.unibo.artrat.model.api.AbstractGameObject;
import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.GameObjectFactoryImpl;
import it.unibo.artrat.model.impl.world.RoomSymbols;
import it.unibo.artrat.utils.impl.RoomsLoader;

/**
 * room generation that take premade room from a file.
 */
public class RoomGenerationFile implements RoomGenerationStrategy {

    private final RoomsLoader rl = new RoomsLoader();

    /**
     * constructor for the room generation.
     * 
     * @param filePath path to the file containing the default mazes
     * @throws IOException if the file has any loading problem
     */
    public RoomGenerationFile(final String filePath) throws IOException {
        rl.setConfigPath(filePath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> generateRoomSet(final int size) {
        if (rl != null) {
            final GameObjectFactory factory = new GameObjectFactoryImpl();
            char[][] room;
            try {
                room = rl.getConfig(size);
            } catch (IllegalStateException e) {
                return new RoomGenerationEmpty().generateRoomSet(size);
            }
            final char[][] tmp = room;
            return IntStream.range(0, tmp.length)
                    .boxed()
                    .flatMap(i -> IntStream.range(0, tmp[i].length)
                            .filter(j -> tmp[i][j] == RoomSymbols.WALL.getSymbol())
                            .mapToObj(j -> factory.getWall(i, j)))
                    .collect(Collectors.toSet());
        } else {
            return new RoomGenerationEmpty().generateRoomSet(size);
        }
    }
}
