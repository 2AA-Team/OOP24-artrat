package it.unibo.artrat.view.impl;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.artrat.controller.api.subcontroller.MenuSubController;
import it.unibo.artrat.model.impl.Stage;

/**
 * empty sub panel for test purpose.
 */
public class EmptySubPanel extends AbstractSubPanel {

    private final MenuSubController menuSubController;

    /**
     * constructor to set the sub controller of the sub panel.
     * 
     * @param menuSubController sub controller
     */
    public EmptySubPanel(final MenuSubController menuSubController) {
        this.menuSubController = menuSubController;
    }

    /**
     * contructor only for test purpose.
     */
    public EmptySubPanel() {
        this.menuSubController = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponents() {
        final JPanel panel = new JPanel();
        final JButton btn = new JButton();
        btn.addActionListener((e) -> {
            this.menuSubController.setStage(Stage.MENU);
        });
        panel.add(btn);
        setPanel(panel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void forceRedraw() {
    }

}
