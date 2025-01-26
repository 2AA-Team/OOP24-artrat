package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.AbstractEntity;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Standard Enemy class.
 */
public final class BaseEnemy extends AbstractEntity implements Enemy {

    /**
     * Base enemy constructor.
     * 
     * @param topLeft     bottom left corner enemy boundingbox
     * @param bottomRight top right corner enemy boundingbox
     */
    public BaseEnemy(final Point topLeft, final Point bottomRight) {
        super(topLeft, bottomRight);
    }

    /**
     * Base enemy constructor.
     * 
     * @param topLeft     top left corner
     * @param bottomRight bottom right corner
     * @param v           vector
     */
    public BaseEnemy(final Point topLeft, final Point bottomRight, final Vector2d v) {
        super(topLeft, bottomRight, v);
    }

    /**
     * {@inheritDoc}
     */
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
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'redraw'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
