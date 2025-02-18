package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import it.unibo.artrat.utils.impl.Converter;

class ConverterTest {

    @Test
    void testNanosToFpsValidInput() {
        final int nanos = 500_000_000;
        final int expectedFps = 2;
        final int result = Converter.nanosToFps(nanos);
        assertEquals(expectedFps, result);
    }

    @Test
    void testLowerThanOne() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Converter.nanosToFps(0);
        });
        assertEquals("nano second must be > 1", thrown.getMessage());
        thrown = assertThrows(IllegalArgumentException.class, () -> {
            Converter.fpsToNanos(0);
        });
        assertEquals("FPS must be > 1", thrown.getMessage());
    }

    @Test
    void testFpsToNanosValidInput() {
        final double delta = 0.00_001;
        final double billion = 1_000_000_000;
        final int fps = 60;
        double expectedNanos = billion / fps;
        double result = Converter.fpsToNanos(fps);
        assertEquals(expectedNanos, result, delta);
        final int fps2 = 120;
        expectedNanos = billion / fps2;
        result = Converter.fpsToNanos(fps2);
        assertEquals(expectedNanos, result, delta);
    }
}
