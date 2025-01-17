package it.unibo.artrat.controller.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.view.api.MainView;

/**
 * implementation of class MainController.
 */
public class MainControllerImpl implements MainController {

    private Stage currentStage;
    private final List<MainView> views = new ArrayList<>(0);

    /**
     * MainController constructor.
     * set the current Stage to the initial menu
     */
    public MainControllerImpl() {
        this.currentStage = Stage.MENU;
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
        System.exit(0);
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
