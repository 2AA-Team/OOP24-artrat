package it.unibo.artrat.controller.api;

import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.utils.api.commands.Command;

/**
 * interface to describe basic subController.
 * 
 * @author Matteo Tonelli
 */
public interface SubController {

    /**
     * go to menu.
     */
    void goToMenu();

    /**
     * go to game.
     */
    void goToGame();

    /**
     * go to shop.
     */
    void goToShop();

    /**
     * go to mission.
     */
    void goToMission();

    /**
     * go to inventory.
     */
    void gotToInventory();

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

    /**
     * start the timer in controller.
     */
    void startTimerSubController();

    /**
     * reset the timer in controller.
     */
    void resetTimerSubController();

    /**
     * check if time is out.
     * 
     */
    void isTimeOutSubController();

    /**
     * getter for current time.
     * 
     * @return current time as a int
     */
    int getCurrentTimeController();
}
