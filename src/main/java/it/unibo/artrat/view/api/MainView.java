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
     * This method is called before the UI is used. It should finalize its status.
     * (if needed).
     */
    void initiate();

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

}
