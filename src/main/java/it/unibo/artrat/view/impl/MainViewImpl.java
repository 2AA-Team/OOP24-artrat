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
        frame.setSize(400, 300);
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
        switchToCurrentPanel();
    }

    /**
     * set up the setted stage.
     */
    private void switchToCurrentPanel() {
        reconduceFromStage();
        frame.revalidate();
    }

    /**
     * force to update all his component.
     */
    @Override
    public void forceUpdate() {
    }

    @Override
    public void reconduceFromStage() {
        switch (currentStage) {
            case MENU:
                frame.setContentPane(new MenuSubView().getPanel());
                break;
            case GAME:
                frame.setContentPane(new MenuSubView().getPanel());
                break;
            case STORE:
                frame.setContentPane(new MenuSubView().getPanel());
                break;
            default:
                throw new IllegalStateException();
        }
    }
}
