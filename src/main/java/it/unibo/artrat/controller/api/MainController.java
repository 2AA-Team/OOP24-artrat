package it.unibo.artrat.controller.api;

import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.view.api.MainView;

/**
 * Controller interface.
 * 
 * @author Matteo Tonelli
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

    /**
     * get the manager of all sub controllers of the main controller.
     * 
     * @return sub controller manager
     */
    SubControllerManager getControllerManager();

    /**
     * interface to describe basic subController.
     */
    interface SubController {
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

}
