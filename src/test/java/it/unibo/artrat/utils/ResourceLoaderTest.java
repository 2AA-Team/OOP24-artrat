package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

public class ResourceLoaderTest {
    private final String configPath = "src" + File.separator
            + "test" + File.separator
            + "java" + File.separator
            + "it" + File.separator
            + "unibo" + File.separator
            + "artrat" + File.separator
            + "utils" + File.separator
            + "test.yaml";

    @Test
    public void TestLoading() {
        ResourceLoader resLoad = new ResourceLoaderImpl();
        assertThrows(IOException.class, () -> resLoad.setConfigPath(configPath + ".exe"));
    }

    @Test
    public void TestReading() throws IOException {
        ResourceLoader resLoad = new ResourceLoaderImpl();
        FileWriter writer = new FileWriter(configPath);
        writer.write("");
        writer.close();
        assertThrows(NullPointerException.class, () -> resLoad.setConfigPath(configPath));
        writer = new FileWriter(configPath);
        writer.write("NULL: ");
        writer.close();
        assertThrows(NullPointerException.class, () -> resLoad.setConfigPath(configPath));
        writer = new FileWriter(configPath);
        writer.write("ONE: 1");
        writer.close();
        resLoad.setConfigPath(configPath);
        assertEquals(1, resLoad.getConfig("ONE"));
        assertThrows(IllegalStateException.class, () -> resLoad.getConfig("TWO"));
    }
}
