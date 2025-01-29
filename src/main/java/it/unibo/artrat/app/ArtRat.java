package it.unibo.artrat.app;

import it.unibo.artrat.model.api.world.floorGeneration.RoomGeneration;
import it.unibo.artrat.model.impl.world.floorGeneration.FileRoomGeneration;

/**
 * Class that rappresents the whole application and starts the game engine.
 * 
 * @author Matteo Tonelli
 */
public final class ArtRat {
    /**
     * private constructor.
     */
    private ArtRat() {
    }

    /**
     * Main method that starts the application.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final Runnable gameEngine = new GameEngineImpl();
        RoomGeneration gen = new FileRoomGeneration("src/main/java/it/unibo/artrat/resources/premadeMaze/mazes.yaml");
        gameEngine.run();
    }
}
