package com.liao_sky.Random;

import net.minecraft.world.Difficulty;

import java.util.Random;


public class Probability {
    public static boolean P(float P){
        Random random = new Random();
        int r = random.nextInt(101);
        return r < P * 100;
    }

    public static float DtoP(Difficulty D){
        float P = 0.0f;
        if(D==Difficulty.PEACEFUL){
                P =0.0f;
            } else if (D==Difficulty.EASY) {
                P =1.0f;
            } else if (D==Difficulty.NORMAL) {
                P =1.2f;
            } else if (D==Difficulty.HARD) {
                P =1.5f;
        }
        return P;
    }
}
