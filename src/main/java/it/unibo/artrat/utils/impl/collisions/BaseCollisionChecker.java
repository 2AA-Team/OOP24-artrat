package it.unibo.artrat.utils.impl.collisions;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.Entity;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Vector2d;

public class BaseCollisionChecker extends AbstractCollisionChecker {

    public BaseCollisionChecker(double renderDistance) {
        super(renderDistance);
    }

    @Override
    public void updateAndCheckPlayer(Command cmd, long delta) {
        final var model = this.mainController.getModel();
        final var player = model.getPlayer();
        if (!Objects.isNull(cmd)) {
            cmd.execute(player);
        }
        player.update(delta);
        if (checkWallCollision(player)) {
            player.update(-delta);
        }
        // System.out.println(player.getSpeed());
        model.setPlayer(player);
        this.mainController.setModel(model);
    }

    private void enemiesCollisionAvoidance(long delta) {
        final var model = this.mainController.getModel();
        final var player = model.getPlayer();
        final Floor floor = model.getFloor();
        final BoundingBox bb = new BoundingBoxImpl(player.getPosition(), renderDistance, renderDistance);
        final var enemies = floor.getEnemies();
        final Set<Enemy> updated = new HashSet<>();

        for (Enemy e : enemies) {
            if (e.getBoundingBox().isColliding(bb)) {
                var speed = e.getSpeed();
                var pos = e.getPosition();
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
        model.setFloor(floor);
    }

    private boolean checkWallCollision(final Entity e) {
        return this.mainController.getModel().getFloor().getWalls().stream()
                .anyMatch(x -> x.getBoundingBox().isColliding(e.getBoundingBox()));
    }

    private boolean checkFieldOfView(final Enemy e) {
        return this.mainController
                .getModel()
                .getPlayer()
                .getBoundingBox()
                .isColliding(e.getFieldOfView());
    }

    @Override
    public void updateAndCheckPaintings() {
    }

    @Override
    public void updateEnemiesState(final long delta) {
        final Model model = this.mainController.getModel();
        final Floor floor = model.getFloor();
        final BoundingBox bb = new BoundingBoxImpl(model.getPlayer().getPosition(), renderDistance, renderDistance);
        final Set<Enemy> enemies = floor.getEnemies();

        enemies.forEach(x -> {
            if (x.getBoundingBox().isColliding(bb)) {
                System.out.println(checkFieldOfView(x));
                if (checkFieldOfView(x)) {
                    x.follow(this.mainController.getModel().getPlayer());
                } else {
                    x.move();
                }
            }

        });
        floor.setEnemies(enemies);
        model.setFloor(floor);
        this.mainController.setModel(model);
        enemiesCollisionAvoidance(delta);

    }

}
