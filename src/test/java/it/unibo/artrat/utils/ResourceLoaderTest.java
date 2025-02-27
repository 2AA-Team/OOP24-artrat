package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

/**
 * tester for the resource loader.
 * (Yaml reading)
 */
class ResourceLoaderTest {

        /**
         * test reading config data.
         *
         */
        @Test
        void testReading() {
                final ResourceLoader<String, Integer> resLoad = new ResourceLoaderImpl<>();

                try {
                        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
                                        .getResourceAsStream("emptyTest.yaml");
                        // ""
                        assertThrows(NullPointerException.class, () -> resLoad.setConfigPath(inputStream),
                                        "Config field cannot be empty");
                        final InputStream inputStream2 = Thread.currentThread().getContextClassLoader()
                                        .getResourceAsStream("NULLTest.yaml");
                        // NULL:
                        assertThrows(NullPointerException.class, () -> resLoad.setConfigPath(inputStream2),
                                        "Config field cannot be empty");
                        final InputStream inputStream3 = Thread.currentThread().getContextClassLoader()
                                        .getResourceAsStream("ONETest.yaml");
                        // ONE:1
                        resLoad.setConfigPath(inputStream3);
                        assertEquals(1, resLoad.getConfig("ONE"), "ONE field has 1 as value.");
                        assertThrows(IllegalStateException.class, () -> resLoad.getConfig("TWO"),
                                        "TWO field doesnt exist.");
                } catch (IOException e) {
                        fail();
                }
        }
}
