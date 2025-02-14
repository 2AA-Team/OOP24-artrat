package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.Player;
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
     * @param topLeft     top left corner
     * @param bottomRight bottom right corner
     * @param v           vector
     */
    public AdvancedEnemy(final Point topLeft, final Point bottomRight, final Vector2d v) {
        super(topLeft, bottomRight, v);
    }

    /**
     * Advanced enemy constructor.
     * 
     * @param topLeft     top left corner
     * @param bottomRight bottom right corner
     */
    public AdvancedEnemy(final Point topLeft, final Point bottomRight) {
        super(topLeft, bottomRight);
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

    @Override
    public void follow(final Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'follow'");
    }

}
