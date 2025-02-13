package it.unibo.artrat.model.impl.world.roomgeneration;

import java.io.IOException;

import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.utils.impl.RoomsLoader;

public class RoomGenerationFile implements RoomGenerationStrategy {

    RoomsLoader rl = null;

    public RoomGenerationFile(String filePath) throws IOException {
        rl = new RoomsLoader();
        rl.setConfigPath(filePath);
    }

    @Override
    public char[][] generateCharRoom(int size) {
        if (rl != null) {
            return rl.getConfig(size);
        } else {
            return new char[0][0];
        }
    }
}
