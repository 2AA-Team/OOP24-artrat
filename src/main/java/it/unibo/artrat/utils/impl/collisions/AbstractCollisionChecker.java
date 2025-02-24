package it.unibo.artrat.utils.impl.collisions;

import java.util.List;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;

public abstract class AbstractCollisionChecker {
    MainController mainController = null;
    double renderDistance = 0;
    private Model model;
    Floor floor;
    Player player;
    BoundingBox renderBB;
    List<BoundingBox> wallRendered;

    public AbstractCollisionChecker(final double renderDistance) {
        this.renderDistance = renderDistance;
    }

    public void updateAndCheck(final MainController mainController, final Command cmd, final long delta) {
        this.mainController = mainController.clone();
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
