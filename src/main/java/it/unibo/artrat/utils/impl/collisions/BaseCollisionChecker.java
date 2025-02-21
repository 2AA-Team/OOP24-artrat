package it.unibo.artrat.utils.impl.collisions;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.Entity;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Vector2d;

public class BaseCollisionChecker extends AbstractCollisionChecker {
    private final static long deltaLimit = 30;

    public BaseCollisionChecker(double renderDistance) {
        super(renderDistance);
    }

    @Override
    public void updateAndCheckPlayer(Command cmd, long delta) {
        if (delta < deltaLimit) {
            final var model = this.mainController.getModel();
            final var player = model.getPlayer();
            if (!Objects.isNull(cmd)) {
                cmd.execute(player);
            }
            player.update(delta);
            if (checkWallCollision(player)) {
                player.update(-delta);
            }
            model.setPlayer(player);
            this.mainController.setModel(model);
        }

    }

    @Override
    public void updateAndCheckVisibleEnemy(long delta) {
        final var model = this.mainController.getModel();
        final var player = model.getPlayer();
        final Floor floor = model.getFloor();
        final BoundingBox bb = new BoundingBoxImpl(player.getPosition(), renderDistance, renderDistance);
        final var enemies = floor.getEnemies().stream().filter(x -> bb.isColliding(x.getBoundingBox())).toList();
        final Set<Enemy> updated = new HashSet<>();

        for (Enemy e : enemies) {
            var speed = e.getSpeed();
            var pos = e.getPosition();
            e.move();
            e.update(delta);
            if (checkWallCollision(e)) {
                e.setSpeed(new Vector2d());
                speed.forEach(x -> e.addDirection(x));
                e.setPosition(pos);
            }
            updated.add(e);
        }
        floor.setEnemies(updated);
        model.setFloor(floor);
    }

    private boolean checkWallCollision(final Entity e) {
        return this.mainController.getModel().getFloor().getWalls().stream()
                .anyMatch(x -> x.getBoundingBox().isColliding(e.getBoundingBox()));
    }
}
