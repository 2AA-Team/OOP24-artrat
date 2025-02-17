package it.unibo.artrat.app.api;

import it.unibo.artrat.utils.api.commands.Sender;

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
}
