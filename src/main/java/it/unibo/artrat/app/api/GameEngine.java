package it.unibo.artrat.app.api;

/**
 * GameEngine is the class designed to manage the game loop.
 */
public interface GameEngine extends Runnable {
    /**
     * chenge the status to stop the gameloop.
     */
    void forceStop();

    /**
     * chenge the status to start the gameloop.
     */
    void forceStart();
}
