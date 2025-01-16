package it.unibo.artrat.view.api;

import it.unibo.artrat.controller.api.MainController;

/**
 * mainView interface.
 * a mainview is the view capable of alternating the various stages.
 */
public interface MainView {

    /**
     * Sets the controller controlled by this view (if works as input).
     *
     * @param observer the controller to attach
     */
    void setController(MainController observer);

    /**
     * This method is called before the UI is used. It should finalize its status.
     * (if needed).
     */
    void initiate();

}
