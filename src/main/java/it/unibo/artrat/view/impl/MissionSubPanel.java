package it.unibo.artrat.view.impl;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

import it.unibo.artrat.controller.api.subcontroller.MissionSubController;
import it.unibo.artrat.model.impl.Stage;

/**
 * MissionSubPanel, the MissionCenter to read and achieve goals.
 */
public class MissionSubPanel extends AbstractSubPanel {
    private static final int GAP = 5;
    private final MissionSubController missionControl;
    private JPanel missionCenterPanel = new JPanel();
    private JPanel missionToClaimPanel = new JPanel();
    private final JPanel contMissionPane = new JPanel(new BorderLayout());
    private final JScrollPane scrollPanel = new JScrollPane(missionCenterPanel);

    /**
     * MissionSubPanel constructor.
     * 
     * @param missionControl MissionSubController.
     */
    public MissionSubPanel(final MissionSubController missionControl) {
        this.missionControl = missionControl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponents() {
        missionCenterPanel.setLayout(new BorderLayout(GAP, GAP));
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contMissionPane.add(scrollPanel, BorderLayout.CENTER);
        missionControl.initMissionList();

        this.missionToClaimPanel = new JPanel(new GridLayout(0, 1, GAP, GAP));
        setMissionCenter();
        allMissionsSetup();

        missionCenterPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                forceRedraw();
            }
        });
        setPanel(contMissionPane);
    }

    private boolean toConfirm(final String text, final String name) {
        return JOptionPane.showConfirmDialog(missionCenterPanel, text, name, 
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

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
            final JButton claimButton = new JButton(missionControl.getMissionName(mission) + ": " 
                    + missionControl.showDescr(mission));

            claimButton.setEnabled(false);
            final JPanel missPanel = new JPanel(new GridLayout(1, 2, GAP, GAP));
            missPanel.add(claimButton);
            missionToClaimPanel.add(missPanel);

            if (missionControl.redeemMission(mission)) {
                claimButton.setBackground(Color.GREEN);
            }
            forceRedraw();
        }
        this.missionCenterPanel.add(missionToClaimPanel, BorderLayout.CENTER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void forceRedraw() {
        missionCenterPanel.revalidate();
        missionCenterPanel.repaint();
    }
}
