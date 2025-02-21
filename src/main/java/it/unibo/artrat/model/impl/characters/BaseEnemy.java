package it.unibo.artrat.model.impl.characters;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.artrat.model.api.characters.AbstractEnemy;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.api.Directions;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Standard Enemy class.
 * 
 * @author Samuele Trapani
 */
public final class BaseEnemy extends AbstractEnemy {
    private final Random rd = new Random();
    private final BoundingBox fieldOfView;
    private static final int DEFAULT_STEPS = 10;
    private int steps = 0;

    public BaseEnemy(final Point center, final double width, final double height) {
        super(center, width, height, new HashSet<>());
        fieldOfView = new BoundingBoxImpl(center, width, height);
    }

    /**
     * Base enemy constructor.
     * 
     * @param topLeft     bottom left corner enemy boundingbox
     * @param bottomRight top right corner enemy boundingbox
     */
    public BaseEnemy(final Point topLeft, final Point bottomRight) {
        super(topLeft, bottomRight);
        this.fieldOfView = new BoundingBoxImpl(topLeft, bottomRight);
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
        this.fieldOfView = new BoundingBoxImpl(topLeft, bottomRight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void follow(final Player p) {
        System.out.println(this.getPosition() + " FOLLOWING");
        final Set<Vector2d> dir = Set.of(Directions.values()).stream()
                .map(x -> x.vector())
                .collect(Collectors.toSet());
        Vector2d tmp = new Vector2d();
        double min = Integer.MAX_VALUE;
        for (Vector2d v : dir) {
            final Point current = this.getPosition().sum(v);
            double distance = p.getPosition().getEuclideanDistance(current);
            if (distance <= min) {
                tmp = v;
                min = distance;
            }
        }
        this.setSpeed(tmp);
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
