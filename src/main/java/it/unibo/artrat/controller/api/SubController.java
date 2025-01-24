package it.unibo.artrat.controller.api;

import it.unibo.artrat.model.impl.Stage;

/**
 * interface to describe basic subController.
 */
public interface SubController {
    /**
     * method to set the frame stage.
     * 
     * @param newStage
     */
    void setStage(Stage newStage);

    /**
     * Gracefully quits from the application.
     */
    void quit();
}