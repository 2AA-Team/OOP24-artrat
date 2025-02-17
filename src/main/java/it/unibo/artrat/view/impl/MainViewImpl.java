package it.unibo.artrat.view.impl;

import javax.swing.JFrame;
import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.view.api.MainView;
import java.awt.Toolkit;

/**
 * implementation of class mainView.
 * 
 * @author Matteo Tonelli
 */
public class MainViewImpl implements MainView {

    private MainController controller;
    private AbstractSubPanel subPanel;
    private final ResourceLoader<String, Double> resourceLoader;
    private final JFrame frame = new JFrame();

    /**
     * constructor set the size of the frame.
     * 
     * @param width  width of the frame
     * @param heigth height of the frame
     */
    public MainViewImpl(ResourceLoader<String, Double> resourceLoader) {
        this.resourceLoader = resourceLoader;
        final double width = resourceLoader.getConfig("MENU_WIDTH");
        final double height = resourceLoader.getConfig("MENU_HEIGHT");
        frame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * width),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * height));
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
        this.controller.setStage(currentStage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forceRedraw() {
        subPanel.forceRedraw();
        this.frame.repaint();
        this.frame.revalidate();
    }

    /**
     * reload frame to get the right size.
     */
    private void reloadFrame() {
        final double width = resourceLoader.getConfig(controller.getStage().toString() + "_WIDTH");
        final double height = resourceLoader.getConfig(controller.getStage().toString() + "_HEIGHT");
        frame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * width),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * height));
        frame.setContentPane(subPanel.getPanel());
        frame.revalidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reconduceFromStage() {
        switch (this.controller.getStage()) {
            case MENU:
                subPanel = new MenuSubPanel(controller.getControllerManager().getMenuSubController());
                break;
            case GAME:
                subPanel = new GameSubPanel(controller.getControllerManager().getGameSubController());
                break;
            case STORE:
                subPanel = new EmptySubPanel(controller.getControllerManager().getMenuSubController());
                break;
            case INVENTORY:
                subPanel = new InventorySubPanel(controller.getControllerManager().getInventorySubController());
                break;
            default:
                throw new IllegalStateException();
        }
        reloadFrame();
        subPanel.setFrameDimension(this.frame.getSize());
    }
}
