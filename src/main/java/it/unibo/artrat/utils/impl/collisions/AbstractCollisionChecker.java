package it.unibo.artrat.utils.impl.collisions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.ModelImpl;
import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * Collision manager.
 */
public abstract class AbstractCollisionChecker {
    private MainController mainController;
    private final double renderDistance;
    private Model model = null;
    private Floor floor = null;
    private Player player = new Lupino(new Point(), new Point());
    private BoundingBox renderBB = new BoundingBoxImpl(new Point(), new Point());
    private List<BoundingBox> wallRendered;

    /**
     * Collision manager contructor.
     * 
     * @param renderDistance of the map
     * @param mainController main controller
     */
    @SuppressFBWarnings("EI2")
    public AbstractCollisionChecker(final double renderDistance, final MainController mainController) {
        this.renderDistance = renderDistance;
        this.mainController = mainController;
    }

    /**
     * Update game objects and check collisions.
     * 
     * @param mainController main controller
     * @param cmd            command executed
     * @param delta          delta time
     */
    @SuppressFBWarnings("EI2")
    public void updateAndCheck(final MainController mainController, final Command cmd, final long delta) {
        this.mainController = Objects.requireNonNull(mainController);
        this.model = this.mainController.getModel();
        this.floor = model.getFloor();
        this.player = model.getPlayer();
        renderBB = new BoundingBoxImpl(player.getPosition(), renderDistance, renderDistance);
        final BoundingBox renderTmp = new BoundingBoxImpl(player.getPosition(), renderDistance + 2, renderDistance + 2);
        wallRendered = floor.getWalls().stream().map(GameObject::getBoundingBox)
                .filter(x -> x.isColliding(renderTmp)).toList();

        updateAndCheckPlayer(cmd, delta);
        updateAndCheckCollectables();

        model.setFloor(floor);
        upPlayer();

        updateEnemiesState(delta);
        updateAndCheckExit();
    }

    /**
     * Update the player in the model.
     */
    protected void upPlayer() {
        model.setPlayer(player);
        if (!Objects.isNull(mainController)) {
            mainController.setModel(model);
        }

    }

    /**
     * Player movement and collisions management.
     * 
     * @param cmd   command executed
     * @param delta delta time
     */
    public abstract void updateAndCheckPlayer(Command cmd, long delta);

    /**
     * Collectables claiming management.
     */
    public abstract void updateAndCheckCollectables();

    /**
     * Game exit collsion management.
     */
    public abstract void updateAndCheckExit();

    /**
     * Enemies movement and collisions management.
     * 
     * @param delta delta time
     */
    public abstract void updateEnemiesState(long delta);

    /**
     * Get local main controller.
     * 
     * @return main controller
     */
    public MainController getMainController() {
        return Objects.requireNonNull(this.mainController);
    }

    /**
     * Set local main controller.
     * 
     * @param controller updated main controller
     */
    @SuppressFBWarnings("EI2")
    public void setMainController(final MainController controller) {
        this.mainController = Objects.requireNonNull(controller);
    }

    /**
     * Get local player.
     * 
     * @return local player
     */
    public Player getPlayer() {
        return this.player.copyPlayer();
    }

    /**
     * Set local player.
     * 
     * @param player updated player
     */
    public void setPlayer(final Player player) {
        this.player = player.copyPlayer();
    }

    /**
     * Set local model.
     * 
     * @param model updated model
     */
    public void setModel(final Model model) {
        this.model = new ModelImpl(model);
    }

    /**
     * Get local model.
     * 
     * @return local model
     */
    public Model getModel() {
        return new ModelImpl(model);
    }

    /**
     * Set local floor.
     * 
     * @param floor updated floor
     */
    public void setFloor(final Floor floor) {
        this.floor = floor.copyFloor();
    }

    /**
     * Get local floor.
     * 
     * @return local floor
     */
    public Floor getFloor() {
        return this.floor.copyFloor();
    }

    /**
     * Get local render bounding box.
     * 
     * @return local render bounding box
     */
    public BoundingBox getRenderBoundingBox() {
        return new BoundingBoxImpl(this.renderBB.getTopLeft(), this.renderBB.getBottomRight());
    }

    /**
     * Set local render bounding box.
     * 
     * @param bb updated bounding box
     */
    public void setRenderBoundingBox(final BoundingBox bb) {
        this.renderBB = new BoundingBoxImpl(bb.getTopLeft(), bb.getBottomRight());
    }

    /**
     * Get rendered walls.
     * 
     * @return rendered walls
     */
    public List<BoundingBox> getRenderedWalls() {
        return new ArrayList<>(wallRendered);
    }

    /**
     * Set Rendered walls.
     * 
     * @param walls new rendered walls
     */
    public void setRenderedWalls(final List<BoundingBox> walls) {
        this.wallRendered = new ArrayList<>(walls);
    }

}
