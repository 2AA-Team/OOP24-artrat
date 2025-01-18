package it.unibo.artrat.controller.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.app.api.GameEngine;
import it.unibo.artrat.controller.api.MainController;
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

    /**
     * MainController constructor.
     * set the current Stage to the initial menu
     * 
     * @param engine game engine.
     */
    public MainControllerImpl(final GameEngine engine) {
        this.currentStage = Stage.MENU;
        this.engine = engine;
    }

    /**
     * Add a new view connected to the application.
     */
    @Override
    public void addMainView(final MainView newView) {
        views.add(newView);
        newView.setController(this);
        newView.setStage(currentStage);
        newView.initiate();
    }

    /**
     * Gracefully quits from the application.
     */
    @Override
    public void quit() {
        engine.forceStop();
        Runtime.getRuntime().exit(1);
    }

    /**
     * Send the signal to his model to update.
     */
    @Override
    public void update() {

    }

    /**
     * set the current stage to a new stage.
     * 
     * @param newStage
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
     * Send the signal to his view to update.
     */
    @Override
    public void redraw() {
        for (final MainView mainView : views) {
            mainView.forceRedraw();
        }
    }
}
