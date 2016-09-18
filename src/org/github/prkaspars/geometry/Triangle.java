package org.github.prkaspars.geometry;

import static org.github.prkaspars.util.Trigonometry.projectionX;
import static org.github.prkaspars.util.Trigonometry.projectionY;

public class Triangle extends AbstractPolygon {
    private double[] sides;

    public static Triangle asa(double alpha, double beta, double c) {
        Triangle triangle = new Triangle();
        triangle.setAngles(alpha, beta, Math.PI - (alpha + beta));
        triangle.sides = new double[] {
                c * Math.sin(triangle.angle(0)) / Math.sin(triangle.angle(2)),
                c * Math.sin(triangle.angle(1)) / Math.sin(triangle.angle(2)),
                c
        };
        return triangle;
    }

    private Triangle() {}

    public double getA() {
        return sides[0];
    }

    public double getB() {
        return sides[1];
    }

    public double getC() {
        return sides[2];
    }

    public double getAlpha() {
        return angle(0);
    }

    public double getBeta() {
        return angle(1);
    }

    public double getGamma() {
        return angle(2);
    }

    @Override
    public int[] getPointsX(int offset, double delta) {
        return new int[] {
            offset,
            offset + projectionX(getC(), delta),
            offset + projectionX(getA(), getBeta() + delta)
        };
    }

    @Override
    public int[] getPointsY(int offset, double delta) {
        return new int[] {
            offset,
            offset + projectionY(getC(), delta),
            offset + projectionY(getA(), getBeta() + delta)
        };
    }

    @Override
    public int getPointsN() {
        return 3;
    }
}
