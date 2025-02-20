package it.unibo.artrat.controller.impl;

import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.impl.ModelImpl;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.utils.api.commands.Command;

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
    public Model getModel() {
        return new ModelImpl(this.mainController.getModel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCentralizeModel(final Model model) {
        mainController.setModel(new ModelImpl(model));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        mainController.quit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inputMainController(final Command cmd) {
        this.mainController.input(cmd);
    }

}
