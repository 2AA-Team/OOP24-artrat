package it.unibo.artrat.utils.impl.collisions;

import java.util.List;
import java.util.Objects;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;

public abstract class AbstractCollisionChecker {
    protected MainController mainController = null;
    protected double renderDistance = 0;
    private Model model;
    protected Floor floor;
    protected Player player;
    protected BoundingBox renderBB;
    protected List<BoundingBox> wallRendered;

    public AbstractCollisionChecker(double renderDistance) {
        this.renderDistance = renderDistance;
    }

    public void updateAndCheck(MainController mainController, Command cmd, long delta) {
        this.mainController = Objects.requireNonNull(mainController);
        this.model = this.mainController.getModel();
        this.floor = model.getFloor();
        this.player = model.getPlayer();
        renderBB = new BoundingBoxImpl(player.getPosition(), renderDistance, renderDistance);
        final BoundingBox renderTmp = new BoundingBoxImpl(player.getPosition(), renderDistance + 2, renderDistance + 2);
        wallRendered = floor.getWalls().stream().map(GameObject::getBoundingBox)
                .filter(x -> x.isColliding(renderTmp)).toList();

        updateAndCheckPlayer(cmd, delta);
        updateAndCheckPaintings();

        model.setFloor(floor);
        upPlayer();
        updateEnemiesState(delta);
        updateAndCheckExit();
    }

    protected void upPlayer() {
        model.setPlayer(player);
        mainController.setModel(model);
    }

    public abstract void updateAndCheckPlayer(Command cmd, long delta);

    public abstract void updateAndCheckPaintings();

    public abstract void updateAndCheckExit();

    public abstract void updateEnemiesState(long delta);
}
