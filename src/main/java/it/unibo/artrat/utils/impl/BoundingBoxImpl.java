package it.unibo.artrat.utils.impl;

import it.unibo.artrat.utils.api.BoundingBox;

/**
 * Class of bounding box as a rectangle.
 */
public class BoundingBoxImpl implements BoundingBox {
    private Point bottomLeft;
    private Point topRight;
    private final double height;
    private final double width;

    /**
     * Bounding box constructor.
     * 
     * @param bottomLeft bottom left bounding box corner center
     * @param topRight   top right bounding box corner center
     */
    public BoundingBoxImpl(final Point bottomLeft, final Point topRight) {
        this.topRight = new Point(topRight);
        this.bottomLeft = new Point(bottomLeft);
        this.width = Math.abs(this.topRight.getY() - this.bottomLeft.getY());
        this.height = Math.abs(this.bottomLeft.getX() - this.topRight.getX());
    }

    /**
     * Bounding box constructor starting from a center point.
     * 
     * @param center rectangle center
     * @param width  rectangle width
     * @param height rectangle height
     */
    public BoundingBoxImpl(final Point center, final double width, final double height) {
        this.topRight = new Point(center.getX() + width / 2, center.getY() - height / 2);
        this.bottomLeft = new Point(center.getX() - width / 2, center.getY() + height / 2);
        this.width = width;
        this.height = height;
    }

    /**
     * @return bottom left bounding box corner
     */
    public Point getbottomLeft() {
        return new Point(this.bottomLeft);
    }

    /**
     * @return Bottom right bounding box corner
     */
    public Point getTopRight() {
        return new Point(this.topRight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isColliding(final BoundingBoxImpl box) {
        return this.getTopRight().getY() < box.bottomLeft.getY()
                || this.bottomLeft.getY() > box.topRight.getY() && this.topRight.getX() < box.bottomLeft.getX()
                || this.bottomLeft.getX() > box.topRight.getX();
    }

    /**
     * 
     * @return Center point of the bounding box.
     */
    public Point getCenter() {

        return new Point(this.bottomLeft.getX() + width / 2, this.bottomLeft.getY() + height / 2);
    }

    /**
     * Set the center of the bounding box.
     * 
     * @param center center point
     */
    public void setCenter(final Point center) {
        this.topRight = new Point(center.getX() + this.width / 2, center.getY() - this.height / 2);
        this.bottomLeft = new Point(center.getX() - this.width / 2, center.getY() + this.height / 2);
    }

}
