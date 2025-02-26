package it.unibo.artrat.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.impl.characters.characters.Enemy;
import it.unibo.artrat.model.impl.world.RoomImpl;
import it.unibo.artrat.model.impl.world.roomgeneration.ObjectInsertionRandom;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationEmpty;

class RoomTest {

    private Room room;
    private static final int SIZE = 5;

    @BeforeEach
    void setUp() {
        room = new RoomImpl.RoomBuilderImpl()
                .insertRoomSize(SIZE)
                .insertGenerationStrategy(new RoomGenerationEmpty())
                .insertInsertionStrategyEnemy(new ObjectInsertionRandom<>())
                .insertInsertionStrategyCollectables(new ObjectInsertionRandom<>())
                .insertNumberOfEnemy(2)
                .insertNumberOfCollectables(3)
                .insertPassages(true, true, false, false)
                .build();
    }

    @Test
    void testRoomCreation() {
        assertNotNull(room, "The RoomImpl object should not be null");
        assertNotNull(room.getStructure(), "Room structure should not be null");
        final Set<Enemy> enemies = room.getEnemies();
        assertNotNull(enemies, "Room enemies set should not be null");
        assertEquals(2, enemies.size(), "The number of enemies should be as set in the builder");
        final Set<Collectable> collectables = room.getCollectables();
        assertNotNull(collectables, "Room collectables set should not be null");
        assertEquals(3, collectables.size(), "The number of collectables should match what was set in the builder");
    }

    @Test
    void testRoomPassages() {
        final Set<GameObject> structure = room.getStructure();
        final long upWalls = structure.stream()
                .filter(obj -> obj.getPosition().getY() == 0).count();
        final long rightWalls = structure.stream()
                .filter(obj -> obj.getPosition().getX() == 4).count();
        final long leftWalls = structure.stream()
                .filter(obj -> obj.getPosition().getX() == 0).count();
        assertFalse(leftWalls < SIZE, "The left passage should be closed");
        assertTrue(upWalls < SIZE, "The top passage should be open");
        assertTrue(rightWalls < SIZE, "The right passage should be open");
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilderImpl().insertRoomSize(2).build();
        }, "Should throw an exception when the room size is too small");
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilderImpl().insertNumberOfEnemy(-1).build();
        }, "Should throw an exception when the number of enemies is negative");
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilderImpl().insertNumberOfCollectables(-1).build();
        }, "Should throw an exception when the number of collectables is negative");
    }

    @Test
    void testNullGenerationStrategy() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilderImpl().insertGenerationStrategy(null).build();
        }, "Should throw an exception when the generation strategy is null");
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilderImpl().insertInsertionStrategyEnemy(null).build();
        }, "Should throw an exception when the enemy insertion strategy is null");
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilderImpl().insertInsertionStrategyCollectables(null).build();
        }, "Should throw an exception when the collectables insertion strategy is null");
    }
}
