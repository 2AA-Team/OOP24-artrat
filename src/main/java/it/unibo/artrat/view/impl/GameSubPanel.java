package it.unibo.artrat.view.impl;

import javax.swing.JPanel;
import it.unibo.artrat.controller.api.subcontroller.GameSubController;
import it.unibo.artrat.utils.impl.Point;

/**
 * game sub panel class.
 */
public class GameSubPanel extends AbstractSubPanel {
    private final GameSubController gameSubController;

    /**
     * constructor for game sub panel.
     * 
     * @param gameSubController sub controller for the game
     */
    public GameSubPanel(final GameSubController gameSubController) {
        this.gameSubController = gameSubController;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void initComponents() {
        final JPanel panel = new JPanel();

        setPanel(panel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forceRedraw() {
        final JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.add(new ImageLabel("src/main/java/it/unibo/artrat/resources/image.jpg",
                (int) Math.floor(getFrameDimension().getWidth() / 2),
                (int) Math.floor(getFrameDimension().getHeight() / 2), 10, 10).getJLabel());
        final Point playerPos = this.gameSubController.getPlayerPos();
        for (final var wallsPoint : this.gameSubController.getVisibleWallPositions()) {
            panel.add(new ImageLabel("src/main/java/it/unibo/artrat/resources/image.jpg",
                    (int) Math.floor(wallsPoint.getX() * this.getFrameDimension().getHeight() / 2 / playerPos.getX()),
                    (int) Math.floor(wallsPoint.getY() * this.getFrameDimension().getWidth() / 2 / playerPos.getY()),
                    100,
                    100)
                    .getJLabel());
        }
        setPanel(panel);
    }
}
