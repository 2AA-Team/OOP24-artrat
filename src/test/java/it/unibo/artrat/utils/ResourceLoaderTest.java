package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

/**
 * tester for the resource loader.
 * (Yaml reading)
 */
class ResourceLoaderTest {

    /**
     * test loading config path.
     */
    @Test
    void testLoading() {
        final ResourceLoader<String, Integer> resLoad = new ResourceLoaderImpl<>();
        assertThrows(IllegalArgumentException.class, () -> resLoad.setConfigPath(new URI("file_not_exist")),
                "config file cannot be an exe");
    }

    /**
     * test reading config data.
     *
     */
    @Test
    void testReading() {
        final ResourceLoader<String, Integer> resLoad = new ResourceLoaderImpl<>();

        try {
            final URI uri = Thread.currentThread().getContextClassLoader().getResource("emptyTest.yaml")
                    .toURI();
            // ""
            assertThrows(NullPointerException.class, () -> resLoad.setConfigPath(uri),
                    "Config field cannot be empty");
            final URI uri2 = Thread.currentThread().getContextClassLoader().getResource("NULLTest.yaml")
                    .toURI();
            // NULL:
            assertThrows(NullPointerException.class, () -> resLoad.setConfigPath(uri2),
                    "Config field cannot be empty");
            final URI uri3 = Thread.currentThread().getContextClassLoader().getResource("ONETest.yaml")
                    .toURI();
            // ONE:1
            resLoad.setConfigPath(uri3);
            assertEquals(1, resLoad.getConfig("ONE"), "ONE field has 1 as value.");
            assertThrows(IllegalStateException.class, () -> resLoad.getConfig("TWO"),
                    "TWO field doesnt exist.");
        } catch (IOException | URISyntaxException e) {
            fail();
        }
    }
}
