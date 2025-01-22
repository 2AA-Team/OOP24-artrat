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
     * {@inheritDoc}
     */
    public AdvancedEnemy(final Point p, final double radius, final Vector2d v) {
        super(p, radius, v);
    }

    /**
     * {@inheritDoc}
     */
    public AdvancedEnemy(final Point p, final double radius) {
        super(p, radius);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'redraw'");
    }

}
