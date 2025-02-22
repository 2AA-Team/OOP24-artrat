package it.unibo.artrat.view.impl;

import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

import javax.swing.JFrame;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.view.api.MainView;

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
     * @param resourceLoader resource loader
     */
    public MainViewImpl(final ResourceLoader<String, Double> resourceLoader) {
        this.resourceLoader = Objects.requireNonNull(resourceLoader);
        final double width = resourceLoader.getConfig("MENU_WIDTH");
        final double height = resourceLoader.getConfig("MENU_HEIGHT");
        frame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * width),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * height));
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent comp) {
                subPanel.setFrameDimension(frame.getSize());
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final MainController observer) {
        this.controller = Objects.requireNonNull(observer);
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
        frame.revalidate();
        frame.repaint();
    }

    /**
     * reload frame to get the right size.
     */
    private void reloadFrame() {

        subPanel.initComponents();
        final double width = resourceLoader.getConfig(controller.getStage().toString() + "_WIDTH");
        final double height = resourceLoader.getConfig(controller.getStage().toString()
                + "_HEIGHT");
        frame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * width),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * height));
        frame.setContentPane(subPanel.getPanel());
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
        subPanel.getPanel().requestFocus();
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
                subPanel = new MarketSubPanel(controller.getControllerManager().getStoreSubController());
                break;
            case INVENTORY:
                subPanel = new InventorySubPanel(controller.getControllerManager().getInventorySubController());
                break;
            case MISSIONS:
                subPanel = new MissionSubPanel(controller.getControllerManager().getMissionsSubController());
                break;
            default:
                throw new IllegalStateException("Stage does not exist.");
        }
        subPanel.setFrameDimension(this.frame.getSize());
        reloadFrame();
    }
}
