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
     * Send the signal to his view to update.
     * The command is used to use in frame.
     */
    void update();

    /**
     * @return subcontroller.
     */
    Requester getRequester();

}
