package it.unibo.artrat.view.impl;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.controller.api.subcontroller.GameSubController;
import it.unibo.artrat.model.impl.world.RoomSymbols;
import it.unibo.artrat.utils.impl.Point;

/**
 * game sub panel class.
 */
public class GameSubPanel extends AbstractSubPanel {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameSubPanel.class);
    private final GameSubController gameSubController;
    private final GamePanel mapPanel = new GamePanel();
    private int resizedX;
    private int resizedY;
    private final int zoom;
    private static final Map<RoomSymbols, Image> MAPSYMBOLS = Map.of(
            RoomSymbols.ENEMY, getObjectImage("enemy.png"),
            RoomSymbols.WALL, getObjectImage("wall.png"),
            RoomSymbols.VALUE, getObjectImage("picture.png"),
            RoomSymbols.EXIT, getObjectImage("exit.png"),
            RoomSymbols.PLAYER, getObjectImage("player.png"));

    private static Image getObjectImage(final String image) {
        try {
            return ImageIO.read(Thread.currentThread().getContextClassLoader().getResource(
                    "gameobjects" + File.separator + image));
        } catch (IOException e) {
            LOGGER.error("Try to get a not existing image.");
            return null;
        }
    }

    private final class GamePanel extends JPanel {
        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            resizedX = (int) Math.floor(getFrameDimension().getWidth() / gameSubController.getRenderDistance());
            resizedY = (int) Math.floor(getFrameDimension().getHeight() / gameSubController.getRenderDistance());
            final Point center = new Point(
                    (int) Math.floor(getFrameDimension().getWidth() / 2),
                    (int) Math.floor(getFrameDimension().getHeight() / 2));

            final Point playerPos = gameSubController.getPlayerPos();
            printObject(g, center, playerPos, RoomSymbols.WALL, gameSubController.getVisibleWallPositions());
            printObject(g, center, playerPos, RoomSymbols.EXIT, Set.of(gameSubController.getExitPos()));
            printObject(g, center, playerPos, RoomSymbols.ENEMY, gameSubController.getVisibleEnemyPositions());
            printObject(g, center, playerPos, RoomSymbols.VALUE, gameSubController.getVisiblePaintings());
            printPlayer(g, center);
        }

        private void printObject(final Graphics g, final Point center, final Point playerPos,
                final RoomSymbols object, final Set<Point> setObject) {
            for (final var it : setObject) {
                final int wallX = (int) Math
                        .floor(center.getX() + (it.getX() - playerPos.getX()) * resizedX * zoom);
                final int wallY = (int) Math
                        .floor(center.getY() + (it.getY() - playerPos.getY()) * resizedY * zoom);

                g.drawImage(MAPSYMBOLS.get(object), wallX, wallY, resizedX * zoom, resizedY * zoom, null);
            }
        }

        private void printPlayer(final Graphics g, final Point center) {
            g.drawImage(MAPSYMBOLS.get(RoomSymbols.PLAYER), (int) center.getX(), (int) center.getY(),
                    resizedX * zoom, resizedY * zoom, null);
        }
    }

    /**
     * constructor for game sub panel.
     * 
     * @param gameSubController sub controller for the game
     */
    public GameSubPanel(final GameSubController gameSubController) {
        this.gameSubController = gameSubController;
        zoom = gameSubController.getZoom();
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void initComponents() {
        final JPanel tmp = new JPanel();
        tmp.setLayout(new BorderLayout());
        tmp.add(this.mapPanel, BorderLayout.CENTER);
        setPanel(tmp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forceRedraw() {
    }
}
