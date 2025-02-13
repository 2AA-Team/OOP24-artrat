package it.unibo.artrat.controller.api.subcontroller;

import it.unibo.artrat.model.impl.Stage;

/**
 * interface to describe basic subController.
 */
public interface StageSubController {
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