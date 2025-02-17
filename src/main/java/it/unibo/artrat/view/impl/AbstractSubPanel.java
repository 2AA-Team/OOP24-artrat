package it.unibo.artrat.view.impl;

import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * abstract class to make panel.
 * each stage have his own panel.
 * 
 * @author Matteo Tonelli
 */
abstract class AbstractSubPanel {
    private JPanel panel;
    private Dimension frameDimension;

    /**
     * abstract view constructor.
     * initialize the panel
     */
    AbstractSubPanel() {
        panel = new JPanel();
    }

    /**
     * method to set the master frame dimension.
     * 
     * @param frameDim dimension of the frame
     */
    public void setFrameDimension(final Dimension frameDim) {
        this.frameDimension = frameDim;
    }

    protected Dimension getFrameDimension() {
        return this.frameDimension;
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
