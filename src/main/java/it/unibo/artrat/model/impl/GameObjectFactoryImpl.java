package it.unibo.artrat.model.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.function.Function;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.impl.characters.AdvancedEnemy;
import it.unibo.artrat.model.impl.characters.BaseEnemy;
import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.model.impl.world.Exit;
import it.unibo.artrat.model.impl.world.Picture;
import it.unibo.artrat.model.impl.world.Wall;
import it.unibo.artrat.utils.impl.Point;

/**
 * An implementation of GameObjectFactory.
 */
public class GameObjectFactoryImpl implements GameObjectFactory {

    private static final Random RANDOM = new Random();

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
        return switch (RANDOM.nextInt(2)) {
            case 0 -> new AdvancedEnemy(new Point(x, y), size, size);
            case 1 -> new BaseEnemy(new Point(x, y), size, size);
            default -> throw new IllegalStateException();

        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getPicture(final int x, final int y) {
        final double priceMax = 10;
        return new Picture(x, y, RANDOM.nextDouble(priceMax));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getExit(final int x, final int y) {
        return new Exit(x, y);
    }

}
