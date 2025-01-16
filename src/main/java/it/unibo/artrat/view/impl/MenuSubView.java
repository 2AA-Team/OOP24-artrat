package it.unibo.artrat.view.impl;

import javax.swing.JButton;

import it.unibo.artrat.model.impl.Stage;

import java.awt.BorderLayout;

public class MenuSubView extends AbstractView {

    @Override
    public void initComponents() {
        panel.setLayout(new BorderLayout());
        JButton jb = new JButton("Menu");
        jb.addActionListener((e) -> {
            requester.setStage(Stage.GAME);
        });
        panel.add(jb);
        panel.setVisible(true);
    }

}
