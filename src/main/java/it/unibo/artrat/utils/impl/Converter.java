package it.unibo.artrat.utils.impl;

/**
 * Converter class.
 * 
 * @author Matteo Tonelli
 */
public final class Converter {
    private static final int MS = 1_000;

    /**
     * private constructor.
     */
    private Converter() {
    }

    /**
     * Converts millis seconds to FPS.
     * 
     * @param millis milli seconds to convert
     * @return int rappresenting FPS
     */
    public static int millisToFps(final int millis) {
        if (millis < 1) {
            throw new IllegalArgumentException("nano second must be > 1");
        }
        return Math.toIntExact(MS / millis);
    }

    /**
     * Converts FPS to millis seconds.
     * 
     * @param fps frame per second
     * @return millis seconds
     */
    public static long fpsToMillis(final int fps) {
        if (fps < 1) {
            throw new IllegalArgumentException("FPS must be > 1");
        }
        return MS / fps;
    }
}
