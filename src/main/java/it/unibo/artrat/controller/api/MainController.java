package it.unibo.artrat.controller.api;

import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.utils.api.commands.Command;
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
     * set the current stage to a new stage.
     * 
     * @param newStage new stage
     */
    void setStage(Stage newStage);

    /**
     * Send the signal to his view to redraw.
     */
    void redraw();

    /**
     * A method that return a copy of the current model.
     * 
     * @return a copy of current Model.
     */
    Model getModel();

    /**
     * A method that permit to reset the current istance of Model with a new one,
     * passed.
     * 
     * @param model the new Model istance to set.
     */
    void setModel(Model model);

    /**
     * get the manager of all sub controllers of the main controller.
     * 
     * @return sub controller manager
     */
    SubControllerManager getControllerManager();

    /**
     * getter for current stage.
     * 
     * @return the current stage
     */
    Stage getStage();

    /**
     * sends to engine the command.
     * 
     * @param cmd
     */
    void input(Command cmd);

    /**
     * This method calls startTimer in WorldTimerImpl (Model).
     * 
     * @author Manuel Benagli
     */
    void startTimerMainController();

    /**
     * This method calls resetTimer in WorldTimerImpl (Model).
     * 
     * @author Manuel Benagli
     */
    void resetTimerMainController();

    /**
     * This method calls getCurrentTime in WorldTimerImpl (Model).
     * 
     * @return the countdown every second.
     * @author Manuel Benagli
     */
    int getCurrentTimeMainController();

    /**
     * check if time is out.
     * 
     * @return true if is time out
     */
    boolean isTimeOutMainController();

    /**
     * Method to handle all the logic related to victory.
     */
    void winGame();

    /**
     * Method to handle all the logic related to defeat.
     */
    void loseGame();

}
