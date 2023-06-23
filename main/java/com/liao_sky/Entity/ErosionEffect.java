package com.liao_sky.Entity;

import com.liao_sky.Events.RegistryEvents;
import com.liao_sky.Sky;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Sky.MOD_ID)
public class ErosionEffect {

    public static void  Erosion(LivingHurtEvent event) {
        LivingEntity beHurt = event.getEntityLiving();//The entity who was hurt.
        int amount = (int) event.getAmount();
        int level;
        EffectInstance EROSION_TIER = beHurt.getEffect(RegistryEvents.EffectRegistry.EROSION_TIER.get());
        if (!(EROSION_TIER ==null)){
             level = EROSION_TIER.getAmplifier()+1;
        }
        else {
             level = 0;
        }
        //World world = beHurt.getCommandSenderWorld();//The world of the entity.
        if (amount > beHurt.getArmorValue() && level<5){
            beHurt.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.EROSION_TIER.get(),99999,level));
        }
        if (level==5){
            beHurt.removeEffect(RegistryEvents.EffectRegistry.EROSION_TIER.get());
            beHurt.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.EROSION.get(),20*5*amount,beHurt.getArmorValue()));
            level=0;
        }
    }
}

