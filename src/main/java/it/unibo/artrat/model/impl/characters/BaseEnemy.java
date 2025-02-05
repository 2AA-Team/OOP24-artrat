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
     * @param bottomLeft bottom left corner enemy boundingbox
     * @param topRight   top right corner enemy boundingbox
     */
    public BaseEnemy(final Point bottomLeft, final Point topRight) {
        super(bottomLeft, topRight);
    }

    /**
     * Base enemy constructor.
     * 
     * @param bottomLeft
     * @param topRight
     * @param v
     */
    public BaseEnemy(final Point bottomLeft, final Point topRight, final Vector2d v) {
        super(bottomLeft, topRight, v);
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
