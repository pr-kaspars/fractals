package org.github.prkaspars.util;

import java.util.Arrays;

public class Sequence {
    public static int avg(int... elements) {
        double d = Arrays.stream(elements).average().getAsDouble();
        return (int) Math.round(d);
    }
}
