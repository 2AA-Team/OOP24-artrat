package it.unibo.artrat.model.impl;

import java.util.HashSet;
import java.util.Random;

import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.EnemyFactory;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.impl.characters.EnemyFactoryImpl;
import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.model.impl.world.Exit;
import it.unibo.artrat.model.impl.world.Painting;
import it.unibo.artrat.model.impl.world.Wall;
import it.unibo.artrat.utils.impl.Point;

/**
 * An implementation of GameObjectFactory.
 */
public class GameObjectFactoryImpl implements GameObjectFactory {

    private static final Random RANDOM = new Random();
    private final EnemyFactory enemies = new EnemyFactoryImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getWall(final int x, final int y) {
        return new Wall(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer(final int x, final int y) {
        return new Lupino(new Point(x, y), new HashSet<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enemy getRandomEnemy(final int x, final int y) {
        final double size = 0.8;
        final Point pos = new Point(x, y);
        return switch (RANDOM.nextInt(2)) {
            case 0 -> enemies.createAdvancedEnemy(pos, size, size);
            case 1 -> enemies.createBaseEnemy(pos, size, size);
            default -> throw new IllegalStateException("");

        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collectable getCollectable(final int x, final int y) {
        final double priceMax = 10;
        return new Painting(x, y, RANDOM.nextDouble(priceMax));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getExit(final int x, final int y) {
        return new Exit(x, y);
    }

}
