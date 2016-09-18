package org.github.prkaspars;

import javax.swing.*;

public class Canvas {
    public static void paint(JPanel panel, int with, int height) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(panel);
        jFrame.setSize(with, height);
        jFrame.setVisible(true);
    }
}