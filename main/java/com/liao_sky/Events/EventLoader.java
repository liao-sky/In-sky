package com.liao_sky.Events;

import com.liao_sky.Entity.EffectEvent;
import com.liao_sky.World.gen.SkyOreCreation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


public class EventLoader {

    public static void Loader(){
        /* ����ע���¼� */
        RegistryEvents.ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryEvents.BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryEvents.EffectRegistry.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryEvents.PotionRegistry.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());

        /* ���������¼� */
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, SkyOreCreation::generateOres);

        /* �����¼� */
        MinecraftForge.EVENT_BUS.register(EffectEvent.class);
        MinecraftForge.EVENT_BUS.register(EffectEvent.ErosionEvent.class);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, EffectEvent.ErosionEvent::Erosion);
        MinecraftForge.EVENT_BUS.register(EffectEvent.FractureEvent.class);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, EffectEvent.FractureEvent::Fracture);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH,EffectEvent.FractureEvent::FractureHurt);
    }
}
