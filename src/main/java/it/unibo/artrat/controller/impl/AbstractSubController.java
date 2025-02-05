package it.unibo.artrat.controller.impl;

import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.impl.Stage;

/**
 * abstract class that implements subcontroller.
 */
public abstract class AbstractSubController implements SubController {
    private final MainControllerImpl mainController;

    /**
     * constructor to define main controller.
     * 
     * @param mainController main controller
     */
    protected AbstractSubController(final MainControllerImpl mainController) {
        this.mainController = mainController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStage(final Stage newStage) {
        mainController.setStage(newStage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        mainController.quit();
    }
}
