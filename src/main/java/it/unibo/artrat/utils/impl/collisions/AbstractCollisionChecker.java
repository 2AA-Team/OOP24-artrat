package it.unibo.artrat.utils.impl.collisions;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.utils.api.commands.Command;

public abstract class AbstractCollisionChecker {
    protected MainController mainController = null;
    protected double renderDistance = 0;

    public AbstractCollisionChecker(double renderDistance) {
        this.renderDistance = renderDistance;
    }

    public void updateAndCheck(MainController mainController, Command cmd, long delta) {
        this.mainController = mainController;
        updateAndCheckPlayer(cmd, delta);
        updateAndCheckVisibleEnemy(delta);
    }

    public abstract void updateAndCheckPlayer(Command cmd, long delta);

    public abstract void updateAndCheckVisibleEnemy(long delta);
}
