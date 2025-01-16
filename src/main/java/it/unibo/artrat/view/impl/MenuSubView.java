package it.unibo.artrat.view.impl;

import javax.swing.JButton;
import java.awt.BorderLayout;

public class MenuSubView extends AbstractView {

    @Override
    public void initComponents() {
        panel.setLayout(new BorderLayout());
        JButton jb = new JButton("Menu");
        jb.addActionListener(null);
        panel.add(jb);
        panel.setVisible(true);
    }

}
