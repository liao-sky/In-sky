package com.liao_sky.World.gen;

import com.liao_sky.Events.RegistryEvents;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SkyOreCreation {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void generateOres(final BiomeLoadingEvent event){
        if(event.getCategory().equals(Biome.Category.MESA)){
            oreGenerate(event.getGeneration(),
                    OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                    RegistryEvents.BlockRegistry.sky_block_ore.get().defaultBlockState(),
                    4,
                    0,
                    0,
                    10,
                    2);
        }
    }

    private static void oreGenerate(
            BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state,
            int veinSize, int minimalHeight, int topOffset, int maximalHeight, int countPerChunk
            ){
        settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(
                        new OreFeatureConfig(fillerType,state,veinSize))
                        .decorated(Placement.RANGE.configured(
                                new TopSolidRangeConfig(minimalHeight,topOffset,maximalHeight)))
                        .squared().count(countPerChunk));

    }
}
