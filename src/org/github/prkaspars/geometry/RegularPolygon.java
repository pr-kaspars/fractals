package org.github.prkaspars.geometry;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class RegularPolygon extends AbstractPolygon {
    private int pointsN;
    private double side;

    public static RegularPolygon square(double side) {
        return new RegularPolygon(side, 4);
    }

    public static RegularPolygon triangle(double side) {
        return new RegularPolygon(side, 3);
    }

    public RegularPolygon(double side, int pointsN) {
        this.side = side;
        this.pointsN = pointsN;
        setAngles(IntStream.range(0, pointsN).mapToDouble(i -> getAlpha()).toArray());
    }

    public double getAlpha() {
        return 2 * Math.PI / pointsN;
    }

    private IntUnaryOperator projectionOperator(double delta, DoubleUnaryOperator triFn) {
        return i -> (int) Math.round(side * triFn.applyAsDouble(delta + i * getAlpha()));
    }

    private IntConsumer connector(int[] points) {
        return i -> points[i] += points[i - 1];
    }

    @Override
    public int[] getPointsX(int offset, double delta) {
        int[] points = IntStream.range(0, pointsN).map(projectionOperator(delta, Math::cos)).toArray();
        points[0] += offset;
        IntStream.range(1, pointsN).forEach(connector(points));
        return points;
    }

    @Override
    public int[] getPointsY(int offset, double delta) {
        int[] points = IntStream.range(0, pointsN).map(projectionOperator(delta, Math::sin)).toArray();
        points[0] += offset;
        IntStream.range(1, pointsN).forEach(connector(points));
        return points;
    }

    @Override
    public int getPointsN() {
        return pointsN;
    }
}
