package it.unibo.artrat.model.api;

/**
 * Abstract class of GameObject class.
 */
public abstract class AbstractGameObject implements GameObject {
    private Point position;

    /**
     * @return current game object position
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Set the current game object position.
     * 
     * @param position
     */
    public void setPosition(final Point position) {
        this.position = position;
    }

}
