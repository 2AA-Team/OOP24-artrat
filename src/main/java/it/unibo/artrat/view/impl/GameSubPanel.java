package it.unibo.artrat.view.impl;

import it.unibo.artrat.controller.api.subcontroller.GameSubController;

/**
 * game sub panel class.
 */
public class GameSubPanel extends AbstractSubPanel {
    private final GameSubController gameSubController;

    /**
     * constructor for game sub panel.
     * 
     * @param gameSubController sub controller for the game
     */
    public GameSubPanel(GameSubController gameSubController) {
        this.gameSubController = gameSubController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponents() {
        forceRedraw();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void forceRedraw() {
        this.gameSubController.getVisibleWallPositions();
    }
}
