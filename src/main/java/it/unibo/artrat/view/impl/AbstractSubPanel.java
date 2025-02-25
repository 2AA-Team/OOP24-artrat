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
     * Method to set the master frame dimension.
     * 
     * @param frameDim dimension of the frame
     */
    public void setFrameDimension(final Dimension frameDim) {
        this.frameDimension = frameDim;
    }

    /**
     * A method to obtain the current dimension of the frame.
     * @return the dimension of the frame.
     */
    protected Dimension getFrameDimension() {
        return this.frameDimension;
    }

    /**
     * Getter for the jpanel.
     * 
     * @return his own starter panel.
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * set a new Jpanel.
     * 
     * @param newPanel panel to set
     */
    protected void setPanel(final JPanel newPanel) {
        this.panel = newPanel;
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
