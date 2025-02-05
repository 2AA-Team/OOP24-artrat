package it.unibo.artrat.model.impl.world;

import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.api.world.RoomBuilder;
import it.unibo.artrat.model.api.world.roomgeneration.ObjectInsertionStrategy;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.world.roomgeneration.ObjectInsertionRandom;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationEmpty;

public class RoomImpl implements Room {

    private char[][] room;

    private RoomImpl(RoomBuilderImpl builder) {
        room = builder.generationStrat.generateCharRoom(builder.size);
        for (int i = 0; i < builder.numEnemies; i++) {
            room = builder.insertStrat.insertSingleObject(room, RoomSymbols.ENEMY.getSymbol());
        }
        for (int i = 0; i < builder.numValues; i++) {
            room = builder.insertStrat.insertSingleObject(room, RoomSymbols.VALUE.getSymbol());
        }
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
    public char[][] getRoomLaout() {
        return room.clone();
    }

    @Override
    public void print() {
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room.length; j++) {
                System.out.print(room[i][j]);
            }
            System.out.println("");
        }
    }
}
