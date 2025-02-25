package it.unibo.artrat.model.api.world;

import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.world.roomgeneration.ObjectInsertionStrategy;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;

/**
 * Room Builder Interface.
 */
public interface RoomBuilder {
    /**
     * set the room size.
     * 
     * @param size room side size
     * @return this room builder
     */
    RoomBuilder insertRoomSize(int size);

    /**
     * set the generation strategy for the room structure.
     * 
     * @param generationStrat generation strategy class
     * @return this room builder
     */
    RoomBuilder insertGenerationStrategy(RoomGenerationStrategy generationStrat);

    /**
     * set the number of enemies to add.
     * 
     * @param numEnemies number of enemies
     * @return this room builder
     */
    RoomBuilder insertNumberOfEnemy(int numEnemies);

    /**
     * set the number of valuable object to add.
     * 
     * @param numValues number of valuable object
     * @return this room builder
     */
    RoomBuilder insertNumberOfValues(int numValues);

    /**
     * set the insertion strategy for all the valuable objects.
     * 
     * @param insertStrat insertion strategy class
     * @return this room builder
     */
    RoomBuilder insertInsertionStrategyValue(ObjectInsertionStrategy<Collectable> insertStrat);

    /**
     * set the insertion strategy for all the enemies in the room.
     * 
     * @param insertStrat insertion strategy class
     * @return this room builder
     */
    RoomBuilder insertInsertionStrategyEnemy(ObjectInsertionStrategy<Enemy> insertStrat);

    /**
     * set the passages of the room.
     * 
     * @param upRoom    upwards passage
     * @param rightRoom passage to the right
     * @param downRoom  downward passage
     * @param leftRoom  passage to the left
     * @return this room builder
     */
    RoomBuilder insertPassages(boolean upRoom,
            boolean rightRoom,
            boolean downRoom,
            boolean leftRoom);

    /**
     * build a new room with the setted arguments.
     * 
     * @return a new room
     */
    Room build();
}
