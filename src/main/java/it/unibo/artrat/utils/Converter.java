package it.unibo.artrat.utils;

public class Converter {
    private static final long billion = 1_000_000_000;

    public static int nanosToFps(final int nanos) {
        return Math.toIntExact(billion / nanos);
    }

    public static int fpsToNanos(final int fps) {
        return Math.toIntExact(billion / fps);
    }
}
