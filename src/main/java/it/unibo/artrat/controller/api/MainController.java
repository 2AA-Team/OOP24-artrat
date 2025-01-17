package it.unibo.artrat.controller.api;

import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.view.api.MainView;

/**
 * Controller interface.
 */
public interface MainController {

    /**
     * Adds a new main view.
     *
     * @param newView the view to be added
     */
    void addMainView(MainView newView);

    /**
     * Gracefully quits from the application.
     */
    void quit();

    /**
     * Send the signal to his model to update.
     */
    void update();

    /**
     * set the current stage to a new stage.
     * 
     * @param newStage
     */
    void setStage(Stage newStage);

    /**
     * Send the signal to his view to redraw.
     */
    void redraw();

}
