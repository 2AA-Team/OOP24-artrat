package it.unibo.artrat.view.impl;

import javax.swing.JFrame;
import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.view.api.MainView;
import java.awt.Toolkit;

/**
 * implementation of class mainView.
 * 
 * @author Matteo Tonelli
 */
public class MainViewImpl implements MainView {

    private Stage currentStage;
    private MainController controller;
    private AbstractSubPanel subPanel;

    private final JFrame frame = new JFrame();

    /**
     * constructor set the size of the frame.
     * 
     * @param width  width of the frame
     * @param heigth height of the frame
     */
    public MainViewImpl(final double width, final double heigth) {
        frame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * width),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * heigth));
        this.currentStage = Stage.MENU;
        this.subPanel = new EmptySubPanel();
        this.controller = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final MainController observer) {
        controller = observer == null ? controller : observer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initiate() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public void forceRedraw() {
        subPanel.forceRedraw();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reconduceFromStage() {
        switch (currentStage) {
            case MENU:
                subPanel = new MenuSubPanel(controller.getControllerManager().getMenuSubController());
                break;
            case GAME:
                subPanel = new EmptySubPanel(controller.getControllerManager().getMenuSubController());
                break;
            case STORE:
                subPanel = new EmptySubPanel(controller.getControllerManager().getMenuSubController());
                break;
            default:
                throw new IllegalStateException();
        }
        frame.setContentPane(subPanel.getPanel());
    }
}
