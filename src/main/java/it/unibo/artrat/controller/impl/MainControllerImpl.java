package it.unibo.artrat.controller.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.view.api.MainView;

/**
 * 
 */
public class MainControllerImpl implements MainController {

    private Stage currentStage;
    private final List<MainView> views = new ArrayList<>(0);

    public MainControllerImpl() {
        this.currentStage = Stage.MENU;
    }

    @Override
    public void addMainView(MainView newView) {
        views.add(newView);
        newView.setController(this);
        newView.setStage(currentStage);
        newView.initiate();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    @Override
    public void update() {
        for (MainView mainView : views) {
            mainView.SetContent("");
        }
    }

}
