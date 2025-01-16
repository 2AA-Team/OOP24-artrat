package it.unibo.artrat.controller.api;

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
}
