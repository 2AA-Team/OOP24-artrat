package it.unibo.artrat.view.impl;

import javax.swing.JPanel;

import it.unibo.artrat.controller.api.Requester;

/**
 * abstract class to make panel.
 * each stage have his own panel.
 */
abstract class AbstractView {
    protected JPanel panel;
    protected Requester requester;

    /**
     * abstract view constructor.
     * initialize the panel
     */
    AbstractView() {
        panel = new JPanel();
    }

    /**
     * @return his own panel.
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
     * set the requester to communicate with model.
     * 
     * @param requester
     */
    public void setSubController(Requester requester) {
        this.requester = requester;
    }
}
