package it.unibo.artrat.app.api;

import it.unibo.artrat.utils.api.commands.Sender;

import it.unibo.artrat.utils.api.ResourceLoader;

/**
 * GameEngine is the class designed to manage the game loop.
 */
public interface GameEngine extends Runnable, Sender {
    /**
     * chenge the status to stop the gameloop.
     */
    void forceStop();

    /**
     * chenge the status to start the gameloop.
     */
    void forceStart();

    /**
     * getter for the resource loader.
     * 
     * @return the main resource loader
     */
    ResourceLoader<String, Double> getResourceLoader();
}
