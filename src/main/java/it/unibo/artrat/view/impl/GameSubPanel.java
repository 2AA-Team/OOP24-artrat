package it.unibo.artrat.view.impl;

import javax.swing.JPanel;
import it.unibo.artrat.controller.api.subcontroller.GameSubController;
import it.unibo.artrat.utils.impl.Point;

/**
 * game sub panel class.
 */
public class GameSubPanel extends AbstractSubPanel {
    private final GameSubController gameSubController;
    private final static int zoom = 100;

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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forceRedraw() {
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        final Point center = new Point(
                (int) Math.floor(getFrameDimension().getWidth() / 2),
                (int) Math.floor(getFrameDimension().getHeight() / 2));
        panel.add(new ImageLabel("src/main/java/it/unibo/artrat/resources/player.png",
                (int) center.getX(), (int) center.getY(), zoom, zoom).getJLabel());
        final Point playerPos = this.gameSubController.getPlayerPos();
        for (final var wallsPoint : this.gameSubController.getVisibleWallPositions()) {
            int wallX = (int) Math.floor(center.getX() + (wallsPoint.getX() - playerPos.getX()) * zoom);
            int wallY = (int) Math.floor(center.getY() + (wallsPoint.getY() - playerPos.getY()) * zoom);
            panel.add(new ImageLabel("src/main/java/it/unibo/artrat/resources/wall.png",
                    wallX,
                    wallY,
                    zoom,
                    zoom)
                    .getJLabel());
        }
        setPanel(panel);
        panel.revalidate();
        panel.repaint();
    }
}
