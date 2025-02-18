package it.unibo.artrat.app;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import it.unibo.artrat.app.api.GameEngine;
import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.Converter;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;
import it.unibo.artrat.view.impl.MainViewImpl;

/**
 * Implement game engine.
 * 
 */
public final class GameEngineImpl implements GameEngine {
    private final List<Command> commands = new LinkedList<>();

    // TODO add Room for GameObjects' access.
    private enum GameStatus {
        STOPPED, RUNNING
    }

    private final URL configPath = Thread.currentThread().getContextClassLoader().getResource(
            "config" + File.separator
                    + "config.yaml");
    private volatile GameStatus status;
    private final ResourceLoader<String, Double> resourceLoader;
    private final MainController mainController;

    /**
     * Game engine constructor.
     * 
     * @throws IOException
     */
    public GameEngineImpl() throws IOException {
        this.status = GameStatus.STOPPED;
        this.resourceLoader = new ResourceLoaderImpl<>();
        this.initiateResources();
        mainController = new MainControllerImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceLoader<String, Double> getResourceLoader() {
        return Objects.requireNonNull(this.resourceLoader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        mainController.addMainView(new MainViewImpl(resourceLoader));
    }

    /**
     * Game loop method.
     */
    private void mainLoop() {
        final long drawInterval = Converter.fpsToMillis(resourceLoader.getConfig("FPS").intValue());
        long delta;
        long lastTime;
        while (status.equals(GameStatus.RUNNING)) {
            lastTime = System.currentTimeMillis();
            this.update();
            this.redraw();
            delta = updateDeltaTime(lastTime, drawInterval);
        }
    }

    private long updateDeltaTime(final long lastFrameTime, final long drawInterval) {
        var delta = System.currentTimeMillis() - lastFrameTime;
        if (delta < drawInterval) {
            try {
                Thread.sleep(drawInterval - delta);
            } catch (InterruptedException e) {
            }
        }

        return System.currentTimeMillis() - lastFrameTime;
    }

    /**
     * Init resources.
     * 
     * @return true if initialization done succesfully false otherwise
     */
    private boolean initiateResources() {
        try {
            resourceLoader.setConfigPath(configPath.toURI());
            return true;
        } catch (IOException | URISyntaxException e) {
            return false;
        }
    }

    private void redraw() {
        // System.err.println("Updating view");
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
        Thread.ofPlatform().daemon().start(this::mainLoop);
    }

    @Override
    public void notifyCommand(final Command cmd) {
        commands.add(cmd);
    }
}
