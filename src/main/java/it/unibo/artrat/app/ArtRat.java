package it.unibo.artrat.app;

import it.unibo.artrat.model.impl.GameEngine;

public class ArtRat {
    public static void main(String[] args) {
        Runnable gameEngine = new GameEngine();
        gameEngine.run();
    }
}
