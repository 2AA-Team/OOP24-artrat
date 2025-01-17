package it.unibo.artrat.view.impl;

import javax.swing.JPanel;
import it.unibo.artrat.controller.api.MainController;

/**
 * abstract class to make panel.
 * each stage have his own panel.
 * 
 * @author Matteo Tonelli
 */
abstract class AbstractSubPanel {
    protected final JPanel panel;
    protected MainController controller;

    /**
     * abstract view constructor.
     * initialize the panel
     */
    AbstractSubPanel() {
        panel = new JPanel();
    }

    /**
     * @return his own starter panel.
     */
    public JPanel getPanel() {
        initComponents();
        return panel;
    }

    /**
     * initializes the panel components.
     */
    public abstract void initComponents();

    /**
     * set the controller to communicate with model.
     * 
     * @param controller
     */
    public void setSubController(final MainController controller) {
        this.controller = controller;
    }
}
