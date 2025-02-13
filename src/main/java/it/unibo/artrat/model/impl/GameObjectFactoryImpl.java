package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.AbstractGameObject;
import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.utils.impl.Point;

/**
 * factory for standard game object.
 */
public class GameObjectFactoryImpl implements GameObjectFactory {

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
    public AbstractGameObject getPlayer(final int x, final int y) {
        return getWall(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractGameObject getEnemy(final int x, final int y) {
        return getWall(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractGameObject getValue(final int x, final int y) {
        return getWall(x, y);
    }

}
