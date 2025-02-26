package it.unibo.artrat.view.impl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.controller.api.subcontroller.GameSubController;
import it.unibo.artrat.model.impl.world.RoomSymbols;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.commands.MoveDown;
import it.unibo.artrat.utils.impl.commands.MoveLeft;
import it.unibo.artrat.utils.impl.commands.MoveRight;
import it.unibo.artrat.utils.impl.commands.MoveUp;
import it.unibo.artrat.utils.impl.commands.StopDown;
import it.unibo.artrat.utils.impl.commands.StopLeft;
import it.unibo.artrat.utils.impl.commands.StopRight;
import it.unibo.artrat.utils.impl.commands.StopUp;

/**
 * game sub panel class.
 * 
 * @author Matteo Tonelli
 */
public class GameSubPanel extends AbstractSubPanel {
    private static final int ONE_SECOND = 1000;
    private static final Logger LOGGER = LoggerFactory.getLogger(GameSubPanel.class);
    private final GameSubController gameSubController;
    private final GamePanel mapPanel = new GamePanel();
    private int resizedX;
    private int resizedY;
    private static final int UP = KeyEvent.VK_W;
    private static final int DOWN = KeyEvent.VK_S;
    private static final int LEFT = KeyEvent.VK_A;
    private static final int RIGHT = KeyEvent.VK_D;

    private final JLabel timerCountdown = new JLabel();
    private final JLabel counterColletable = new JLabel();

    private static final Map<RoomSymbols, Image> MAPSYMBOLS = Map.of(
            RoomSymbols.ENEMY, getObjectImage("enemy.png"),
            RoomSymbols.WALL, getObjectImage("wall.png"),
            RoomSymbols.COLLECTABLE, getObjectImage("painting.png"),
            RoomSymbols.EXIT, getObjectImage("exit.png"),
            RoomSymbols.PLAYER, getObjectImage("player.png"));
    private static final Image COMPASS = getObjectImage("compass.png");

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
            printObject(g, center, playerPos, RoomSymbols.EXIT, gameSubController.getExitPos());
            printObject(g, center, playerPos, RoomSymbols.COLLECTABLE, gameSubController.getVisibleCollectables());
            printObject(g, center, playerPos, RoomSymbols.ENEMY, gameSubController.getVisibleEnemyPositions());
            printPlayer(g, center);

            timerCountdown.setText(Integer.toString(gameSubController.getCurrentTimeController() / ONE_SECOND));
            counterColletable.setText(Integer.toString(gameSubController.getStolenCollectable()));
            forceRedraw();

            printCompass(g, gameSubController.getAngleCompass());
        }

        private void printCompass(final Graphics g, final double angle) {
            final Graphics2D g2 = (Graphics2D) g;
            g2.rotate(angle, resizedX + resizedX, resizedY + resizedX);
            g2.drawImage(
                    COMPASS,
                    resizedX,
                    resizedX,
                    resizedX * 2,
                    resizedY * 2,
                    null);
        }

        private void printObject(final Graphics g, final Point center, final Point playerPos,
                final RoomSymbols object, final Set<Point> setObject) {
            for (final var it : setObject) {
                final int wallX = (int) Math
                        .floor(center.getX() + (it.getX() - playerPos.getX()) * resizedX);
                final int wallY = (int) Math
                        .floor(center.getY() + (it.getY() - playerPos.getY()) * resizedY);

                g.drawImage(MAPSYMBOLS.get(object), wallX, wallY, resizedX, resizedY, null);
            }
        }

        private void printPlayer(final Graphics g, final Point center) {
            g.drawImage(MAPSYMBOLS.get(RoomSymbols.PLAYER), (int) center.getX(), (int) center.getY(),
                    resizedX, resizedY, null);
        }
    }

    /**
     * constructor for game sub panel.
     * 
     * @param gameSubController sub controller for the game
     */
    public GameSubPanel(final GameSubController gameSubController) {
        this.gameSubController = gameSubController;

        this.gameSubController.startTimerSubController(); // starto il timer
    }

    private boolean isMovementCommand(final KeyEvent e) {
        final var key = e.getKeyCode();
        return key == UP || key == DOWN || key == LEFT || key == RIGHT;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void initComponents() {
        final JPanel tmp = new JPanel();
        final JPanel southJPanel = new JPanel();
        final int verticalGap = 5;

        tmp.setLayout(new BorderLayout());
        tmp.add(this.mapPanel, BorderLayout.CENTER);

        final JLabel timerJLabel = new JLabel("TIMER");
        final JLabel collectableCounterLabel = new JLabel("Collectable: ");
        southJPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, verticalGap)); // 50px tra i due gruppi

        final JPanel timerPanel = new JPanel();
        timerPanel.add(timerJLabel);
        timerPanel.add(timerCountdown);

        final JPanel collectableLabel = new JPanel();
        collectableLabel.add(collectableCounterLabel);
        collectableLabel.add(counterColletable);

        southJPanel.add(timerPanel);
        southJPanel.add(collectableLabel);
        tmp.add(southJPanel, BorderLayout.SOUTH);

        tmp.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
            }

            @Override
            public void keyPressed(final KeyEvent e) {
                if (isMovementCommand(e)) {
                    switch (e.getKeyCode()) {
                        case UP -> gameSubController.inputMainController(new MoveUp());
                        case DOWN -> gameSubController.inputMainController(new MoveDown());
                        case RIGHT -> gameSubController.inputMainController(new MoveRight());
                        case LEFT -> gameSubController.inputMainController(new MoveLeft());
                        default -> LOGGER.info("Tasto premuto non gestito: " + e.getKeyCode());
                    }
                }
            }

            @Override
            public void keyReleased(final KeyEvent e) {
                if (isMovementCommand(e)) {
                    switch (e.getKeyCode()) {
                        case UP -> gameSubController.inputMainController(new StopUp());
                        case DOWN -> gameSubController.inputMainController(new StopDown());
                        case RIGHT -> gameSubController.inputMainController(new StopRight());
                        case LEFT -> gameSubController.inputMainController(new StopLeft());
                        default -> LOGGER.info("Tasto premuto non gestito: " + e.getKeyCode());
                    }
                }
            }
        });
        setPanel(tmp);
        tmp.setFocusable(true);
        tmp.requestFocusInWindow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forceRedraw() {
        SwingUtilities.invokeLater(gameSubController::isTimeOutSubController);
    }
}
