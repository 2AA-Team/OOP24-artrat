package it.unibo.artrat.model.impl.characters;

import java.util.HashSet;
import java.util.Set;

import it.unibo.artrat.model.api.characters.AbstractEnemy;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * New hypothetical enemies.
 * 
 * @author Samuele Trapani
 */
public final class AdvancedEnemy extends AbstractEnemy {

    public AdvancedEnemy(final Point center, final double width, final double height) {
        super(center, width, height, new HashSet<>());
    }

    /**
     * Advanced enemy constructor.
     * 
     * @param topLeft     top left corner
     * @param bottomRight bottom right corner
     * @param v           vector
     */
    public AdvancedEnemy(final Point topLeft, final Point bottomRight, final Set<Vector2d> v) {
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

    @Override
    public void follow(final Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'follow'");
    }

}
