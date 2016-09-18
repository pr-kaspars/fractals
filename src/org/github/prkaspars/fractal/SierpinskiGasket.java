package org.github.prkaspars.fractal;

import org.github.prkaspars.*;
import org.github.prkaspars.Canvas;
import org.github.prkaspars.geometry.AbstractPolygon;
import org.github.prkaspars.geometry.RegularPolygon;
import org.github.prkaspars.util.Sequence;

import javax.swing.*;
import java.awt.*;

public class SierpinskiGasket extends JPanel {
    enum Triangle {
        A, B, C;

        public static int[] vertices(int[] outer, int[] inner, Triangle triangle) {
            switch(triangle) {
                case A:
                    return new int[]{outer[0], inner[0], inner[2]};
                case B:
                    return new int[]{inner[0], outer[1], inner[1]};
                case C:
                    return new int[]{inner[1], outer[2], inner[2]};
            }

            return null;
        }
    }

    int x0, y0;
    double length;

    public static void main(String[] args) {
        JPanel panel = new SierpinskiGasket(10, 10, 480);
        Canvas.paint(panel, 500, 500);
    }

    public SierpinskiGasket(int x0, int y0, int length) {
        this.x0 = x0;
        this.y0 = y0;
        this.length = length;
    }

    void devide(int[] x, int[] y, Graphics graphics) {
        int[] xi = new int[3];
        int[] yi = new int[3];

        for (int i0 = 0; i0 < 3; i0++) {
            int i1 = i0 == 2 ? 0 : i0 + 1;
            xi[i0] = Sequence.avg(x[i0], x[i1]);
            yi[i0] = Sequence.avg(y[i0], y[i1]);
        }
        graphics.fillPolygon(xi, yi, 3);

        if (Math.abs(x[2] - x[0]) < 9) {
            return;
        }

        for (Triangle t: new Triangle[]{Triangle.A, Triangle.B, Triangle.C}) {
            devide(Triangle.vertices(x, xi, t), Triangle.vertices(y, yi, t), graphics);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        AbstractPolygon triangle = RegularPolygon.triangle(length);
        int[] xa = triangle.getPointsX(x0, 0);
        int[] ya = triangle.getPointsY(y0, 0);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillPolygon(xa, ya, 3);
        graphics.setColor(Color.WHITE);
        devide(xa, ya, graphics);
    }
}
