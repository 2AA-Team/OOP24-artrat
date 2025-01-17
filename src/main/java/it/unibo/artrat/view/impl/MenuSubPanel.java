package it.unibo.artrat.view.impl;

import javax.swing.JButton;

import it.unibo.artrat.model.impl.Stage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * panel for the initial Menu.
 */
public class MenuSubView extends AbstractSubPanel {

    /**
     * construct the panel to add at the mainView.
     */
    @Override
    public void initComponents() {
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 20, 10, 20);
        panel.revalidate();
        panel.repaint();
        final JButton jbGame = new JButton("Game");
        jbGame.addActionListener((e) -> {
            controller.setStage(Stage.GAME);
        });
        panel.add(jbGame, gbc);

        gbc.gridy = 1; // Secondo pulsante
        final JButton jbShop = new JButton("Shop");
        jbShop.addActionListener((e) -> {
            controller.setStage(Stage.STORE);
        });
        panel.add(jbShop, gbc);

        gbc.gridy = 2; // Terzo pulsante
        final JButton jbExit = new JButton("Exit");
        jbExit.addActionListener((e) -> {
            controller.quit();
        });
        panel.add(jbExit, gbc);
    }

}
