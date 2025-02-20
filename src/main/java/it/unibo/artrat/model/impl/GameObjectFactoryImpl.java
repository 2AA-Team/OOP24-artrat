package it.unibo.artrat.model.impl;

import java.util.HashSet;
import java.util.Random;

import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.model.api.characters.AbstractEntity;
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
    public AbstractGameObject getWall(final int x, final int y) {
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
    public AbstractEntity getRandomEnemy(final int x, final int y) {
        final AbstractEntity a;
        switch (RANDOM.nextInt(2)) {
            case 1:
                a = new AdvancedEnemy(new Point(), new Point());
                break;
            default:
                a = new BaseEnemy(new Point(), new Point());
                break;
        }
        a.setPosition(new Point(x, y));
        return a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractGameObject getPicture(final int x, final int y) {
        return new Picture(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractGameObject getExit(final int x, final int y) {
        return new Exit(x, y);
    }

}
