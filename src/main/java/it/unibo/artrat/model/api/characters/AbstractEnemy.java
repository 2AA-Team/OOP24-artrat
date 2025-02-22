package it.unibo.artrat.model.api.characters;

import java.util.HashSet;
import java.util.Set;

import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

public abstract class AbstractEnemy extends AbstractEntity implements Enemy {
    private static final int FOV_SCALE = 5;
    private boolean follow = false;
    private BoundingBox fieldOfView;
    private static final double VEL = 0.005;

    /**
     * {@inheritDoc}
     */
    public AbstractEnemy(Point center, double width, double height, Set<Vector2d> v) {
        super(center, width, height, v);
        this.fieldOfView = new BoundingBoxImpl(center, width * FOV_SCALE, height * FOV_SCALE);
        setVelocity(VEL);
    }

    /**
     * {@inheritDoc}
     */
    public AbstractEnemy(final Point topLeft, final Point bottomRight) {
        super(topLeft, bottomRight, new HashSet<>());
        final BoundingBox tmp = this.getBoundingBox();
        this.fieldOfView = new BoundingBoxImpl(tmp.getCenter(), tmp.getWidth() * FOV_SCALE,
                tmp.getHeight() * FOV_SCALE);
        setVelocity(VEL);
    }

    /**
     * {@inheritDoc}
     */
    public AbstractEnemy(final Point topLeft, final Point bottomRight, final Set<Vector2d> v) {
        super(topLeft, bottomRight, v);
        final BoundingBox tmp = this.getBoundingBox();
        this.fieldOfView = new BoundingBoxImpl(tmp.getCenter(), tmp.getWidth() * FOV_SCALE,
                tmp.getHeight() * FOV_SCALE);
        setVelocity(VEL);
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
    public boolean isFollowing() {
        return follow;
    }

    @Override
    public void trigger(boolean follow) {
        this.follow = follow;
    }

    @Override
    public void setFieldOfView(BoundingBox fov) {
        this.fieldOfView = new BoundingBoxImpl(fov.getTopLeft(), fov.getBottomRight());

    }

    @Override
    public void update(long delta) {
        super.update(delta);
        this.fieldOfView.setCenter(this.getPosition().sum(this.calculateSpeed().mul(delta * this.getVelocity())));
    }

    @Override
    public void follow(Player p) {
        // TODO Auto-generated method stub

    }

}
