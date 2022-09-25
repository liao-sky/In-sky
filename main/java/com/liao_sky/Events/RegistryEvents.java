package com.liao_sky.Events;


import com.liao_sky.List.FoodList;
import com.liao_sky.List.ItemList;
import com.liao_sky.Sky;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
    public static final Logger LOGGER = Sky.LOGGER;
    public static String MOD_ID = Sky.MOD_ID;


    @SubscribeEvent
    public static void registryItem(final RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(
                ItemList.soup = new Item(new Item.Properties()
                        .tab(ItemGroup.TAB_FOOD)
                        .stacksTo(1)
                        .food(FoodList.soup)
                ).setRegistryName(location("soup")),

                ItemList.gold_head = new Item(new Item.Properties()
                        .tab(ItemGroup.TAB_FOOD)
                        .stacksTo(16)
                        .food(FoodList.gold_head)
                ).setRegistryName(location("gold_head"))
        );
    }

    public static void registryBlock(final RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(

        );
    }

    private static ResourceLocation location(String name) {
        return new ResourceLocation(MOD_ID,name);

    }

}