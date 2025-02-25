package it.unibo.artrat.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibo.artrat.controller.api.subcontroller.MissionSubController;

/**
 * MissionSubPanel, the MissionCenter to read and achieve goals.
 */
public class MissionSubPanel extends AbstractSubPanel {
    private static final int GAP = 5;
    private final MissionSubController missionControl;
    private final JPanel missionCenterPanel = new JPanel();
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
        forceRedraw();
        setPanel(contMissionPane);
    }

    private void allMissionsSetup() {
        for (final var mission : missionControl.missionList()) {
            final JLabel missionLabel = new JLabel(missionControl.getMissionName(mission) + ": "
                    + missionControl.showDescr(mission));

            final JPanel missPanel = new JPanel(new GridLayout(1, 2, GAP, GAP));
            missPanel.add(missionLabel);
            if (mission.isMissionDone(missionControl.getModel().getPlayer())) {
                missionLabel.setOpaque(true);
                missionLabel.setBackground(Color.GREEN);
            }
            missionToClaimPanel.add(missPanel);
        }
        this.missionCenterPanel.add(missionToClaimPanel, BorderLayout.CENTER);
    }

    private void setMissionCenter() {
        final JPanel uppJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JPanel bottomPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JButton toMenu = new JButton("BACK");
        final JLabel missJLabel = new JLabel("MISSION CENTER, BECOME AN ART RATTER!");

        toMenu.addActionListener(e -> {
            missionControl.goToMenu();
        });

        uppJPanel.add(missJLabel);
        bottomPan.add(toMenu);
        missionCenterPanel.add(bottomPan, BorderLayout.SOUTH);
        missionCenterPanel.add(uppJPanel, BorderLayout.NORTH);
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
