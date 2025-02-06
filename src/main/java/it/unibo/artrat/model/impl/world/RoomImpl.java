package it.unibo.artrat.model.impl.world;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import it.unibo.artrat.model.api.AbstractGameObject;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.api.world.roomgeneration.ObjectInsertionStrategy;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.world.roomgeneration.ObjectInsertionRandom;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationEmpty;

/**
 * class that implements room interface to rapresent a square room.
 */
public final class RoomImpl implements Room {

    private final Set<AbstractGameObject> roomStructure;
    private final Set<AbstractGameObject> roomEnemies = new HashSet<>();
    private final Set<AbstractGameObject> roomValues = new HashSet<>();

    /**
     * constructor using the builder.
     * 
     * @param builder room builder
     */
    private RoomImpl(final RoomBuilder builder) {
        roomStructure = new HashSet<>(builder.generationStrat.generateRoomSet(builder.size));
        roomEnemies.addAll(builder.insertStrat.insertMultipleObject(
                Stream.concat(roomStructure.stream(), roomValues.stream())
                        .collect(Collectors.toSet()),
                builder.size,
                RoomSymbols.ENEMY,
                builder.numEnemies));
        roomValues.addAll(builder.insertStrat.insertMultipleObject(
                Stream.concat(roomStructure.stream(), roomEnemies.stream())
                        .collect(Collectors.toSet()),
                builder.size,
                RoomSymbols.VALUE,
                builder.numValues));
    }

    /**
     * inner class to define a builder for a room.
     */
    public static class RoomBuilder {

        private RoomGenerationStrategy generationStrat = new RoomGenerationEmpty();
        private ObjectInsertionStrategy insertStrat = new ObjectInsertionRandom();
        private int numEnemies = 0;
        private int numValues = 0;
        private int size = 4;

        /**
         * set the room size.
         * 
         * @param size room side size
         * @return this room builder
         */
        public RoomBuilder insertRoomSize(final int size) {
            if (size <= 2) {
                throw new IllegalArgumentException("Size not valid: " + size);
            }
            this.size = size;
            return this;
        }

        /**
         * set the generation strategy for the room structure.
         * 
         * @param generationStrat generation strategy class
         * @return this room builder
         */
        public RoomBuilder insertGenerationStrategy(final RoomGenerationStrategy generationStrat) {
            if (generationStrat == null) {
                throw new IllegalArgumentException("Generation strategy cannot be null.");
            }
            this.generationStrat = generationStrat;
            return this;
        }

        /**
         * set the number of enemies to add.
         * 
         * @param numEnemies number of enemies
         * @return this room builder
         */
        public RoomBuilder insertNumberOfEnemy(final int numEnemies) {
            if (numEnemies < 0) {
                throw new IllegalArgumentException("Number of enemies not valid: " + numEnemies);
            }
            this.numEnemies = numEnemies;
            return this;
        }

        /**
         * set the number of valuable object to add.
         * 
         * @param numValues number of valuable object
         * @return this room builder
         */
        public RoomBuilder insertNumberOfValues(final int numValues) {
            if (numValues < 0) {
                throw new IllegalArgumentException("Number of values not valid: " + numValues);
            }
            this.numValues = numValues;
            return this;
        }

        /**
         * set the insertion strategy for all the objects except the wall.
         * 
         * @param insertStrat insertion strategy class
         * @return this room builder
         */
        public RoomBuilder insertInsertionStrategy(final ObjectInsertionStrategy insertStrat) {
            if (insertStrat == null) {
                throw new IllegalArgumentException("Insertion strategy cannot be null.");
            }
            this.insertStrat = insertStrat;
            return this;
        }

        /**
         * build a new room with the setted arguments.
         * 
         * @return a new room
         */
        public Room build() {
            return new RoomImpl(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> getStructure() {
        return new HashSet<>(this.roomStructure);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> getEnemies() {
        return new HashSet<>(this.roomEnemies);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> getValues() {
        return new HashSet<>(this.roomValues);
    }
}
