package it.unibo.artrat.model.impl.world.roomgeneration;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import it.unibo.artrat.model.api.AbstractGameObject;
import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.model.api.world.roomgeneration.ObjectInsertionStrategy;
import it.unibo.artrat.model.impl.GameObjectFactoryImpl;
import it.unibo.artrat.model.impl.world.RoomSymbols;

/**
 * random insertion method.
 */
public class ObjectInsertionRandom implements ObjectInsertionStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> insertMultipleObject(
            final Set<AbstractGameObject> baseRoom,
            final int roomSize,
            final RoomSymbols obj,
            final int addNumber) {
        final Set<AbstractGameObject> newObjects = new HashSet<>();
        final Random rd = new Random();
        while (newObjects.size() < addNumber) {
            final int x = rd.nextInt(roomSize - 2) + 1;
            final int y = rd.nextInt(roomSize - 2) + 1;

            if (baseRoom.stream().noneMatch((o) -> {
                return o.getPosition().getX() == x && o.getPosition().getY() == y;
            })) {
                final GameObjectFactory factory = new GameObjectFactoryImpl();
                switch (obj) {
                    case RoomSymbols.ENEMY:
                        newObjects.add(factory.getEnemy(x, y));
                        break;
                    case RoomSymbols.VALUE:
                        newObjects.add(factory.getValue(x, y));
                        break;
                    default:
                        throw new IllegalArgumentException(obj + " not implemented.");
                }
            }
        }
        return newObjects;
    }

}
