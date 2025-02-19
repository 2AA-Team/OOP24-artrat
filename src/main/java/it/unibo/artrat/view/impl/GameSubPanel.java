package it.unibo.artrat.view.impl;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.controller.api.subcontroller.GameSubController;
import it.unibo.artrat.model.impl.world.RoomSymbols;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.commands.MoveDown;
import it.unibo.artrat.utils.impl.commands.MoveLeft;
import it.unibo.artrat.utils.impl.commands.MoveRight;
import it.unibo.artrat.utils.impl.commands.MoveStop;
import it.unibo.artrat.utils.impl.commands.MoveUp;

/**
 * game sub panel class.
 */
public class GameSubPanel extends AbstractSubPanel {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameSubPanel.class);
    private final GameSubController gameSubController;
    private final GamePanel mapPanel = new GamePanel();
    private final int zoom;
    private static final int UP = KeyEvent.VK_W;
    private static final int DOWN = KeyEvent.VK_S;
    private static final int LEFT = KeyEvent.VK_A;
    private static final int RIGHT = KeyEvent.VK_D;
    private final Set<Command> pressed = new HashSet<>();
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
            final Point center = new Point(
                    (int) Math.floor(getFrameDimension().getWidth() / 2),
                    (int) Math.floor(getFrameDimension().getHeight() / 2));

            final Point playerPos = gameSubController.getPlayerPos();

            printPlayer(g, center);
            printWalls(g, center, playerPos);
            printExit(g, center, playerPos);
            printEnemy(g, center, playerPos);
            printPaintings(g, center, playerPos);
        }

        private void printEnemy(final Graphics g, final Point center, final Point playerPos) {
            for (final var enemyPoints : gameSubController.getVisibleEnemyPositions()) {
                final int wallX = (int) Math.floor(center.getX() + (enemyPoints.getX() - playerPos.getX()) * zoom);
                final int wallY = (int) Math.floor(center.getY() + (enemyPoints.getY() - playerPos.getY()) * zoom);

                g.drawImage(MAPSYMBOLS.get(RoomSymbols.ENEMY), wallX, wallY, zoom, zoom, null);
            }
        }

        private void printPaintings(final Graphics g, final Point center, final Point playerPos) {
            for (final var paintPoints : gameSubController.getVisiblePaintings()) {
                final int wallX = (int) Math.floor(center.getX() + (paintPoints.getX() - playerPos.getX()) * zoom);
                final int wallY = (int) Math.floor(center.getY() + (paintPoints.getY() - playerPos.getY()) * zoom);

                g.drawImage(MAPSYMBOLS.get(RoomSymbols.VALUE), wallX, wallY, zoom, zoom, null);
            }
        }

        private void printExit(final Graphics g, final Point center, final Point playerPos) {
            final Point exitPos = gameSubController.getExitPos();
            final int wallX = (int) Math.floor(center.getX() + (exitPos.getX() - playerPos.getX()) * zoom);
            final int wallY = (int) Math.floor(center.getY() + (exitPos.getY() - playerPos.getY()) * zoom);

            g.drawImage(MAPSYMBOLS.get(RoomSymbols.EXIT), wallX, wallY, zoom, zoom, null);
        }

        private void printPlayer(final Graphics g, final Point center) {
            g.drawImage(MAPSYMBOLS.get(RoomSymbols.PLAYER), (int) center.getX(), (int) center.getY(),
                    zoom, zoom, null);
        }

        private void printWalls(final Graphics g, final Point center, final Point playerPos) {
            for (final var wallsPoint : gameSubController.getVisibleWallPositions()) {

                final int wallX = (int) Math.floor(center.getX() + (wallsPoint.getX() - playerPos.getX()) * zoom);
                final int wallY = (int) Math.floor(center.getY() + (wallsPoint.getY() - playerPos.getY()) * zoom);

                g.drawImage(MAPSYMBOLS.get(RoomSymbols.WALL), wallX, wallY, zoom, zoom, null);
            }
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

    private boolean isMovementCommand(KeyEvent e) {
        var key = e.getKeyCode();
        return key == UP || key == DOWN || key == LEFT || key == RIGHT;
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

        tmp.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("typed: " + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case UP -> gameSubController.inputMainController(new MoveUp());
                    case DOWN -> gameSubController.inputMainController(new MoveDown());
                    case RIGHT -> gameSubController.inputMainController(new MoveRight());
                    case LEFT -> gameSubController.inputMainController(new MoveLeft());

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (isMovementCommand(e)) {
                    gameSubController.inputMainController(new MoveStop());
                }
            }
        });

        setPanel(tmp);
        tmp.setFocusable(true);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forceRedraw() {
    }
}
