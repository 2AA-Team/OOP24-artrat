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

    public EmptySubPanel(MenuSubController menuSubController) {
        this.menuSubController = menuSubController;
    }

    public EmptySubPanel() {
        this.menuSubController = null;
    }

    /**
     * construct the panel to add at the mainView.
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

    @Override
    protected void forceRedraw() {
    }

}
