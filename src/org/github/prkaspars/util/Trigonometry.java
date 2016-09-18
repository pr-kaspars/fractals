package org.github.prkaspars.util;

public class Trigonometry {
    public static int projectionX(double length, double alpha) {
        return (int) Math.round(length * Math.cos(alpha));
    }

    public static int projectionY(double length, double alpha) {
        return (int) Math.round(length * Math.sin(alpha));
    }
}
