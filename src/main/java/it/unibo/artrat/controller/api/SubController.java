package it.unibo.artrat.controller.api;

import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.utils.api.commands.Command;

/**
 * interface to describe basic subController.
 */
public interface SubController {
    /**
     * method to set the frame stage.
     * 
     * @param newStage new stage to set
     */
    void setStage(Stage newStage);

    /**
     * Gracefully quits from the application.
     */
    void quit();

    /**
     * Method that allows obtaining a copy of the current instance of the model.
     * 
     * @return a copy of the current istance of method.
     */
    Model getModel();

    /**
     * Method allows to notifying the main controller that something.
     * has changed and that it needs to update the centralized model with passed
     * version.
     * 
     * @param model the new istance of model to set.
     */
    void updateCentralizeModel(Model model);

    /**
     * Sends to main controller the input.
     *
     * @param cmd command to execute
     */
    void inputMainController(Command cmd);


    void startTimerSubController();
    void resetTimerSubController();
    void stopTimerSubController();
    int getCurrentTimeController();
}
