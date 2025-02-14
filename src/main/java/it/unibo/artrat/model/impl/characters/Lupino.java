package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.AbstractEntity;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Player implementation.
 */
public class Lupino extends AbstractEntity implements Player {
    /**
     * Player constructor with default vector.
     * 
     * @param topLeft     boundingbox corner
     * @param bottomRight boundingbox corner
     */
    public Lupino(final Point topLeft, final Point bottomRight) {
        this(topLeft, bottomRight, new Vector2d());
        // TODO Auto-generated constructor stub
    }

    /**
     * Player constructor.
     * 
     * @param topLeft     boundingbox corner
     * @param bottomRight boundingbox corner
     * @param v           direction
     */
    public Lupino(final Point topLeft, final Point bottomRight, final Vector2d v) {
        super(topLeft, bottomRight, v);
        // TODO Auto-generated constructor stub
    }

    /**
     * Player constructor.
     * 
     * @param center bounding box center
     */
    public Lupino(final Point center) {
        super(center);
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
