package it.unibo.artrat.app;

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
     * @param args main arguments
     */
    public static void main(final String[] args) {
        final Runnable gameEngine = new GameEngineImpl();
        gameEngine.run();
    }
}
