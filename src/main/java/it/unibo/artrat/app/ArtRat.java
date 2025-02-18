package it.unibo.artrat.app;

import java.io.IOException;

/**
 * Class that rappresents the whole application and starts the game engine.
 * 
 */
public class ArtRat {

    /**
     * Main method that starts the application.
     * 
     * @param args ignore
     */
    public static void main(final String[] args) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        try {
            System.out.println("Starting");
            new GameEngineImpl().run();
        } catch (IOException e) {
            throw new IllegalStateException(e.getStackTrace().toString());
        }
    }
}
