package it.unibo.artrat.model.impl.characters;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import it.unibo.artrat.model.api.characters.AbstractEnemy;
import it.unibo.artrat.utils.api.Directions;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Standard Enemy class.
 * 
 * @author Samuele Trapani
 */
public final class BaseEnemy extends AbstractEnemy {
    private final Random rd = new Random();
    private static final int DEFAULT_STEPS = 10;
    private int steps = 0;

    public BaseEnemy(final Point center, final double width, final double height) {
        super(center, width, height, new HashSet<>());
    }

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
    public BaseEnemy(final Point topLeft, final Point bottomRight, final Set<Vector2d> v) {
        super(topLeft, bottomRight, v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        if (steps <= 0) {
            this.steps = DEFAULT_STEPS;
            final int dir = rd.nextInt(Directions.values().length);
            this.setSpeed(Directions.values()[dir].vector());
        } else {
            this.steps--;
        }
    }

}
