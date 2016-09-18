package org.github.prkaspars.geometry;

public abstract class AbstractPolygon {
    private double[] angles;

    public abstract int[] getPointsX(int offset, double delta);

    public abstract int[] getPointsY(int offset, double delta);

    public abstract int getPointsN();

    public void setAngles(double... angles) {
        this.angles = angles;
    }

    public double angle(int i) {
        return angles[i];
    }
}
