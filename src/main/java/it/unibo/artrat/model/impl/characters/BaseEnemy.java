package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.AbstractEntity;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Standard Enemy.
 */
public final class BaseEnemy extends AbstractEntity implements Enemy {
    /**
     * {@inheritDoc}
     */
    public BaseEnemy(final Point p, final double radius) {
        super(p, radius);
    }

    /**
     * {@inheritDoc}
     */
    public BaseEnemy(final Point p, final double radius, final Vector2d v) {
        super(p, radius, v);
    }

    @Override
    public void follow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'follow'");
    }

    @Override
    public void capture() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'capture'");
    }

    @Override
    public void interact() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

    @Override
    public void redraw() {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'redraw'");
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
