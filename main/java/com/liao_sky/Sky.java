package com.liao_sky;

import com.liao_sky.Events.RegistryEvents;
import com.liao_sky.world.gen.SkyOreCreation;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("sky")
public class Sky {
    public static Sky instance;
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "sky";
    public static final ItemGroup SKY_GROUP = new Sky.skyGroup("in_sky");
    public Sky() {
        instance = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        RegistryEvents.ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryEvents.BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryEvents.EffectRegistry.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, SkyOreCreation::generateOres);
    }
    private void setup(final FMLCommonSetupEvent event){

    }

    private void clientSetup(final FMLClientSetupEvent event){

    }

    private void onServerStarting(FMLServerStartingEvent event){

    }

    public static class skyGroup extends ItemGroup{
        public skyGroup(String name) {
            super(name);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RegistryEvents.ItemRegistry.gold_head.get());
        }

    }
}
