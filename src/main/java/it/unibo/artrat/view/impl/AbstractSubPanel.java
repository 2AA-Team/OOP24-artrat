package it.unibo.artrat.view.impl;

import javax.swing.JPanel;

/**
 * abstract class to make panel.
 * each stage have his own panel.
 * 
 * @author Matteo Tonelli
 */
abstract class AbstractSubPanel {
    private JPanel panel;

    /**
     * abstract view constructor.
     * initialize the panel
     */
    AbstractSubPanel() {
        panel = new JPanel();
    }

    /**
     * getter for the jpanel.
     * 
     * @return his own starter panel.
     */
    public JPanel getPanel() {
        initComponents();
        return panel;
    }

    /**
     * set a new Jpanel.
     * 
     * @param newPanel panel to set
     */
    protected void setPanel(final JPanel newPanel) {
        panel = newPanel;
    }

    /**
     * initializes the panel components.
     */
    public abstract void initComponents();

    /**
     * force to update all his component.
     */
    protected abstract void forceRedraw();

}
