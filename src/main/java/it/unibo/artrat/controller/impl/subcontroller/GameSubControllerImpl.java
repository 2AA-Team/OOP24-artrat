package it.unibo.artrat.controller.impl.subcontroller;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import it.unibo.artrat.controller.api.subcontroller.GameSubController;
import it.unibo.artrat.controller.impl.AbstractSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.model.impl.world.FloorImpl;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * sub controller for the game.
 */
public class GameSubControllerImpl extends AbstractSubController implements GameSubController {
    private final Player player;
    private final Floor floor;
    private final double renderDistance;
    private final double zoom;

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
        this.zoom = rl.getConfig("ZOOM");
        this.floor = new FloorImpl(rl);
        this.floor.generateFloorSet();
        this.player = mainController.getModel().getPlayer();
        this.player.setPosition(this.floor.getStartPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point> getVisibleWallPositions() {
        final BoundingBox bb = new BoundingBoxImpl(getPlayerPos(), renderDistance, renderDistance);
        return this.floor.getWalls().stream().filter(x -> bb.isColliding(x.getBoundingBox()))
                .map(AbstractGameObject::getPosition).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point> getVisibleEnemyPositions() {
        final BoundingBox bb = new BoundingBoxImpl(getPlayerPos(), renderDistance, renderDistance);
        return this.floor.getEnemies().stream().filter(x -> bb.isColliding(x.getBoundingBox()))
                .map(AbstractGameObject::getPosition).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getPlayerPos() {
        return player.getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getZoom() {
        return (int) zoom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getExitPos() {
        return this.floor.getExitPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point> getVisiblePaintings() {
        final BoundingBox bb = new BoundingBoxImpl(getPlayerPos(), renderDistance, renderDistance);
        return this.floor.getValues().stream().filter(x -> bb.isColliding(x.getBoundingBox()))
                .map(AbstractGameObject::getPosition).collect(Collectors.toSet());
    }

}
