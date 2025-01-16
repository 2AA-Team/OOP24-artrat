package it.unibo.artrat.view.impl;

import javax.swing.JFrame;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.view.api.MainView;

/**
 * implementation of class mainView.
 */
public class MainViewImpl implements MainView {

    private Stage currentStage;
    private MainController controller;

    private final JFrame frame = new JFrame();

    /**
     * Sets the controller controlled by this view (if works as input).
     *
     * @param observer the controller to attach
     */
    @Override
    public void setController(final MainController observer) {
        controller = observer;
    }

    /**
     * This method is called before the UI is used. It should finalize its status.
     * (if needed).
     */
    @Override
    public void initiate() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * This method is used to set the stage in a view.
     * 
     * @param currentStage
     */
    @Override
    public void setStage(final Stage currentStage) {
        this.currentStage = currentStage;
    }

    /**
     * force to update all his component.
     */
    @Override
    public void forceUpdate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'forceUpdate'");
    }

}
