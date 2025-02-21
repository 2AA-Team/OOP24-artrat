package it.unibo.artrat.view.impl;

import java.awt.*;
import java.util.Locale;

import javax.swing.*;

import it.unibo.artrat.controller.api.subcontroller.MissionSubController;

/**
 * MissionSubPanel, we enter here from the market.
 */
public class MissionSubPanel extends AbstractSubPanel {
    private final MissionSubController missionControl;
    private JPanel missionPanel = new JPanel();
        private final JPanel contPane = new JPanel(new BorderLayout());
        private final JScrollPane scrollPanel = new JScrollPane(missionPanel);
        //private final JTextField searchMissionField = new JTextField(SEARCH_TEXT_FIELD);
    
        public MissionSubPanel(final MissionSubController missionControl) {
            this.missionControl = missionControl;
        }
    
        @Override
        public void initComponents() {
           // missionPanel.setLayout(new BorderLayout(GAP, GAP));
            scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            contPane.add(scrollPanel, BorderLayout.CENTER);
            missionControl.initMissionList();
    
            this.missionPanel = new JPanel(new GridLayout(0, 1, 2, 2));
        setMissions();
        allMissionsSetup();
        setPanel(contPane);
    }

    private void setMissions() {
        // Layout, similar to the MarketSubPanel, but for missions
        final JPanel bottomPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JButton toMarket = new JButton("BACK");
        
        // Add components to the layout
        missionPanel.add(bottomPan, BorderLayout.SOUTH);

        /* 
        toMarket.addActionListener(e -> {
            if (toConfirm("Do you want to come back to the menu?", "Back to menu")) {
                exitSettings();
                missionControl.setStage(Stage.MARKET);
            }
        });*/

        bottomPan.add(toMarket);
    }

    private void allMissionsSetup() {
        missionPanel.removeAll();

        for (final var mission : missionControl.redeemableMissions()) {
            final JButton missionButton = new JButton("Mission");
            final JButton acceptMission = new JButton("Accept");
            final JPanel missionPanel = new JPanel(new GridLayout(1, 3, 2, 2));  // Similar to items grid
            missionPanel.add(missionButton);
            missionPanel.add(acceptMission);

            this.missionPanel.add(missionPanel);

            acceptMission.addActionListener(e -> {
                if (toConfirm("Do you want to accept this mission?", "Accept Mission") && contr.acceptMission(mission)) {
                    forceRedraw();
                }
            });
        }
        missionPanel.revalidate();
        missionPanel.repaint();
    }

    @Override
    protected void forceRedraw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'forceRedraw'");
    }
}
