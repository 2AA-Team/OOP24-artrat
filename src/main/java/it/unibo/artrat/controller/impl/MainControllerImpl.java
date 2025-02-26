package it.unibo.artrat.controller.impl;

import java.io.IOException;
import java.util.ArrayList;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.artrat.app.api.GameEngine;
import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.controller.api.SubControllerManager;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.WorldTimer;
import it.unibo.artrat.model.impl.ModelImpl;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.model.impl.WorldTimerImpl;
import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.view.api.MainView;

/**
 * implementation of class MainController.
 * 
 * @author Matteo Tonelli
 */
public class MainControllerImpl implements MainController {

    private Stage currentStage;
    private MainView view;
    private final GameEngine engine;
    private final SubControllerManager subControllerManager;
    private Model model;
    private final WorldTimer timer;

    /**
     * MainController constructor.
     * set the current Stage to the initial menu
     * 
     * @param engine game engine.
     * @throws IOException if resource loader fail to load resource
     */
    public MainControllerImpl(final GameEngine engine) throws IOException {
        this.currentStage = Stage.MENU;
        this.engine = engine;
        this.view = null;
        this.model = new ModelImpl();
        this.subControllerManager = new SubControllerManagerImpl(this, engine.getResourceLoader());
        this.timer = new WorldTimerImpl();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings("EI2")
    @Override
    public void addMainView(final MainView newView) {
        this.view = newView;
        newView.setStage(currentStage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        engine.forceStop();
        Runtime.getRuntime().exit(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStage(final Stage newStage) {
        currentStage = newStage;
        view.reconduceFromStage();
        if (newStage.equals(Stage.GAME)) {
            engine.forceStart();
        } else {
            engine.forceStop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        view.forceRedraw();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubControllerManager getControllerManager() {
        return subControllerManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Model getModel() {
        return new ModelImpl(this.model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModel(final Model model) {
        if (model != null) {
            this.model = new ModelImpl(model);
        } else {
            throw new IllegalArgumentException("The new istance of model passed by the controller is null");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stage getStage() {
        return currentStage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void input(final Command cmd) {
        this.engine.notifyCommand(cmd);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startTimerMainController() {
        timer.startTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetTimerMainController() {
        timer.resetTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTimeOutMainController() {
        return timer.isTimeOut();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentTimeMainController() {
        return timer.getCurrentTime();
    }

    private void gameExit(final Player passedPlayer) {
        model.setPlayer(passedPlayer.copyPlayer());
        resetTimerMainController();
        setStage(Stage.MENU);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void winGame() {
        final Player player = getModel().getPlayer();
        final double obtainedCoins = player.obtainCollectable();
        gameExit(player);
        view.showGameResult(obtainedCoins, "VICTORY");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loseGame() {
        final Player player = model.getPlayer();
        player.setColletableList(new ArrayList<>());
        gameExit(player);
        view.showGameResult(0.0, "LOOSE");
    }

}
