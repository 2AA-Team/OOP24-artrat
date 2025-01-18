package it.unibo.artrat.view.impl;

import javax.swing.JPanel;

/**
 * empty sub panel for test purpose.
 */
public class EmptySubPanel extends AbstractSubPanel {

    /**
     * construct the panel to add at the mainView.
     */
    @Override
    public void initComponents() {
        setPanel(new JPanel());
    }

}
