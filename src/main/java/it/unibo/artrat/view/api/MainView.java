package it.unibo.artrat.view.api;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.impl.Stage;

/**
 * mainView interface.
 * a mainview is the view capable of alternating the various stages.
 * 
 * @author Matteo Tonelli
 */
public interface MainView {

    /**
     * Sets the controller controlled by this view (if works as input).
     *
     * @param observer the controller to attach
     */
    void setController(MainController observer);

    /**
     * This method is used to set the stage in a view.
     * 
     * @param currentStage new stage to set
     */
    void setStage(Stage currentStage);

    /**
     * force to update all his component.
     */
    void forceRedraw();

    /**
     * translate from stage to his relative JPanel.
     */
    void reconduceFromStage();

    /**
     * Displays in the view where the player is located the match result and the points obtained.
     * @param point The final points obtained.
     * @param state The final result of the game.
     */
    void showGameResult(double point, String state);
}
