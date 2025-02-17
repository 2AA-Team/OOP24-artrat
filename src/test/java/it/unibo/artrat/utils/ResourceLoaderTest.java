package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileWriter;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

/**
 * tester for the resource loader.
 * (Yaml reading)
 */
class ResourceLoaderTest {
    private final String configPath = "src" + File.separator
            + "test" + File.separator
            + "java" + File.separator
            + "it" + File.separator
            + "unibo" + File.separator
            + "artrat" + File.separator
            + "utils" + File.separator
            + "resourceLoaderTest.yaml";

    /**
     * test loading config path.
     */
    @Test
    void testLoading() {
        final ResourceLoader<String, Integer> resLoad = new ResourceLoaderImpl<>();
        assertThrows(IOException.class, () -> resLoad.setConfigPath(configPath + ".exe"));
    }

    /**
     * test reading config data.
     * 
     */
    @Test
    void testReading() {
        final ResourceLoader<String, Integer> resLoad = new ResourceLoaderImpl<>();
        try {
            try (FileWriter writer = new FileWriter(configPath, StandardCharsets.UTF_8)) {
                writer.write("");
            }
            assertThrows(NullPointerException.class, () -> resLoad.setConfigPath(configPath));
            try (FileWriter writer = new FileWriter(configPath, StandardCharsets.UTF_8)) {
                writer.write("NULL: ");
            }
            assertThrows(NullPointerException.class, () -> resLoad.setConfigPath(configPath));
            try (FileWriter writer = new FileWriter(configPath, StandardCharsets.UTF_8)) {
                writer.write("ONE: 1");
            }
            resLoad.setConfigPath(configPath);
            assertEquals(1, resLoad.getConfig("ONE"));
            assertThrows(IllegalStateException.class, () -> resLoad.getConfig("TWO"));
        } catch (IOException e) {
            fail();
        }
    }
}
