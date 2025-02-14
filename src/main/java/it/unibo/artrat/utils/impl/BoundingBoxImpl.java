package it.unibo.artrat.utils.impl;

import it.unibo.artrat.utils.api.BoundingBox;

/**
 * Class of bounding box as a rectangle.
 * 
 * @author Samuele Trapani
 */
public class BoundingBoxImpl implements BoundingBox {
    private Point topLeft;
    private Point bottomRight;
    private final double height;
    private final double width;

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.width;

    }

    /**
     * Bounding box constructor.
     * 
     * @param topLeft     top left bounding box corner center
     * @param bottomRight bottom right bounding box corner center
     */
    public BoundingBoxImpl(final Point topLeft, final Point bottomRight) {
        this.bottomRight = new Point(bottomRight);
        this.topLeft = new Point(topLeft);
        this.width = Math.abs(this.bottomRight.getY() - this.topLeft.getY());
        this.height = Math.abs(this.topLeft.getX() - this.bottomRight.getX());
    }

    /**
     * Bounding box constructor starting from a center point.
     * 
     * @param center rectangle center
     * @param width  rectangle width
     * @param height rectangle height
     */
    public BoundingBoxImpl(final Point center, final double width, final double height) {
        this.width = width;
        this.height = height;
        this.topLeft = new Point(center.getX() - width / 2, center.getY() - height / 2);
        this.bottomRight = new Point(center.getX() + width / 2, center.getY() + height / 2);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getTopLeft() {
        return new Point(this.topLeft);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getBottomRight() {
        return new Point(this.bottomRight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isColliding(final BoundingBoxImpl box) {
        return !(this.topLeft.getX() > box.getBottomRight().getX()
                || this.bottomRight.getX() < box.getTopLeft().getX()
                || this.topLeft.getY() > box.getBottomRight().getY()
                || this.bottomRight.getY() < box.getTopLeft().getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getCenter() {

        return new Point(this.topLeft.getX() + this.getWidth() / 2, this.topLeft.getY() + height / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCenter(final Point center) {
        this.topLeft = new BoundingBoxImpl(center, this.getWidth(), this.getHeight()).getTopLeft();
        this.bottomRight = new BoundingBoxImpl(center, this.getWidth(), this.getHeight()).getBottomRight();
    }

}
