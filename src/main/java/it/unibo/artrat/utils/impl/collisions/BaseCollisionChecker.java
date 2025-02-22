package it.unibo.artrat.utils.impl.collisions;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.Entity;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.Vector2d;

public class BaseCollisionChecker extends AbstractCollisionChecker {

    public BaseCollisionChecker(double renderDistance) {
        super(renderDistance);
    }

    @Override
    public void updateAndCheckPlayer(Command cmd, long delta) {
        if (!Objects.isNull(cmd)) {
            cmd.execute(player);
        }
        player.update(delta);
        if (checkWallCollision(player)) {
            player.update(-delta);
        }
        upPlayer();
    }

    private void enemiesCollisionAvoidance(long delta) {
        final var enemies = floor.getEnemies();
        final Set<Enemy> updated = new HashSet<>();

        for (final Enemy e : enemies) {
            if (e.getBoundingBox().isColliding(renderBB)) {
                final var speed = e.getSpeed();
                final var pos = e.getPosition();
                e.update(delta);
                if (checkWallCollision(e)) {
                    e.setSpeed(new Vector2d());
                    speed.forEach(x -> e.addDirection(x));
                    e.setPosition(pos);
                }
            }
            updated.add(e);

        }
        floor.setEnemies(updated);
    }

    private boolean checkWallCollision(final Entity e) {
        return this.wallRendered.stream().anyMatch(x -> x.isColliding(e.getBoundingBox()));
    }

    private boolean checkFieldOfView(final Enemy e) {
        return this.mainController
                .getModel()
                .getPlayer()
                .getBoundingBox()
                .isColliding(e.getFieldOfView());
    }

    @Override
    public void updateEnemiesState(final long delta) {

        final Set<Enemy> enemies = floor.getEnemies();

        enemies.forEach(x -> {
            if (x.getBoundingBox().isColliding(renderBB)) {
                if (checkFieldOfView(x)) {
                    x.follow(this.mainController.getModel().getPlayer());
                } else {
                    x.move();
                }
            }
        });
        floor.setEnemies(enemies);
        enemiesCollisionAvoidance(delta);

    }

    @Override
    public void updateAndCheckPaintings() {
        BoundingBox bbPlayer = player.getBoundingBox();
        Set<Collectable> updated = new HashSet<>();
        for (Collectable value : floor.getValues()) {
            if (!value.getBoundingBox().isColliding(bbPlayer)) {
                updated.add(value);
            } else {
                player.addCollectable(value);
            }
        }
        floor.setValues(updated);
    }

    @Override
    public void updateAndCheckExit() {
        if (floor.getExit().stream()
                .anyMatch(x -> x.getBoundingBox().isColliding(player.getBoundingBox()))) {
            mainController.winGame();
        }
    }

}
