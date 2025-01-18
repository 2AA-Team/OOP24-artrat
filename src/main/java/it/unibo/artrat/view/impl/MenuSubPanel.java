package it.unibo.artrat.view.impl;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.impl.Stage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * panel for the initial Menu.
 * 
 * @author Matteo Tonelli
 */
public class MenuSubPanel extends AbstractSubPanel {

    /**
     * construct the panel to add at the mainView.
     */
    @Override
    public void initComponents() {
        final int verticalInsets = 10;
        final int orizzontalInsets = 20;
        final JPanel panel = new JPanel();
        final MainController controller = getSubController();
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(verticalInsets, orizzontalInsets, verticalInsets, orizzontalInsets);
        panel.revalidate();
        panel.repaint();
        final JButton jbGame = new JButton("Game");
        jbGame.addActionListener((e) -> {
            controller.setStage(Stage.GAME);
        });
        panel.add(jbGame, gbc);

        gbc.gridy = 1;
        final JButton jbShop = new JButton("Shop");
        jbShop.addActionListener((e) -> {
            controller.setStage(Stage.STORE);
        });
        panel.add(jbShop, gbc);

        gbc.gridy = 2;
        final JButton jbExit = new JButton("Exit");
        jbExit.addActionListener((e) -> {
            controller.quit();
        });
        panel.add(jbExit, gbc);
        setPanel(panel);
    }

}
