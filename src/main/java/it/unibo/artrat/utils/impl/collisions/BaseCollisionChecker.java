package it.unibo.artrat.utils.impl.collisions;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.Entity;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;

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
            final var rollBack = new Lupino(player.getPosition(), new HashSet<>());

            if (!Objects.isNull(cmd)) {
                cmd.execute(player);
            }
            player.update(delta);
            System.out.println(player.getSpeed());

            if (!checkWallCollision(player)) {
                model.setPlayer(player);
            } else {
                model.setPlayer(rollBack);
            }
            this.mainController.setModel(model);
        }
    }

    @Override
    public void updateAndCheckVisibleEnemy(long delta) {
        if (delta < deltaLimit) {
            final var model = this.mainController.getModel();
            final var player = model.getPlayer();
            final Floor floor = model.getFloor();
            final var enemies = floor.getEnemies();
            final Set<Enemy> updated = new HashSet<>();
            final BoundingBox bb = new BoundingBoxImpl(player.getPosition(),
                    renderDistance, renderDistance);
            for (Enemy e : enemies) {
                if (bb.isColliding(e.getBoundingBox())) {
                    final Enemy tmp = e;
                    tmp.move();
                    tmp.update(delta);
                    if (!checkWallCollision(tmp)) {
                        updated.add(tmp);
                    } else {
                        updated.add(e);
                    }
                } else {
                    updated.add(e);
                }
            }
            floor.setEnemies(updated);
            model.setFloor(floor);
        }
    }

    private boolean checkWallCollision(final Entity e) {
        return this.mainController.getModel().getFloor().getWalls().stream()
                .anyMatch(x -> x.getBoundingBox().isColliding(e.getBoundingBox()));
    }

    @Override
    public void updateAndCheckPaintings() {
    }

}
