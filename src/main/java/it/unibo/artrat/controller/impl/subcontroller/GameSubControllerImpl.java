package it.unibo.artrat.controller.impl.subcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.controller.api.subcontroller.GameSubController;
import it.unibo.artrat.controller.impl.AbstractSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.model.api.Compass;
import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.CompassNearestExit;
import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * sub controller for the game.
 * 
 * @author Matteo Tonelli
 */
public class GameSubControllerImpl extends AbstractSubController implements GameSubController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameSubControllerImpl.class);
    private final Floor floor;
    private final double renderDistance;
    private Compass compass;

    /**
     * constructor to initialize mainController.
     * 
     * @param mainController main controller
     * @param rl             resource loader
     * @throws IOException if the resource loader fails
     */
    public GameSubControllerImpl(final MainControllerImpl mainController, final ResourceLoader<String, Double> rl)
            throws IOException {
        super(mainController);
        this.renderDistance = rl.getConfig("RENDER_DISTANCE");
        this.floor = mainController.getModel().getFloor();
        this.floor.generateFloorSet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point> getVisibleWallPositions() {
        final BoundingBox bb = new BoundingBoxImpl(getPlayerPos(), renderDistance, renderDistance);
        return this.getModel().getFloor().getWalls().stream().filter(x -> bb.isColliding(x.getBoundingBox()))
                .map(GameObject::getPosition).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point> getVisibleEnemyPositions() {
        final BoundingBox bb = new BoundingBoxImpl(getPlayerPos(), renderDistance, renderDistance);
        return this.getModel().getFloor().getEnemies().stream().filter(x -> bb.isColliding(x.getBoundingBox()))
                .map(GameObject::getPosition).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getPlayerPos() {
        return getModel().getPlayer().getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point> getExitPos() {
        return this.getModel().getFloor().getExit().stream().map(GameObject::getPosition).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point> getVisibleCollectables() {
        final BoundingBox bb = new BoundingBoxImpl(getPlayerPos(), renderDistance, renderDistance);
        return this.getModel().getFloor().getCollectables().stream().filter(x -> bb.isColliding(x.getBoundingBox()))
                .map(GameObject::getPosition).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRenderDistance() {
        return (int) renderDistance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        final Model model = this.getModel();
        try {
            this.floor.generateFloorSet();
        } catch (IOException e) {
            LOGGER.warn("floor generation failed.");
        }
        model.setFloor(this.floor);
        final Player player = model.getPlayer();
        player.setPosition(this.floor.getStartPosition());
        player.setSpeed(new Vector2d());
        model.setPlayer(player);
        this.updateCentralizeModel(model);
        this.compass = new CompassNearestExit(
                this::getPlayerPos,
                () -> new ArrayList<>(getExitPos()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getAngleCompass() {
        return this.compass.calculateAngle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStolenCollectable() {
        return getModel().getPlayer().getColletableList().size();
    }

}
