package com.liao_sky.Events;

import com.liao_sky.Block.SkyOreBlock;
import com.liao_sky.Effect.SkyEffects;
import com.liao_sky.Item.armor.SkyArmorMaterial;
import com.liao_sky.Item.SkyItemBase;
import com.liao_sky.List.FoodList;
import com.liao_sky.Sky;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.*;
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
                        .tab(SKY).rarity(Rarity.EPIC)
                        .stacksTo(1)
                        .food(FoodList.soup)
                )
        );

        public static RegistryObject<Item> gold_head = ITEMS.register("gold_head",
                () -> new EnchantedGoldenAppleItem(new Item.Properties()
                        .tab(SKY).rarity(Rarity.EPIC)
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

        public static RegistryObject<Item> sky_block_ore = ITEMS.register("sky_block_ore",
                ()-> new BlockItem(BlockRegistry.sky_block_ore.get(),
                        new Item.Properties()
                                .tab(SKY)
                                .stacksTo(64)
                )
        );

        public static RegistryObject<Item> sky_sword = ITEMS.register("sky_sword",
                ()-> new SwordItem(SkyItemBase.SkyTier,14, -1.5f,
                        new Item.Properties().tab(SKY)
                )
        );

        public static RegistryObject<Item> sky_shovel = ITEMS.register("sky_shovel",
                ()-> new AxeItem(SkyItemBase.SkyTier,3, -3.5f,
                        new Item.Properties().tab(SKY)
                )
        );

        public static RegistryObject<Item> sky_pickaxe = ITEMS.register("sky_pickaxe",
                ()-> new PickaxeItem(SkyItemBase.SkyTier,3, -3.5f,
                        new Item.Properties().tab(SKY)
                )
        );

        public static RegistryObject<Item> sky_axe = ITEMS.register("sky_axe",
                ()-> new AxeItem(SkyItemBase.SkyTier,12, -2.5f,
                        new Item.Properties().tab(SKY)
                )
        );

        public static RegistryObject<Item> sky_hoe = ITEMS.register("sky_hoe",
                ()-> new HoeItem(SkyItemBase.SkyTier,3, -3.5f,
                        new Item.Properties().tab(SKY)
                )
        );

        public static final RegistryObject<Item> sky_helmet = ITEMS.register("sky_helmet",
                () -> new ArmorItem(
                        SkyArmorMaterial.SKY,
                        EquipmentSlotType.HEAD,
                        (new Item.Properties()).tab(SKY)
                )
        );

        public static final RegistryObject<Item> sky_chestplate = ITEMS.register("sky_chestplate",
                () -> new ArmorItem(
                        SkyArmorMaterial.SKY,
                        EquipmentSlotType.CHEST,
                        (new Item.Properties()).tab(SKY)
                )
        );

        public static final RegistryObject<Item> sky_leggings = ITEMS.register("sky_leggings",
                () -> new ArmorItem(
                        SkyArmorMaterial.SKY,
                        EquipmentSlotType.LEGS,
                        (new Item.Properties()).tab(SKY)
                )
        );

        public static final RegistryObject<Item> sky_boots = ITEMS.register("sky_boots",
                () -> new ArmorItem(
                        SkyArmorMaterial.SKY,
                        EquipmentSlotType.FEET,
                        (new Item.Properties()).tab(SKY)
                )
        );
    }

    public static class BlockRegistry {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
        public static RegistryObject<Block> sky_block = BLOCKS.register("sky_block",
                () -> new Block(AbstractBlock.Properties
                        .of(Material.STONE)
                        .strength(5.0f,6.0f)
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
        public static RegistryObject<Effect> erosion = EFFECTS.register("erosion",
                () -> new SkyEffects.ErosionEffect(EffectType.HARMFUL, 0x660033, false));

        public static RegistryObject<Effect> fracture = EFFECTS.register("fracture",
                ()->new SkyEffects.FractureEffect(EffectType.HARMFUL,0xFFFFFF,false));
    }

    public static class  PotionRegistry{
        public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, MOD_ID);
        public static RegistryObject<Potion> erosion = POTIONS.register("erosion",
                ()->new Potion(new EffectInstance(EffectRegistry.erosion.get(),20*45)));

        public static RegistryObject<Potion> long_erosion = POTIONS.register("long_erosion",
                ()->new Potion("erosion",new EffectInstance(EffectRegistry.erosion.get(),20*90)));

        public static RegistryObject<Potion> strong_erosion = POTIONS.register("strong_erosion",
                ()->new Potion("erosion",new EffectInstance(EffectRegistry.erosion.get(),20*45,1)));
    }
}
