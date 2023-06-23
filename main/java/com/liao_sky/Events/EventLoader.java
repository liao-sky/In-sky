package com.liao_sky.Events;

import com.liao_sky.Entity.ErosionEffect;
import com.liao_sky.world.gen.SkyOreCreation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


public class EventLoader {

    public static void Loader(){
        RegistryEvents.ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryEvents.BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryEvents.EffectRegistry.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, SkyOreCreation::generateOres);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ErosionEffect::Erosion);
        MinecraftForge.EVENT_BUS.register(ErosionEffect.class);
    }
}
