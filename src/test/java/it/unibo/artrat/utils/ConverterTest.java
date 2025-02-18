package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.unibo.artrat.utils.impl.Converter;

public class ConverterTest {

    @Test
    public void testValidInput() {
        int nanos = 500_000_000;
        int expectedFps = 2;
        int result = Converter.nanosToFps(nanos);
        assertEquals(expectedFps, result);
    }

    @Test
    public void testLowerThanOne() {
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
    public void testFpsToNanos_ValidInput() {
        int fps = 60;
        double expectedNanos = 1_000_000_000.0 / 60;
        double result = Converter.fpsToNanos(fps);
        assertEquals(expectedNanos, result, 0.00001);
        fps = 120;
        expectedNanos = 1_000_000_000.0 / 120;
        result = Converter.fpsToNanos(fps);
        assertEquals(expectedNanos, result, 0.00001);
    }
}
