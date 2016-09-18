package org.github.prkaspars.fractal;

import org.github.prkaspars.Canvas;
import org.github.prkaspars.geometry.AbstractPolygon;
import org.github.prkaspars.geometry.RegularPolygon;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.PI;

public class KochSnowflake extends JPanel {
    private static double[] ANGLES = {0, -PI / 3, PI / 3, 0};

    private static int getX(double length, double alpha) {
        return (int) Math.round(length * Math.cos(alpha));
    }

    private static int getY(double length, double alpha) {
        return (int) Math.round(length * Math.sin(alpha));
    }

    private int x0, y0;
    private double length;

    public static void main(String[] args) {
        JPanel panel = new KochSnowflake(50, 150, 500);
        Canvas.paint(panel, 600, 610);
    }

    public KochSnowflake(int x0, int y0, double length) {
        this.x0 = x0;
        this.y0 = y0;
        this.length = length;
    }

    public void line(int x, int y, double length, double a, Graphics graphics) {
        if (length < 3) {
            graphics.drawLine(x, y, x + getX(length,a), y + getY(length, a));
            return;
        }

        double _a, l = length / 3;
        for (int i = 0; i < ANGLES.length; i++, x += getX(l, _a), y += getY(l, _a)) {
            _a = a + ANGLES[i];
            line(x, y, l, _a, graphics);
        }
    }

    void paintBase(int x, int y, double l, Graphics graphics) {
        AbstractPolygon triangle = RegularPolygon.triangle(l);

        int[] xa = triangle.getPointsX(x, 0);
        int[] ya = triangle.getPointsY(y, 0);

        for (int i = 0; i < 3; i++) {
            line(xa[i], ya[i], l, (i+1) * triangle.angle(i), graphics);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        paintBase(x0, y0, length, graphics);
    }
}
