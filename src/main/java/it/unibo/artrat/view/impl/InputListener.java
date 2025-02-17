package it.unibo.artrat.view.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.utils.impl.commands.MoveDown;
import it.unibo.artrat.utils.impl.commands.MoveLeft;
import it.unibo.artrat.utils.impl.commands.MoveRight;
import it.unibo.artrat.utils.impl.commands.MoveStop;
import it.unibo.artrat.utils.impl.commands.MoveUp;

public class InputListener implements KeyListener {
    private final SubController controller;
    private static final int UP = KeyEvent.VK_W;
    private static final int DOWN = KeyEvent.VK_S;
    private static final int LEFT = KeyEvent.VK_A;
    private static final int RIGHT = KeyEvent.VK_D;

    public InputListener(final SubController controller) {
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
        switch (e.getKeyCode()) {
            case UP -> this.controller.inputMainController(new MoveUp());
            case DOWN -> this.controller.inputMainController(new MoveDown());
            case LEFT -> this.controller.inputMainController(new MoveLeft());
            case RIGHT -> this.controller.inputMainController(new MoveRight());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        var key = e.getKeyCode();
        if (key == UP || key == DOWN || key == LEFT || key == RIGHT) {
            this.controller.inputMainController(new MoveStop());
        }
    }

}
