package it.unibo.artrat.utils.impl;

/**
 * Converter class.
 * 
 * @author Matteo Tonelli
 */
public final class Converter {
    private static final int BILLION = 1_000_000_000;

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
        if (nanos < 1) {
            throw new IllegalArgumentException("nano second must be > 1");
        }
        return Math.toIntExact(BILLION / nanos);
    }

    /**
     * Converts FPS to nano seconds.
     * 
     * @param fps frame per second
     * @return nano seconds
     */
    public static double fpsToNanos(final int fps) {
        if (fps < 1) {
            throw new IllegalArgumentException("FPS must be > 1");
        }
        return (double) BILLION / fps;
    }
}
