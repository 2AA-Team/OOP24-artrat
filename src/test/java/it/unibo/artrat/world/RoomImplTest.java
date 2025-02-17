package it.unibo.artrat.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import it.unibo.artrat.model.api.characters.AbstractEntity;
import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationEmpty;
import it.unibo.artrat.model.impl.world.RoomImpl;
import it.unibo.artrat.model.impl.world.roomgeneration.ObjectInsertionRandom;

class RoomImplTest {

    private Room room;
    private final static int SIZE = 5;

    @BeforeEach
    void setUp() {
        room = new RoomImpl.RoomBuilder()
                .insertRoomSize(SIZE)
                .insertGenerationStrategy(new RoomGenerationEmpty())
                .insertInsertionStrategyEnemy(new ObjectInsertionRandom<>())
                .insertInsertionStrategyValue(new ObjectInsertionRandom<>())
                .insertNumberOfEnemy(2)
                .insertNumberOfValues(3)
                .insertPassages(true, true, false, false)
                .build();
    }

    @Test
    @DisplayName("Test Room Creation with Valid Builder")
    void testRoomCreation() {
        assertNotNull(room, "The RoomImpl object should not be null");
        assertNotNull(room.getStructure(), "Room structure should not be null");
        final Set<AbstractEntity> enemies = room.getEnemies();
        assertNotNull(enemies, "Room enemies set should not be null");
        assertEquals(2, enemies.size(), "The number of enemies should be as set in the builder");
        final Set<AbstractGameObject> values = room.getValues();
        assertNotNull(values, "Room values set should not be null");
        assertEquals(3, values.size(), "The number of values should match what was set in the builder");
    }

    @Test
    @DisplayName("Test Room Passage Creation")
    void testRoomPassages() {
        final Set<AbstractGameObject> structure = room.getStructure();
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
    @DisplayName("Test Invalid values")
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilder().insertRoomSize(2).build();
        }, "Should throw an exception when the room size is too small");
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilder().insertNumberOfEnemy(-1).build();
        }, "Should throw an exception when the number of enemies is negative");
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilder().insertNumberOfValues(-1).build();
        }, "Should throw an exception when the number of values is negative");
    }

    @Test
    @DisplayName("Test Null Generation Strategy Throws Exception")
    void testNullGenerationStrategy() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilder().insertGenerationStrategy(null).build();
        }, "Should throw an exception when the generation strategy is null");
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilder().insertInsertionStrategyEnemy(null).build();
        }, "Should throw an exception when the enemy insertion strategy is null");
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomImpl.RoomBuilder().insertInsertionStrategyValue(null).build();
        }, "Should throw an exception when the value insertion strategy is null");
    }
}
