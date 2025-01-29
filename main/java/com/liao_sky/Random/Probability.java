package com.liao_sky.Random;

public class Probability {
    public static boolean P(float P){
        java.util.Random random = new java.util.Random();
        int r = random.nextInt(101);
        return r < P * 100;
    }
}
