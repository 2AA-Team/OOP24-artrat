package it.unibo.artrat.view.impl;

import java.awt.*;
import javax.swing.*;

import it.unibo.artrat.controller.api.subcontroller.MissionSubController;
import it.unibo.artrat.model.impl.Stage;

/**
 * MissionSubPanel, the MissionCenter to read and claim rewards.
 */
public class MissionSubPanel extends AbstractSubPanel {
    private static final int GAP = 5;
    private final MissionSubController missionControl;
    private JPanel missionCenterPanel = new JPanel();
    private JPanel missionToClaimPanel = new JPanel();
    private final JPanel contMissionPane = new JPanel(new BorderLayout());
    private final JScrollPane scrollPanel = new JScrollPane(missionCenterPanel);

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
        missionCenterPanel.setLayout(new BorderLayout(GAP, GAP));
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contMissionPane.add(scrollPanel, BorderLayout.CENTER);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        missionControl.initMissionList();
    
        this.missionToClaimPanel = new JPanel(new GridLayout(0, 1, GAP, GAP));
        setMissionCenter();
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
        return JOptionPane.showConfirmDialog(missionCenterPanel, text, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /* 
    * Layout, similar to MarketSubPanel, but for missions, which has a completely different concept.
    */
    private void setMissionCenter() {
        final JPanel uppJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JPanel bottomPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JButton toMenu = new JButton("BACK");
        final JLabel missJLabel = new JLabel("MISSION CENTER, BECOME AN ART RATTER!");

        toMenu.addActionListener(e -> {
            if (toConfirm("Do you want to come back to menu?", "Back to menu")) {
                missionControl.setStage(Stage.MENU);
            }
        });

        uppJPanel.add(missJLabel);
        bottomPan.add(toMenu);
        missionCenterPanel.add(bottomPan, BorderLayout.SOUTH);
        missionCenterPanel.add(uppJPanel, BorderLayout.NORTH);
    }

    private void allMissionsSetup() {

        for (final var mission : missionControl.redeemableMissions()) {
            final JButton claimButton = new JButton(missionControl.getMissionName(mission) + ": " + missionControl.showDescr(mission));
            final JPanel missPanel = new JPanel(new GridLayout(1, 2, GAP, GAP));

            missPanel.add(claimButton);
            missionToClaimPanel.add(missPanel);

            if(missionControl.redeemMission(mission)) {
                claimButton.setBackground(new Color(0,128,0));
            }
            forceRedraw();
        }   

        this.missionCenterPanel.add(missionToClaimPanel, BorderLayout.CENTER);
       // forceRedraw();
    }

    /**
     * Forces the redraw of the panel to reflect any changes
     */
    @Override
    protected void forceRedraw() {
        missionCenterPanel.revalidate();
        missionCenterPanel.repaint();
    }
}
