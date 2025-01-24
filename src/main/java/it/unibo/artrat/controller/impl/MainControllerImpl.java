package it.unibo.artrat.controller.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.app.api.GameEngine;
import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.controller.api.SubControllerManager;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.view.api.MainView;

/**
 * implementation of class MainController.
 * 
 * @author Matteo Tonelli
 */
public class MainControllerImpl implements MainController {

    private Stage currentStage;
    private final List<MainView> views = new ArrayList<>(0);
    private final GameEngine engine;
    private final SubControllerManager subControllerManager;

    /**
     * MainController constructor.
     * set the current Stage to the initial menu
     * 
     * @param engine game engine.
     */
    public MainControllerImpl(final GameEngine engine) {
        this.currentStage = Stage.MENU;
        this.engine = engine;
        this.subControllerManager = new SubControllerManagerImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMainView(final MainView newView) {
        views.add(newView);
        newView.setController(this);
        newView.setStage(currentStage);
        newView.initiate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        engine.forceStop();
        Runtime.getRuntime().exit(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStage(final Stage newStage) {
        currentStage = newStage;
        if (newStage.equals(Stage.GAME)) {
            engine.forceStart();
        } else {
            engine.forceStop();
        }
        for (final MainView mainView : views) {
            mainView.setStage(currentStage);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        for (final MainView mainView : views) {
            mainView.forceRedraw();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubControllerManager getControllerManager() {
        return subControllerManager;
    }

}
