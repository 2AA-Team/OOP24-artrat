package it.unibo.artrat.view.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import it.unibo.artrat.controller.api.subcontroller.GameSubController;
import it.unibo.artrat.utils.impl.commands.MoveDown;
import it.unibo.artrat.utils.impl.commands.MoveLeft;
import it.unibo.artrat.utils.impl.commands.MoveRight;
import it.unibo.artrat.utils.impl.commands.MoveUp;

/**
 * game sub panel class.
 */
public class GameSubPanel extends AbstractSubPanel {
    private final GameSubController gameSubController;
    private final JPanel mainPanel = new JPanel();
    private final InputListener commands;

    /**
     * constructor for game sub panel.
     * 
     * @param gameSubController sub controller for the game
     */
    public GameSubPanel(final GameSubController gameSubController) {
        this.gameSubController = gameSubController;
        this.mainPanel.setFocusable(true);
        this.mainPanel.requestFocus();
        this.mainPanel.addKeyListener(new KeyListener() {

            private static final int UP = KeyEvent.VK_W;
            private static final int DOWN = KeyEvent.VK_S;
            private static final int LEFT = KeyEvent.VK_A;
            private static final int RIGHT = KeyEvent.VK_D;

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyChar());
                switch (e.getKeyCode()) {
                    case UP -> gameSubController.inputMainController(new MoveUp());
                    case DOWN -> gameSubController.inputMainController(new MoveDown());
                    case LEFT -> gameSubController.inputMainController(new MoveLeft());
                    case RIGHT -> gameSubController.inputMainController(new MoveRight());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                var key = e.getKeyCode();
                if (key == UP || key == DOWN || key == LEFT || key == RIGHT) {
                    // gameSubController.inputMainController(new MoveStop());
                }
            }

        });

        commands = new InputListener(gameSubController);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void initComponents() {
        final JPanel panel = new JPanel();

        setPanel(mainPanel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forceRedraw() {
        System.out.println(this.gameSubController.getPlayerPos());
    }

}
