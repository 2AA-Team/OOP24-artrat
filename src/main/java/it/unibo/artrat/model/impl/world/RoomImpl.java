package it.unibo.artrat.model.impl.world;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import it.unibo.artrat.model.api.AbstractGameObject;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.api.world.RoomBuilder;
import it.unibo.artrat.model.api.world.roomgeneration.ObjectInsertionStrategy;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.world.roomgeneration.ObjectInsertionRandom;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationEmpty;

public class RoomImpl implements Room {

    private Set<AbstractGameObject> roomStructure = new HashSet<>();
    private Set<AbstractGameObject> roomEnemies = new HashSet<>();
    private Set<AbstractGameObject> roomValues = new HashSet<>();

    private RoomImpl(RoomBuilderImpl builder) {
        roomStructure = builder.generationStrat.generateRoomSet(builder.size);
        roomEnemies = builder.insertStrat.insertMultipleObject(
                Stream.concat(roomStructure.stream(), roomValues.stream())
                        .collect(Collectors.toSet()),
                builder.size,
                RoomSymbols.ENEMY,
                builder.numEnemies);
        roomValues = builder.insertStrat.insertMultipleObject(
                Stream.concat(roomStructure.stream(), roomEnemies.stream())
                        .collect(Collectors.toSet()),
                builder.size,
                RoomSymbols.VALUE,
                builder.numValues);
    }

    public static class RoomBuilderImpl implements RoomBuilder {

        private RoomGenerationStrategy generationStrat = new RoomGenerationEmpty();
        private ObjectInsertionStrategy insertStrat = new ObjectInsertionRandom();
        private int numEnemies = 0;
        private int numValues = 0;
        private int size = 5;

        public RoomBuilder setRoomSize(int size) {
            if (size <= 2) {
                throw new IllegalArgumentException("Size not valid: " + size);
            }
            this.size = size;
            return this;
        }

        public RoomBuilder setGenerationStrategy(RoomGenerationStrategy generationStrat) {
            if (generationStrat == null) {
                throw new IllegalArgumentException("Generation strategy cannot be null.");
            }
            this.generationStrat = generationStrat;
            return this;
        }

        public RoomBuilder setNumberOfEnemy(int numEnemies) {
            if (numEnemies < 0) {
                throw new IllegalArgumentException("Number of enemies not valid: " + numEnemies);
            }
            this.numEnemies = numEnemies;
            return this;
        }

        public RoomBuilder setNumberOfValues(int numValues) {
            if (numValues < 0) {
                throw new IllegalArgumentException("Number of values not valid: " + numValues);
            }
            this.numValues = numValues;
            return this;
        }

        public RoomBuilder setInsertionStrategy(ObjectInsertionStrategy insertStrat) {
            if (insertStrat == null) {
                throw new IllegalArgumentException("Insertion strategy cannot be null.");
            }
            this.insertStrat = insertStrat;
            return this;
        }

        public Room build() {
            return new RoomImpl(this);
        }
    }

    @Override
    public Set<AbstractGameObject> getStructure() {
        return new HashSet<>(this.roomStructure);
    }

    @Override
    public Set<AbstractGameObject> getEnemies() {
        return new HashSet<>(this.roomEnemies);
    }

    @Override
    public Set<AbstractGameObject> getValues() {
        return new HashSet<>(this.roomValues);
    }
}
