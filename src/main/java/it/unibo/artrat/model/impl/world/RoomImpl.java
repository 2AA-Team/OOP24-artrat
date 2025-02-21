package it.unibo.artrat.model.impl.world;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.api.world.roomgeneration.ObjectInsertionStrategy;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.GameObjectFactoryImpl;
import it.unibo.artrat.model.impl.world.roomgeneration.ObjectInsertionRandom;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationEmpty;
import it.unibo.artrat.utils.impl.Point;

/**
 * class that implements room interface to rapresent a square room.
 */
public final class RoomImpl implements Room {

    private final Set<GameObject> roomStructure = new HashSet<>();
    private final Set<Enemy> roomEnemies = new HashSet<>();
    private final Set<GameObject> roomValues = new HashSet<>();

    /**
     * constructor using the builder.
     * 
     * @param builder room builder
     */
    private RoomImpl(final RoomBuilder builder) {
        final GameObjectFactory factory = new GameObjectFactoryImpl();
        roomStructure.addAll(builder.generationStrat.generateRoomSet(builder.size));
        roomEnemies.addAll(builder.enemyStrat.insertMultipleObject(
                Stream.concat(roomStructure.stream(), roomValues.stream())
                        .collect(Collectors.toSet()),
                builder.size,
                builder.numEnemies,
                factory::getRandomEnemy));
        roomValues.addAll(builder.valuableStrat.insertMultipleObject(
                Stream.concat(roomStructure.stream(), roomEnemies.stream())
                        .collect(Collectors.toSet()),
                builder.size,
                builder.numValues,
                factory::getPicture));
        this.createPassage(builder);
    }

    private void createPassage(final RoomBuilder builder) {
        final double averagePassage = Math.floor((double) builder.size / 2);
        boolean tmpU = builder.upPassage;
        boolean tmpR = builder.rightPassage;
        boolean tmpD = builder.downPassage;
        boolean tmpL = builder.leftPassage;
        for (int i = 0; i < builder.size - 1 && (tmpU || tmpR || tmpD || tmpL); i++) {
            final int tmpI = i;
            if (tmpU) {
                tmpU = this.roomStructure.removeIf((o) -> o.getPosition().equals(new Point(averagePassage, tmpI))
                        || o.getPosition().equals(new Point(averagePassage - 1, tmpI)));
            }
            if (tmpR) {
                tmpR = this.roomStructure
                        .removeIf((o) -> o.getPosition().equals(new Point(builder.size - tmpI - 1, averagePassage))
                                || o.getPosition().equals(new Point(builder.size - tmpI - 1, averagePassage - 1)));
            }
            if (tmpD) {
                tmpD = this.roomStructure.removeIf((o) -> o.getPosition().equals(new Point(averagePassage,
                        builder.size - tmpI - 1))
                        || o.getPosition().equals(new Point(averagePassage - 1, builder.size - tmpI - 1)));
            }
            if (tmpL) {
                tmpL = this.roomStructure.removeIf((o) -> o.getPosition().equals(new Point(tmpI, averagePassage))
                        || o.getPosition().equals(new Point(tmpI, averagePassage - 1)));
            }
        }

    }

    /**
     * inner class to define a builder for a room.
     */
    public static class RoomBuilder {

        private RoomGenerationStrategy generationStrat;
        private ObjectInsertionStrategy<Enemy> enemyStrat;
        private ObjectInsertionStrategy<GameObject> valuableStrat;
        private int numEnemies;
        private int numValues;
        private int size;
        private boolean upPassage;
        private boolean rightPassage;
        private boolean downPassage;
        private boolean leftPassage;

        /**
         * constructor that defines standard variable.
         */
        public RoomBuilder() {
            generationStrat = new RoomGenerationEmpty();
            enemyStrat = new ObjectInsertionRandom<>();
            valuableStrat = new ObjectInsertionRandom<>();
            numEnemies = 0;
            numValues = 0;
            size = 4;
        }

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
         * set the insertion strategy for all the valuable objects.
         * 
         * @param insertStrat insertion strategy class
         * @return this room builder
         */
        public RoomBuilder insertInsertionStrategyValue(final ObjectInsertionStrategy<GameObject> insertStrat) {
            if (insertStrat == null) {
                throw new IllegalArgumentException("Insertion strategy cannot be null.");
            }
            this.valuableStrat = insertStrat.cloneStrategy();
            return this;
        }

        /**
         * set the insertion strategy for all the enemies in the room.
         * 
         * @param insertStrat insertion strategy class
         * @return this room builder
         */
        public RoomBuilder insertInsertionStrategyEnemy(final ObjectInsertionStrategy<Enemy> insertStrat) {
            if (insertStrat == null) {
                throw new IllegalArgumentException("Insertion strategy cannot be null.");
            }
            this.enemyStrat = insertStrat.cloneStrategy();
            return this;
        }

        /**
         * set the passages of the room.
         * 
         * @param upRoom    upwards passage
         * @param rightRoom passage to the right
         * @param downRoom  downward passage
         * @param leftRoom  passage to the left
         * @return this room builder
         */
        public RoomBuilder insertPassages(final boolean upRoom,
                final boolean rightRoom,
                final boolean downRoom,
                final boolean leftRoom) {
            this.upPassage = upRoom;
            this.rightPassage = rightRoom;
            this.downPassage = downRoom;
            this.leftPassage = leftRoom;
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
    public Set<GameObject> getStructure() {
        return new HashSet<>(this.roomStructure);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Enemy> getEnemies() {
        return new HashSet<>(this.roomEnemies);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getValues() {
        return new HashSet<>(this.roomValues);
    }

}
