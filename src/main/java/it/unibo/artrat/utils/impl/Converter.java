package it.unibo.artrat.utils.impl;

/**
 * Converter class.
 * 
 * @author Matteo Tonelli
 */
public final class Converter {
    private static final long BILLION = 1_000_000_000;

    /**
     * private constructor.
     */
    private Converter() {
    }

    /**
     * Converts nano seconds to FPS.
     * 
     * @param nanos nano seconds to convert
     * @return int rappresenting FPS
     */
    public static int nanosToFps(final int nanos) {
        return Math.toIntExact(BILLION / nanos);
    }

    /**
     * Converts FPS to nano seconds.
     * 
     * @param fps frame per second
     * @return nano seconds
     */
    public static int fpsToNanos(final int fps) {
        return Math.toIntExact(BILLION / fps);
    }
}
