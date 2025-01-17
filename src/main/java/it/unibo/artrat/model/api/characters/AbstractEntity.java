package it.unibo.artrat.model.api.characters;

import it.unibo.artrat.model.api.AbstractGameObject;
import it.unibo.artrat.model.impl.characters.Physics;

/**
 * Abstract class for entities variables set up.
 */
public abstract class AbstractEntity extends AbstractGameObject implements Entity {
    private final Physics entityMovment = new Physics();
    // private Direction dir;s

    @Override
    public void move() {

    }
}
