package com.liao_sky.Events;

import com.liao_sky.Effect.SkyEffects;
import com.liao_sky.List.FoodList;
import com.liao_sky.Sky;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Logger;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
    public static final Logger LOGGER = Sky.LOGGER;
    public static String MOD_ID = Sky.MOD_ID;
    public static ItemGroup SKY = Sky.SKY_GROUP;

    public static class ItemRegistry{
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
        public static RegistryObject<Item> soup = ITEMS.register("soup",
                () -> new EnchantedGoldenAppleItem(new Item.Properties()
                        .tab(SKY)
                        .stacksTo(1)
                        .food(FoodList.soup)
                )
        );

        public static RegistryObject<Item> gold_head = ITEMS.register("gold_head",
                () -> new EnchantedGoldenAppleItem(new Item.Properties()
                        .tab(SKY)
                        .stacksTo(16)
                        .food(FoodList.gold_head)
                )
        );

        public static RegistryObject<Item> sky_block = ITEMS.register("sky_block",
                ()-> new BlockItem(
                        BlockRegistry.sky_block.get(),
                        new Item.Properties()
                        .tab(SKY)
                        .stacksTo(64)
                )
        );

        public static RegistryObject<Item> sky_ingot = ITEMS.register("sky_ingot",
                () -> new Item(new Item.Properties()
                        .tab(SKY)
                        .stacksTo(64)
                )
        );

        public static RegistryObject<Item> stone_ingot = ITEMS.register("stone_ingot",
                () -> new Item(new Item.Properties()
                        .tab(SKY)
                        .stacksTo(64)
                )
        );

        public static RegistryObject<Item> sky_stick = ITEMS.register("sky_stick",
                () -> new Item(new Item.Properties()
                        .tab(SKY)
                        .stacksTo(64)
                )
        );

        public static RegistryObject<Item> stone_stick = ITEMS.register("stone_stick",
                () -> new Item(new Item.Properties()
                        .tab(SKY)
                        .stacksTo(64)
                )
        );

        public static RegistryObject<Item> iron_stick = ITEMS.register("iron_stick",
                () -> new Item(new Item.Properties()
                        .tab(SKY)
                        .stacksTo(64)
                )
        );

        public static RegistryObject<Item> gold_stick = ITEMS.register("gold_stick",
                () -> new Item(new Item.Properties()
                        .tab(SKY)
                        .stacksTo(64)
                )
        );

        public static RegistryObject<Item> diamond_stick = ITEMS.register("diamond_stick",
                () -> new Item(new Item.Properties()
                        .tab(SKY)
                        .stacksTo(64)
                )
        );

        public static RegistryObject<Item> sky_block_ore = ITEMS.register("sky_block_ore",
                ()-> new BlockItem(BlockRegistry.sky_block_ore.get(),
                        new Item.Properties()
                                .tab(SKY)
                                .stacksTo(64)
                )
        );

        public static RegistryObject<Item> sky_sword = ITEMS.register("sky_sword",
                ()-> new SwordItem(SkyItemTier.SkyTier,10, -1.5f,
                        new Item.Properties().tab(SKY)
                )
        );

        public static RegistryObject<Item> sky_shovel = ITEMS.register("sky_shovel",
                ()-> new AxeItem(SkyItemTier.SkyTier,3, -3.5f,
                        new Item.Properties().tab(SKY)
                )
        );

        public static RegistryObject<Item> sky_pickaxe = ITEMS.register("sky_pickaxe",
                ()-> new PickaxeItem(SkyItemTier.SkyTier,3, -3.5f,
                        new Item.Properties().tab(SKY)
                )
        );

        public static RegistryObject<Item> sky_axe = ITEMS.register("sky_axe",
                ()-> new AxeItem(SkyItemTier.SkyTier,12, -2.5f,
                        new Item.Properties().tab(SKY)
                )
        );

        public static RegistryObject<Item> sky_hoe = ITEMS.register("sky_hoe",
                ()-> new HoeItem(SkyItemTier.SkyTier,3, -3.5f,
                        new Item.Properties().tab(SKY)
                )
        );

    }

    public static class BlockRegistry {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
        public static RegistryObject<Block> sky_block = BLOCKS.register("sky_block",
                () -> new Block(AbstractBlock.Properties
                        .of(Material.STONE)
                        .strength(5,6)
                        .sound(SoundType.STONE)
                )
        );

        public static RegistryObject<Block> sky_block_ore = BLOCKS.register("sky_block_ore",
                ()->new SkyOreBlock((AbstractBlock.Properties
                        .of(Material.STONE)
                        .harvestTool(ToolType.PICKAXE)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE)
                        .strength(8.0f, 8.0f)
                )
        ));
    }
    public static class EffectRegistry{
        public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);
        //buff1
        public static RegistryObject<Effect> EROSION = EFFECTS.register("erosion",
                () -> new SkyEffects.ErosionEffect(EffectType.HARMFUL, 0x660033, false));
    }
}
