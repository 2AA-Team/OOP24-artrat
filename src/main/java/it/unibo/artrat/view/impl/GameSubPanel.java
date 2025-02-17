package it.unibo.artrat.view.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.artrat.controller.api.subcontroller.FloorSubController;

/**
 * sub panel class.
 */
public class GameSubPanel extends AbstractSubPanel implements KeyListener {

    private final FloorSubController floorController;
    private static final int UP = KeyEvent.VK_W;
    private static final int DOWN = KeyEvent.VK_S;
    private static final int LEFT = KeyEvent.VK_A;
    private static final int RIGHT = KeyEvent.VK_D;

    public GameSubPanel(final FloorSubController controller) {
        this.floorController = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponents() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void forceRedraw() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}
