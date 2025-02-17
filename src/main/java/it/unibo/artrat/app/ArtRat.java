package it.unibo.artrat.app;

import java.io.IOException;

import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.world.FloorImpl;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

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
        // try {
        // final Runnable gameEngine = new GameEngineImpl();
        // gameEngine.run();
        // } catch (IOException e) {
        // // TODO: handle exception
        // }
        ResourceLoaderImpl<String, Double> rl = new ResourceLoaderImpl<>();
        try {
            rl.setConfigPath("/home/rufio/OOP24-artrat/src/main/java/it/unibo/artrat/resources/config/config.yaml");

            FloorImpl f = new FloorImpl(rl);
            f.generateFloorSet();
            f.print();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
