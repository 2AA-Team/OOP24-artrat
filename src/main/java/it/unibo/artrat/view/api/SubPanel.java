package it.unibo.artrat.view.api;

import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Subpanel interface.
 */
public interface SubPanel {
    /**
     * Method to set the master frame dimension.
     * 
     * @param frameDim dimension of the frame
     */
    void setFrameDimension(Dimension frameDim);

    /**
     * A method to obtain the current dimension of the frame.
     * 
     * @return the dimension of the frame.
     */
    Dimension getFrameDimension();

    /**
     * Getter for the jpanel.
     * 
     * @return his own starter panel.
     */
    JPanel getPanel();

    /**
     * set a new Jpanel.
     * 
     * @param newPanel panel to set
     */
    void setPanel(JPanel newPanel);

    /**
     * initializes the panel components.
     */
    void initComponents();

    /**
     * force to update all his component.
     */
    void forceRedraw();
}
