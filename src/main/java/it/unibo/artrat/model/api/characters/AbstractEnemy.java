package it.unibo.artrat.model.api.characters;

import java.util.HashSet;
import java.util.Set;

import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Enemy essential implementation.
 * 
 * @author Samuele Trapani
 */
public abstract class AbstractEnemy extends AbstractEntity implements Enemy {
    private static final int FOV_SCALE = 8;
    private BoundingBox fieldOfView;
    private static final double VEL = 0.005;

    /**
     * Enemy constructor.
     * 
     * @param center of the enemy bounding box
     * @param width  of the enemy bounding box
     * @param height of the enemy bounding box
     * @param v      current direction
     */
    public AbstractEnemy(final Point center, final double width, final double height, final Set<Vector2d> v) {
        super(center, width, height, v);
        this.fieldOfView = new BoundingBoxImpl(center, width * FOV_SCALE, height * FOV_SCALE);
        super.setVelocity(VEL);
    }

    /**
     * Enemy constructor.
     * 
     * @param topLeft     corner of the enemy bounding box
     * @param bottomRight corner of the enemy bounding box
     */
    public AbstractEnemy(final Point topLeft, final Point bottomRight) {
        super(topLeft, bottomRight, new HashSet<>());
        final BoundingBox tmp = new BoundingBoxImpl(topLeft, bottomRight);
        this.fieldOfView = new BoundingBoxImpl(tmp.getCenter(), tmp.getWidth() * FOV_SCALE,
                tmp.getHeight() * FOV_SCALE);
        super.setVelocity(VEL);
    }

    /**
     * Enemy constructor.
     * 
     * @param topLeft     corner of the enemy bounding box
     * @param bottomRight corner of the enemy bounding box
     * @param v           current direction
     */
    public AbstractEnemy(final Point topLeft, final Point bottomRight, final Set<Vector2d> v) {
        super(topLeft, bottomRight, v);
        final BoundingBox tmp = new BoundingBoxImpl(topLeft, bottomRight);
        this.fieldOfView = new BoundingBoxImpl(tmp.getCenter(), tmp.getWidth() * FOV_SCALE,
                tmp.getHeight() * FOV_SCALE);
        super.setVelocity(VEL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoundingBox getFieldOfView() {
        return new BoundingBoxImpl(this.fieldOfView.getTopLeft(), this.fieldOfView.getBottomRight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFieldOfView(final BoundingBox fov) {
        this.fieldOfView = new BoundingBoxImpl(fov.getTopLeft(), fov.getBottomRight());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long delta) {
        super.update(delta);
        this.fieldOfView.setCenter(this.getPosition().sum(this.calculateSpeed().mul(delta * this.getVelocity())));
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void follow(final Player p) {

    }

}
