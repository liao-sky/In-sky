package com.liao_sky.List;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodList {
    public static final Food soup =
            (new Food.Builder().nutrition(8).saturationMod(0.5f)
                    .effect(
                            ()-> new EffectInstance(Effects.DAMAGE_RESISTANCE,20*20,4),0.33f)
                    .effect(
                            ()-> new EffectInstance(Effects.POISON,20*20,1),0.33f)
                    .effect(
                            ()-> new EffectInstance(Effects.REGENERATION,20*20,1),0.33f)
                    .effect(
                            ()-> new EffectInstance(Effects.GLOWING,20*20,0),0.33f)
                    .effect(
                            ()-> new EffectInstance(Effects.HUNGER,20*20,0),0.33f)
                    .effect(
                            ()-> new EffectInstance(Effects.SATURATION,20*20,0),0.33f)
                    .alwaysEat()
                    .meat()
                    .build()
            );

    public static final Food gold_head =
            (new Food.Builder().nutrition(4).saturationMod(1f)
                    .effect(
                            ()-> new EffectInstance(Effects.REGENERATION,15*20,1),1f)
                    .effect(
                            ()-> new EffectInstance(Effects.DAMAGE_RESISTANCE,10*20,1),0.8f)
                    .effect(
                            ()-> new EffectInstance(Effects.ABSORPTION,15*20,1),1f)
                    .effect(
                            ()-> new EffectInstance(Effects.GLOWING,20*20,0),1f)
                    .effect(
                            ()-> new EffectInstance(Effects.HEALTH_BOOST,15*20,0),1f)
                    .effect(
                            ()-> new EffectInstance(Effects.MOVEMENT_SPEED,10*20,0),1f)
                    .fast()
                    .alwaysEat()
                    .build()
            );
}
