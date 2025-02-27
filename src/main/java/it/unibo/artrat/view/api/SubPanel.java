package it.unibo.artrat.view.api;

import java.awt.Dimension;

import javax.swing.JPanel;

public interface SubPanel {
    /**
     * Method to set the master frame dimension.
     * 
     * @param frameDim dimension of the frame
     */
    public void setFrameDimension(final Dimension frameDim);

    /**
     * A method to obtain the current dimension of the frame.
     * 
     * @return the dimension of the frame.
     */
    public Dimension getFrameDimension();

    /**
     * Getter for the jpanel.
     * 
     * @return his own starter panel.
     */
    public JPanel getPanel();

    /**
     * set a new Jpanel.
     * 
     * @param newPanel panel to set
     */
    public void setPanel(final JPanel newPanel);

    /**
     * initializes the panel components.
     */
    public abstract void initComponents();

    /**
     * force to update all his component.
     */
    public abstract void forceRedraw();
}
