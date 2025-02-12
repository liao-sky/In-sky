package com.liao_sky;

import com.liao_sky.Entity.LivingInitialize;
import com.liao_sky.Events.EffectEvents;
import com.liao_sky.Events.EnchantmentEvents;
import com.liao_sky.Events.RegistryEvents;
import com.liao_sky.World.gen.SkyOreCreation;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
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
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onServerStarting);

        RegistryEvents.ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryEvents.BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryEvents.EffectRegistry.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryEvents.PotionRegistry.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryEvents.EnchantRegistry.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(SkyOreCreation.class);
        MinecraftForge.EVENT_BUS.register(EffectEvents.ErosionEvent.class);
        MinecraftForge.EVENT_BUS.register(EffectEvents.FractureEvent.class);
        MinecraftForge.EVENT_BUS.register(LivingInitialize.class);
        MinecraftForge.EVENT_BUS.register(EnchantmentEvents.ErosionProtectionEvent.class);

    }
    private void setup(final FMLCommonSetupEvent event){

    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(final FMLClientSetupEvent event){

    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    private void onServerStarting(final FMLServerStartingEvent event){

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
