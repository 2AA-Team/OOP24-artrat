package it.unibo.artrat.app;

import it.unibo.artrat.model.impl.GameEngine;

/**
 * Class that rappresents the whole application and starts the game engine.
 */
public class ArtRat {
    private ArtRat() {
    }

    public static void main(String[] args) {
        final Runnable gameEngine = new GameEngine();
        gameEngine.run();
    }
}
