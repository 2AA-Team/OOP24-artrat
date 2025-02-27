package it.unibo.artrat.view.impl;

import java.awt.Dimension;

import javax.swing.JPanel;

import it.unibo.artrat.view.api.SubPanel;

/**
 * abstract class to make panel.
 * each stage have his own panel.
 * 
 * @author Matteo Tonelli
 */
abstract class AbstractSubPanel implements SubPanel {
    private JPanel panel;
    private Dimension frameDimension;

    /**
     * {@inheritDoc}
     */
    AbstractSubPanel() {
        panel = new JPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFrameDimension(final Dimension frameDim) {
        this.frameDimension = frameDim;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getFrameDimension() {
        return this.frameDimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return panel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPanel(final JPanel newPanel) {
        this.panel = newPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void initComponents();

    /**
     * {@inheritDoc}
     */
    @Override
    public void forceRedraw() {

    }

}
