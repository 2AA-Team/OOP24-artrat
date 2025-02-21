package it.unibo.artrat.view.impl;

import java.awt.*;
import javax.swing.*;

import it.unibo.artrat.controller.api.subcontroller.MissionSubController;
import it.unibo.artrat.model.impl.Stage;

/**
 * MissionSubPanel, we enter here from the market.
 */
public class MissionSubPanel extends AbstractSubPanel {
    private final MissionSubController missionControl;
    private JPanel missionPanel = new JPanel();
    private final JPanel contMissionPane = new JPanel(new BorderLayout());
    private final JScrollPane scrollPanel = new JScrollPane(missionPanel);

    /**
     * Constructor.
     * @param missionControl Controller for mission management
     */
    public MissionSubPanel(final MissionSubController missionControl) {
        this.missionControl = missionControl;
    }
    
    /**
     * Initializes the components of the view.
     */
    @Override
    public void initComponents() {
        // Set the layout of the main panel to BorderLayout to support flexible resizing
        missionPanel.setLayout(new BorderLayout(3, 3));
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        contMissionPane.add(scrollPanel, BorderLayout.CENTER);
        missionControl.initMissionList();
    
        this.missionPanel = new JPanel(new GridLayout(0, 1, 2, 2));
        setMissions();
        allMissionsSetup();
        setPanel(contMissionPane);
    }

    /**
     * Method to confirm an action with a message dialog.
     * @param text The message text
     * @param name The title of the dialog
     * @return true if confirmed, false otherwise
     */
    private boolean toConfirm(final String text, final String name) {
        return JOptionPane.showConfirmDialog(missionPanel, text, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /* 
    * Layout, similar to MarketSubPanel, but for missions, which has a completely different concept.
    */
    private void setMissions() {
        final JPanel bottomPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JButton toMarket = new JButton("BACK");
        missionPanel.add(bottomPan, BorderLayout.SOUTH);

        toMarket.addActionListener(e -> {
            if (toConfirm("Do you want to come back to the market?", "Back to market")) {
                missionControl.setStage(Stage.STORE);
            }
        });

        bottomPan.add(toMarket);
    }

    private void allMissionsSetup() {
        missionPanel.removeAll();

        for (final var mission : missionControl.redeemableMissions()) {
            final JButton missionButton = new JButton("Mission");
            final JButton acceptMission = new JButton("Accept");
            final JPanel missionItemPanel = new JPanel(new GridLayout(1, 3, 2, 2));
            missionItemPanel.add(missionButton);
            missionItemPanel.add(acceptMission);

            this.missionPanel.add(missionItemPanel);

            acceptMission.addActionListener(e -> {
                if (toConfirm("Do you want to accept this mission?", "Accept Mission") && missionControl.redeemMission(mission)) {
                    forceRedraw();
                }
            });
        }
        // Update the view after any changes
        missionPanel.revalidate();
        missionPanel.repaint();
    }

    /**
     * Forces the redraw of the panel to reflect any changes
     */
    @Override
    protected void forceRedraw() {
        missionPanel.revalidate();
        missionPanel.repaint();
    }
}
