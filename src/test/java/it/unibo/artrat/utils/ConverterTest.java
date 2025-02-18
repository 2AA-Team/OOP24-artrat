package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.utils.impl.Converter;

class ConverterTest {

    @Test
    void testNanosToFpsValidInput() {
        final int milli = 500;
        final int expectedFps = 2;
        final int result = Converter.millisToFps(milli);
        assertEquals(expectedFps, result);
    }

    @Test
    void testLowerThanOne() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Converter.millisToFps(0);
        });
        assertEquals("nano second must be > 1", thrown.getMessage());
        thrown = assertThrows(IllegalArgumentException.class, () -> {
            Converter.fpsToMillis(0);
        });
        assertEquals("FPS must be > 1", thrown.getMessage());
    }

    @Test
    void testFpsToNanosValidInput() {
        final double delta = 0.00_001;
        final long thousand = 1_000;
        final int fps = 60;
        long expectedNanos = thousand / fps;
        long result = Converter.fpsToMillis(fps);
        assertEquals(expectedNanos, result, delta);
        final int fps2 = 120;
        expectedNanos = thousand / fps2;
        result = Converter.fpsToMillis(fps2);
        assertEquals(expectedNanos, result, delta);
    }
}
