package it.unibo.artrat.model.impl.characters;

import java.util.Random;

import it.unibo.artrat.model.api.characters.AbstractEntity;
import it.unibo.artrat.model.api.characters.Enemy;
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
public final class BaseEnemy extends AbstractEntity implements Enemy {
    private final Random rd = new Random();
    private final BoundingBox fieldOfView;

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
    public BaseEnemy(final Point topLeft, final Point bottomRight, final Vector2d v) {
        super(topLeft, bottomRight, v);
        this.fieldOfView = new BoundingBoxImpl(topLeft, bottomRight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void follow(final Player p) {
        final var speed = this.getSpeed();
        final var playerDirection = p.getSpeed().normalize();
        this.setSpeed(playerDirection.mul(speed.module()));
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
        final var dir = rd.nextInt(Directions.values().length);
        final var speed = this.getSpeed();
        final Vector2d v = Directions.values()[dir].vector();
        this.setSpeed(v.mul(speed.module()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long delta) {
        super.update(delta);
        this.fieldOfView.setCenter(this.getPosition());
    }

}
