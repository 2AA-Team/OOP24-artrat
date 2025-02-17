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
    final Player player;
    final Floor floor;
    final double FOV;

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
        player = null;
        FOV = rl.getConfig("FOV");
        floor = new FloorImpl(rl);
        this.floor.generateFloorSet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point> getVisibleWallPositions() {
        BoundingBox bb = new BoundingBoxImpl(getPlayerPos(), FOV, FOV);
        // return this.floor.getWalls().stream().filter(x ->
        // bb.isColliding(x.getBoundingBox()))
        // .map(AbstractGameObject::getPosition).collect(Collectors.toSet());
        return null;
    }

    @Override
    public Point getPlayerPos() {
        return player.getPosition();
    }

}
