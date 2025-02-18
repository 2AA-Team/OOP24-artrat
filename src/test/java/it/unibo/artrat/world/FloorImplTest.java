package it.unibo.artrat.world;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import it.unibo.artrat.model.impl.world.FloorImpl;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

class FloorImplTest {

    private Floor floor;
    private final String baseConfigPath = "src" + File.separator
            + "test" + File.separator
            + "java" + File.separator
            + "it" + File.separator
            + "unibo" + File.separator
            + "artrat" + File.separator
            + "world" + File.separator;

    @BeforeEach
    void setUp() throws IOException {
        final ResourceLoaderImpl<String, Double> resourceLoader;
        resourceLoader = new ResourceLoaderImpl<>();
        resourceLoader.setConfigPath(baseConfigPath + "floorImplTest.yaml");
        floor = new FloorImpl(resourceLoader);
    }

    @Test
    void testFloorCreation() {
        assertNotNull(floor, "The FloorImpl object should not be null");
    }

    @Test
    void testGenerateFloorSet() {
        assertDoesNotThrow(floor::generateFloorSet, "Generating the floor should not throw exceptions");
        assertNotNull(floor.getWalls(), "Walls should not be null");
        assertNotNull(floor.getEnemies(), "Enemies should not be null");
        assertNotNull(floor.getValues(), "Values should not be null");
        assertFalse(floor.getWalls().isEmpty(), "Walls should be present in the floor structure");
    }

    @Test
    void testInvalidConfig() throws IOException {
        final ResourceLoaderImpl<String, Double> invalidLoader = new ResourceLoaderImpl<>();
        invalidLoader.setConfigPath(baseConfigPath + "floorImplTestNeg.yaml");
        assertThrows(IOException.class, () -> new FloorImpl(invalidLoader),
                "Should throw an exception if configuration values are invalid");
    }

    @Test
    void testGenerateRoomsIOException() {
        assertThrows(IllegalStateException.class, () -> {
            final FloorImpl faultyFloor = new FloorImpl(new ResourceLoaderImpl<>());
            faultyFloor.generateFloorSet();
        }, "Should throw an IOException if the resource file is missing");
    }
}
