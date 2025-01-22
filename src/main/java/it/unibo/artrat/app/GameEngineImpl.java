package it.unibo.artrat.app;

import java.io.File;
import java.io.IOException;

import it.unibo.artrat.app.api.GameEngine;
import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.Converter;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;
import it.unibo.artrat.view.impl.MainViewImpl;

/**
 * Implement game engine.
 * 
 * @author Matteo Tonelli
 */
public final class GameEngineImpl implements GameEngine {

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
    private final ResourceLoader resourceLoader;
    private final MainController mainController;

    /**
     * Game engine constructor.
     */
    public GameEngineImpl() {
        this.status = GameStatus.STOPPED;
        this.resourceLoader = new ResourceLoaderImpl();
        mainController = new MainControllerImpl(this);
    }

    @Override
    public void run() {
        if (!initiateResources()) {
            Runtime.getRuntime().exit(1);
        }
        mainController.addMainView(new MainViewImpl(
                (int) resourceLoader.getConfig("WIDTH"),
                (int) resourceLoader.getConfig("HEIGHT")));
        mainLoop();
    }

    /**
     * Game loop method.
     */
    private void mainLoop() {
        while (true) {
            final double drawInterval = Converter.fpsToNanos((int) resourceLoader.getConfig("FPS"));
            double delta = 0;
            double lastTime = System.nanoTime();
            long currentTime;
            while (status.equals(GameStatus.RUNNING)) {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                lastTime = currentTime;
                if (delta >= 1) {
                    this.update();
                    this.redraw();
                    delta--;
                }
            }
        }

    }

    /**
     * Init resources.
     * 
     * @return true if initialization done succesfully false otherwise
     */
    private boolean initiateResources() {
        try {
            resourceLoader.setConfigPath(configPath);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void redraw() {
        mainController.redraw();
    }

    private void update() {

    }

    /**
     * chenge the status to stop the gameloop.
     */
    @Override
    public void forceStop() {
        this.status = GameStatus.STOPPED;
    }

    /**
     * chenge the status to start the gameloop.
     */
    @Override
    public void forceStart() {
        this.status = GameStatus.RUNNING;
    }
}
