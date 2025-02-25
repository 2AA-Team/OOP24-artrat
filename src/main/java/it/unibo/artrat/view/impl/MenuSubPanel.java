package it.unibo.artrat.view.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.artrat.controller.api.subcontroller.MenuSubController;

/**
 * panel for the initial Menu.
 * 
 * @author Matteo Tonelli
 */
public class MenuSubPanel extends AbstractSubPanel {

    private final MenuSubController menuSubController;

    /**
     * constructor to set the sub controller of the sub panel.
     * 
     * @param menuSubController sub controller
     */
    public MenuSubPanel(final MenuSubController menuSubController) {
        this.menuSubController = menuSubController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponents() {
        final int verticalInsets = 10;
        final int orizzontalInsets = 20;
        final JPanel panel = new JPanel();
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
            menuSubController.goToGame();
        });
        panel.add(jbGame, gbc);

        gbc.gridy = 1;
        final JButton jbShop = new JButton("Shop");
        jbShop.addActionListener((e) -> {
            menuSubController.goToShop();
        });
        panel.add(jbShop, gbc);

        gbc.gridy = 2;
        final JButton jbInvetory = new JButton("Inventory");
        jbInvetory.addActionListener((e) -> {
            menuSubController.gotToInventory();
        });
        panel.add(jbInvetory, gbc);

        gbc.gridy = 3;
        final JButton jbMissions = new JButton("Missions");
        jbMissions.addActionListener((e) -> {
            menuSubController.goToMission();
        });
        panel.add(jbMissions, gbc);

        gbc.gridy = 4;
        final JButton jbExit = new JButton("Exit");
        jbExit.addActionListener((e) -> {
            menuSubController.quit();
        });
        panel.add(jbExit, gbc);
        setPanel(panel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void forceRedraw() {
    }

}
