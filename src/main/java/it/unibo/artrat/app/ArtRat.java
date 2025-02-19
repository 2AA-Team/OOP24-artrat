package it.unibo.artrat.app;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that rappresents the whole application and starts the game engine.
 * 
 */
public final class ArtRat {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtRat.class);

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
            new GameEngineImpl().run();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
