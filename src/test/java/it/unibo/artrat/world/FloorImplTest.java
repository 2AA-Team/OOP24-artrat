package it.unibo.artrat.world;

import static org.junit.jupiter.api.Assertions.*;

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
    private ResourceLoaderImpl<String, Double> resourceLoader;
    private final String baseConfigPath = "src" + File.separator
            + "test" + File.separator
            + "java" + File.separator
            + "it" + File.separator
            + "unibo" + File.separator
            + "artrat" + File.separator
            + "world" + File.separator;

    @BeforeEach
    void setUp() throws IOException {
        resourceLoader = new ResourceLoaderImpl<>();
        resourceLoader.setConfigPath(baseConfigPath + "floorImplTest.yaml");
        floor = new FloorImpl(resourceLoader);
    }

    @Test
    @DisplayName("Test FloorImpl creation with valid configuration")
    void testFloorCreation() {
        assertNotNull(floor, "The FloorImpl object should not be null");
    }

    @Test
    @DisplayName("Test floor structure generation")
    void testGenerateFloorSet() {
        assertDoesNotThrow(() -> floor.generateFloorSet(), "Generating the floor should not throw exceptions");
        assertNotNull(floor.getWalls(), "Walls should not be null");
        assertNotNull(floor.getEnemies(), "Enemies should not be null");
        assertNotNull(floor.getValues(), "Values should not be null");
        assertFalse(floor.getWalls().isEmpty(), "Walls should be present in the floor structure");
    }

    @Test
    @DisplayName("Test invalid configuration handling")
    void testInvalidConfig() throws IOException {
        // Create a new resource loader with invalid values
        ResourceLoaderImpl<String, Double> invalidLoader = new ResourceLoaderImpl<>();
        invalidLoader.setConfigPath(baseConfigPath + "floorImplTestNeg.yaml");
        assertThrows(IOException.class, () -> new FloorImpl(invalidLoader),
                "Should throw an exception if configuration values are invalid");
    }

    @Test
    @DisplayName("Test IOException handling during room generation")
    void testGenerateRoomsIOException() {
        assertThrows(IllegalStateException.class, () -> {
            // Create a FloorImpl with a non-existing resource file
            FloorImpl faultyFloor = new FloorImpl(new ResourceLoaderImpl<>());
            faultyFloor.generateFloorSet();
        }, "Should throw an IOException if the resource file is missing");
    }
}
