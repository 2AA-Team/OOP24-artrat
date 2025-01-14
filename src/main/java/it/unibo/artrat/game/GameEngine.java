package it.unibo.artrat.game;

import java.io.File;
import java.io.FileNotFoundException;

import it.unibo.artrat.utils.Converter;
import it.unibo.artrat.utils.ResourceLoader;
import it.unibo.artrat.utils.ResourceLoaderImpl;

/**
 * GameEngine is the class designed to manage the game loop
 */
public class GameEngine implements Runnable {

    private enum GameStatus {
        STOPPED, RUNNING
    }

    private final String configPath = "src" + File.separator
            + "main" + File.separator
            + "java" + File.separator
            + "it" + File.separator
            + "unibo" + File.separator
            + "artrat" + File.separator
            + "resources" + File.separator
            + "config" + File.separator
            + "config.yaml";
    private GameStatus status;
    private ResourceLoader resourceLoader;

    public GameEngine() {
        this.status = GameStatus.STOPPED;
    }

    @Override
    public void run() {
        this.status = GameStatus.RUNNING;
        mainLoop();
    }

    private void mainLoop() {
        if (InitiateResources() != 0) {
            System.exit(1);
        }
        double drawInterval = Converter.fpsToNanos((int) resourceLoader.getConfig("FPS"));
        double delta = 0;
        double lastTime = System.nanoTime();
        long currentTime;
        while (status.equals(GameStatus.RUNNING)) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                this.update();
                this.render();
                delta--;
            }
        }
    }

    private int InitiateResources() {
        try {
            resourceLoader = new ResourceLoaderImpl(configPath);
            return 0;
        } catch (FileNotFoundException e) {
            return 1;
        }
    }

    private void render() {
        System.out.println("render");
    }

    private void update() {
        System.out.println("update");
    }

}
