package it.unibo.artrat.app;

/**
 * Class that rappresents the whole application and starts the game engine.
 */
public final class ArtRat {
    /**
     * 
     */
    private ArtRat() {
    }

    /**
     * Main method that starts the application.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final Runnable gameEngine = new GameEngine();
        gameEngine.run();
    }
}
