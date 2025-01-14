package it.unibo.artrat.game;

public class ArtRat {
    public static void main(String[] args) {
        Runnable gameEngine = new GameEngine();
        gameEngine.run();
    }
}
