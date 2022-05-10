package com.mygdx.zombieland.utils;

import java.util.Random;

public class MathHelper {

    public static double nextDouble(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

}
