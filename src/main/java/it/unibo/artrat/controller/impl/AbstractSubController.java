package it.unibo.artrat.controller.impl;


import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.utils.api.observers.Observer;

/**
 * abstract class that implements subcontroller.
 */
public abstract class AbstractSubController implements SubController,Observer {
    private final MainControllerImpl mainController;
    private Model model;

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

    public MainController getMainController() {
        return this.mainController;
    }

    public Model getModel() {
        return this.model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        mainController.quit();
    }

    @Override
    public void update(Model o) {
        this.model = o;
    }

    
}
