package org.github.prkaspars.fractal;

import org.github.prkaspars.geometry.AbstractPolygon;
import org.github.prkaspars.geometry.RegularPolygon;
import org.github.prkaspars.geometry.Triangle;

import javax.swing.*;
import java.awt.*;

public class PythagorasTree extends JPanel {
    double[] ta;
    int x0, y0, length;

    public static void main(String[] args) {
        JPanel panel = new PythagorasTree(250, 10, 100, Math.PI / 3);
        org.github.prkaspars.Canvas.paint(panel, 600, 500);
    }

    public PythagorasTree(int x0, int y0, int length, double a) {
        ta = new double[]{a, Math.PI / 2 - a};
        this.x0 = x0;
        this.y0 = y0;
        this.length = length;
    }

    void add(int x, int y, int l, double a, Graphics graphics) {
        AbstractPolygon square = RegularPolygon.square(l);
        int[] xa = square.getPointsX(x, a);
        int[] ya = square.getPointsY(y, a);
        graphics.drawPolygon(xa, ya, 4);

        if (l < 3) {
            return;
        }

        Triangle triangle = Triangle.asa(ta[0], ta[1], l);
        int[] txa = triangle.getPointsX(xa[2], a);
        int[] tya = triangle.getPointsY(ya[2], a);
        graphics.fillPolygon(txa, tya, triangle.getPointsN());

        add(txa[0], tya[0], (int) triangle.getA(), a + ta[1], graphics);
        add(txa[2], tya[2], (int) triangle.getB(), a - ta[0], graphics);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        add(x0, y0, length, 0, graphics);
    }
}
