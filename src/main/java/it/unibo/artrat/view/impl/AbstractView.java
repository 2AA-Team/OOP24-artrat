package it.unibo.artrat.view.impl;

import javax.swing.JPanel;

/**
 * abstract class to make panel.
 * each stage have his own panel.
 */
abstract class AbstractView {
    protected JPanel panel;

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
}
