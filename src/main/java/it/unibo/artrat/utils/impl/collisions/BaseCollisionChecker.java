package it.unibo.artrat.utils.impl.collisions;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import it.unibo.artrat.controller.api.MainController;
import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.characters.characters.Enemy;
import it.unibo.artrat.model.impl.characters.characters.Entity;
import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Base collision manager.
 */
public class BaseCollisionChecker extends AbstractCollisionChecker {
    /**
     * Collsion manager constructor.
     * 
     * @param renderDistance render distance
     * @param mainController main controller
     */
    public BaseCollisionChecker(final double renderDistance, final MainController mainController) {
        super(renderDistance, mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAndCheckPlayer(final Command cmd, final long delta) {
        final Player player = this.getPlayer();
        if (!Objects.isNull(cmd)) {
            cmd.execute(player);
        }
        player.update(delta);
        if (checkWallCollision(player)) {
            player.update(-delta);
        }
        this.setPlayer(player);
        upPlayer();
    }

    private void enemiesCollisionAvoidance(final long delta) {
        final var enemies = this.getFloor().getEnemies();
        final Set<Enemy> updated = new HashSet<>();

        for (final Enemy e : enemies) {
            if (e.getBoundingBox().isColliding(this.getRenderBoundingBox())) {
                final var speed = e.getSpeed();
                final var pos = e.getPosition();
                e.update(delta);
                if (checkWallCollision(e)
                        || getFloor().getExit().stream()
                                .anyMatch(x -> x.getBoundingBox().isColliding(e.getBoundingBox()))) {
                    e.setSpeed(new Vector2d());
                    speed.forEach(e::addDirection);
                    e.setPosition(pos);
                }
                if (e.getBoundingBox().isColliding(this.getPlayer().getBoundingBox())) {
                    this.getMainController().loseGame();
                }
            }
            updated.add(e);

        }
        this.getFloor().setEnemies(updated);
    }

    private boolean checkWallCollision(final Entity e) {
        return this.getRenderedWalls().stream().anyMatch(x -> x.isColliding(e.getBoundingBox()));
    }

    private boolean checkFieldOfView(final Enemy e) {
        return this.getMainController()
                .getModel()
                .getPlayer()
                .getBoundingBox()
                .isColliding(e.getFieldOfView());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEnemiesState(final long delta) {

        final Set<Enemy> enemies = this.getFloor().getEnemies();

        enemies.forEach(x -> {
            if (x.getBoundingBox().isColliding(this.getRenderBoundingBox())) {
                if (checkFieldOfView(x)) {
                    x.follow(this.getMainController().getModel().getPlayer());
                } else {
                    x.move();
                }
            }
        });
        final Floor floor = this.getFloor();
        floor.setEnemies(enemies);
        this.setFloor(floor);
        enemiesCollisionAvoidance(delta);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAndCheckCollectables() {
        final BoundingBox bbPlayer = this.getPlayer().getBoundingBox();
        final Set<Collectable> updated = new HashSet<>();
        final Player p = this.getPlayer();
        final Floor floor = this.getFloor();

        for (final Collectable collectable : this.getFloor().getCollectables()) {
            if (!collectable.getBoundingBox().isColliding(bbPlayer)) {
                updated.add(collectable);
            } else {
                p.addCollectable(collectable);
            }
        }
        this.setPlayer(p);
        floor.setCollectables(updated);
        this.setFloor(floor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAndCheckExit() {
        if (this.getFloor().getExit().stream()
                .anyMatch(x -> x.getBoundingBox().isColliding(this.getPlayer().getBoundingBox()))) {
            this.getMainController().winGame();
        }
    }

}
