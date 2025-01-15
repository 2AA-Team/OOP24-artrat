package it.unibo.artrat.utils.impl;

/**
 * Converter class.
 */
public final class Converter {
    private static final long BILLION = 1_000_000_000;

    /**
     * Converts nano seconds to FPS.
     * 
     * @param nanos
     * @return int rappresenting FPS
     */
    public static int nanosToFps(final int nanos) {
        return Math.toIntExact(BILLION / nanos);
    }

    /**
     * Converts FPS to nano seconds.
     * 
     * @param fps
     * @return nano seconds
     */
    public static int fpsToNanos(final int fps) {
        return Math.toIntExact(BILLION / fps);
    }
}
