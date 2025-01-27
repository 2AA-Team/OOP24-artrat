package it.unibo.artrat.model.impl.world;

import java.util.Set;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.world.Room;

/**
 * implementation of interface Room.
 */
public class RoomImpl implements Room {

    private Set<GameObject> obstacles;
    private Set<GameObject> enemies;
    private Set<GameObject> valuable;

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getObstacles() {
        return obstacles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getEnemies() {
        return enemies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getValuableItem() {
        return valuable;
    }

}
