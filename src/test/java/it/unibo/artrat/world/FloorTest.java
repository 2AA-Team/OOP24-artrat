package it.unibo.artrat.world;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.world.FloorImpl;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

class FloorTest {

    private Floor floor;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("floorImplTest.yaml");

        final ResourceLoader<String, Double> resourceLoader;
        resourceLoader = new ResourceLoaderImpl<>();
        resourceLoader.setConfigPath(inputStream);
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
        assertNotNull(floor.getCollectables(), "Collectables should not be null");
        assertFalse(floor.getWalls().isEmpty(), "Walls should be present in the floor structure");
    }

    @Test
    void testInvalidConfig() throws IOException, URISyntaxException {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("floorImplTestNeg.yaml");
        final ResourceLoader<String, Double> invalidLoader = new ResourceLoaderImpl<>();
        invalidLoader.setConfigPath(inputStream);
        assertThrows(IllegalStateException.class, () -> new FloorImpl(invalidLoader),
                "Should throw an exception if configuration values are invalid");
    }

    @Test
    void testGenerateRoomsIOException() {
        assertThrows(IllegalStateException.class, () -> {
            final Floor faultyFloor = new FloorImpl(new ResourceLoaderImpl<>());
            faultyFloor.generateFloorSet();
        }, "Should throw an IOException if the resource file is missing");
    }
}
