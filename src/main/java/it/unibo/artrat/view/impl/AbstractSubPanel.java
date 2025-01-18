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
    private JPanel panel;
    private MainController controller;

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
     * set the current panel.
     * This approach applies the concept of 'information hiding'.
     * 
     * @param newPanel
     */
    protected void setPanel(final JPanel newPanel) {
        panel = newPanel;
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

    /**
     * this method return the controller to use in the panel.
     * 
     * @return subController
     */
    protected MainController getSubController() {
        return controller;
    }

}
