package it.unibo.artrat.model.impl.world;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.GameObjectFactory;
import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.api.world.RoomBuilder;
import it.unibo.artrat.model.api.world.roomgeneration.ObjectInsertionStrategy;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.GameObjectFactoryImpl;
import it.unibo.artrat.model.impl.world.roomgeneration.ObjectInsertionRandom;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationEmpty;
import it.unibo.artrat.utils.impl.Point;

/**
 * class that implements room interface to rapresent a square room.
 * 
 * @author Matteo Tonelli
 */
public final class RoomImpl implements Room {

    private final Set<GameObject> roomStructure = new HashSet<>();
    private final Set<Enemy> roomEnemies = new HashSet<>();
    private final Set<Collectable> roomCollectables = new HashSet<>();

    /**
     * constructor using the builder.
     * 
     * @param builder room builder
     */
    private RoomImpl(final RoomBuilderImpl builder) {
        final GameObjectFactory factory = new GameObjectFactoryImpl();
        roomStructure.addAll(builder.generationStrat.generateRoomSet(builder.size));
        roomEnemies.addAll(builder.enemyStrat.insertMultipleObject(
                Stream.concat(roomStructure.stream(), roomCollectables.stream())
                        .collect(Collectors.toSet()),
                builder.size,
                builder.numEnemies,
                factory::getRandomEnemy));
        roomCollectables.addAll(builder.collectableStrat.insertMultipleObject(
                Stream.concat(roomStructure.stream(), roomEnemies.stream())
                        .collect(Collectors.toSet()),
                builder.size,
                builder.numCollectables,
                factory::getCollectable));
        this.createPassage(builder);
    }

    private void createPassage(final RoomBuilderImpl builder) {
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
    public static class RoomBuilderImpl implements RoomBuilder {

        private RoomGenerationStrategy generationStrat;
        private ObjectInsertionStrategy<Enemy> enemyStrat;
        private ObjectInsertionStrategy<Collectable> collectableStrat;
        private int numEnemies;
        private int numCollectables;
        private int size;
        private boolean upPassage;
        private boolean rightPassage;
        private boolean downPassage;
        private boolean leftPassage;

        /**
         * constructor that defines standard variable.
         */
        public RoomBuilderImpl() {
            generationStrat = new RoomGenerationEmpty();
            enemyStrat = new ObjectInsertionRandom<>();
            collectableStrat = new ObjectInsertionRandom<>();
            numEnemies = 0;
            numCollectables = 0;
            size = 4;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RoomBuilder insertRoomSize(final int size) {
            if (size <= 2) {
                throw new IllegalArgumentException("Size not valid: " + size);
            }
            this.size = size;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RoomBuilder insertGenerationStrategy(final RoomGenerationStrategy generationStrat) {
            if (generationStrat == null) {
                throw new IllegalArgumentException("Generation strategy cannot be null.");
            }
            this.generationStrat = generationStrat;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RoomBuilder insertNumberOfEnemy(final int numEnemies) {
            if (numEnemies < 0) {
                throw new IllegalArgumentException("Number of enemies not valid: " + numEnemies);
            }
            this.numEnemies = numEnemies;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RoomBuilder insertNumberOfCollectables(final int numCollectables) {
            if (numCollectables < 0) {
                throw new IllegalArgumentException("Number of collectables not valid: " + numCollectables);
            }
            this.numCollectables = numCollectables;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RoomBuilder insertInsertionStrategyCollectables(final ObjectInsertionStrategy<Collectable> insertStrat) {
            if (insertStrat == null) {
                throw new IllegalArgumentException("Insertion strategy cannot be null.");
            }
            this.collectableStrat = insertStrat.cloneStrategy();
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RoomBuilder insertInsertionStrategyEnemy(final ObjectInsertionStrategy<Enemy> insertStrat) {
            if (insertStrat == null) {
                throw new IllegalArgumentException("Insertion strategy cannot be null.");
            }
            this.enemyStrat = insertStrat.cloneStrategy();
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
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
         * {@inheritDoc}
         */
        @Override
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
    public Set<Collectable> getCollectables() {
        return new HashSet<>(this.roomCollectables);
    }

}
