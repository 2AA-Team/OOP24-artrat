package it.unibo.artrat.view.impl;

import javax.swing.JButton;

import it.unibo.artrat.model.impl.Stage;

import java.awt.BorderLayout;

public class GameSubView extends AbstractView {

    @Override
    public void initComponents() {
        panel.setLayout(new BorderLayout());
        JButton jb = new JButton("Game");
        jb.addActionListener((e) -> {
            requester.setStage(Stage.MENU);
        });
        panel.add(jb);
        panel.setVisible(true);
    }

}
