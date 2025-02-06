package it.unibo.artrat.app;

import java.io.IOException;

import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.world.FloorImpl;

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
     * @param args ignore
     */
    public static void main(final String[] args) {
        // final Runnable gameEngine = new GameEngineImpl();
        // gameEngine.run();
        try {
            Floor floor = new FloorImpl("src/main/java/it/unibo/artrat/resources/config/config.yaml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
