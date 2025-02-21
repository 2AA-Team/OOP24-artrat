package it.unibo.artrat.utils.impl.collisions;

import java.util.Objects;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.utils.api.commands.Command;

public abstract class AbstractCollisionChecker {
    protected MainController mainController = null;
    protected double renderDistance = 0;

    public AbstractCollisionChecker(double renderDistance) {
        this.renderDistance = renderDistance;
    }

    public void updateAndCheck(MainController mainController, Command cmd, long delta) {
        this.mainController = Objects.requireNonNull(mainController);
        updateAndCheckPlayer(cmd, delta);
        updateEnemiesState(delta);
        updateAndCheckPaintings();

    }

    public abstract void updateAndCheckPlayer(Command cmd, long delta);

    public abstract void updateAndCheckPaintings();

    public abstract void updateEnemiesState(long delta);
}
