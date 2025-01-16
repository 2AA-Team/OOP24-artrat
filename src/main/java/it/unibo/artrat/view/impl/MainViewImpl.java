package it.unibo.artrat.view.impl;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.view.api.MainView;

public class MainViewImpl implements MainView {

    private Stage currentStage;
    private MainController controller;

    private final JFrame frame = new JFrame();

    @Override
    public void setController(MainController observer) {
        controller = observer;
    }

    @Override
    public void initiate() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    @Override
    public void SetContent(String s) {
    }

}
