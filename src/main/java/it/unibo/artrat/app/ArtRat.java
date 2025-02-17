package it.unibo.artrat.app;

import java.io.IOException;

/**
 * Class that rappresents the whole application and starts the game engine.
 * 
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
        try {
            final Runnable gameEngine = new GameEngineImpl();
            gameEngine.run();
        } catch (IOException e) {
            throw new IllegalStateException(e.getStackTrace().toString());
        }
    }
}
