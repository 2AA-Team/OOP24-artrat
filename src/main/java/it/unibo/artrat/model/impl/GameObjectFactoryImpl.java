package it.unibo.artrat.model.impl;

import java.util.Random;
import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.model.api.characters.AbstractEntity;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.impl.characters.AdvancedEnemy;
import it.unibo.artrat.model.impl.characters.BaseEnemy;
import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

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
        final AbstractGameObject a = new AbstractGameObject() {

            @Override
            public void redraw() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'redraw'");
            }

            @Override
            public void update(final int delta) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'update'");
            }

        };
        a.setPosition(new Point(x, y));
        return a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer(final int x, final int y) {
        return new Lupino(new Point(x, y), new Vector2d());
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
    public AbstractGameObject getValue(final int x, final int y) {
        return getWall(x, y);
    }

}
