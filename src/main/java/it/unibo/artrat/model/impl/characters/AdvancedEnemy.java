package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;
import it.unibo.artrat.model.api.characters.AbstractEntity;

/**
 * New hypothetical enemies.
 */
public final class AdvancedEnemy extends AbstractEntity implements Enemy {

    /**
     * Advanced enemy constructor.
     * 
     * @param bottomLeft
     * @param topRight
     * @param v
     */
    public AdvancedEnemy(final Point bottomLeft, final Point topRight, final Vector2d v) {
        super(bottomLeft, topRight, v);
    }

    /**
     * Advanced enemy constructor.
     * 
     * @param bottomLeft
     * @param topRight
     */
    public AdvancedEnemy(final Point bottomLeft, final Point topRight) {
        super(bottomLeft, topRight);
    }

    @Override
    public void follow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'follow'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void capture() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'capture'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interact() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'redraw'");
    }

}
