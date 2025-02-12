package com.liao_sky.Entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class LivingInitialize {

    @SubscribeEvent
    public static void LivingSpawn(LivingSpawnEvent.SpecialSpawn event){
        LivingEntity entity = event.getEntityLiving();
        CompoundNBT RNbt = new CompoundNBT();
        entity.readAdditionalSaveData(RNbt);
        if(!RNbt.contains("Karma")){
            CompoundNBT WNbt = new CompoundNBT();
            WNbt.putInt("Karma",0);
            entity.addAdditionalSaveData(WNbt);
        }
        if(!RNbt.contains("P")){
            CompoundNBT WNbt = new CompoundNBT();
            WNbt.putFloat("P",1.0f);
            entity.addAdditionalSaveData(WNbt);
        }
    }
}
