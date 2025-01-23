package it.unibo.artrat.view.impl;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.artrat.model.impl.Stage;

/**
 * empty sub panel for test purpose.
 */
public class EmptySubPanel extends AbstractSubPanel {

    /**
     * construct the panel to add at the mainView.
     */
    @Override
    public void initComponents() {
        JPanel panel = new JPanel();
        JButton btn = new JButton();
        btn.addActionListener((e) -> {
            this.getSubController().setStage(Stage.MENU);
        });
        panel.add(btn);
        setPanel(panel);

    }

    @Override
    protected void forceRedraw() {
    }

}
