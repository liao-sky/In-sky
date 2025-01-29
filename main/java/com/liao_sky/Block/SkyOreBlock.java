package com.liao_sky.Block;

import com.liao_sky.Events.RegistryEvents;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class SkyOreBlock extends OreBlock {
    public SkyOreBlock(Properties p_i48357_1_) {
        super(p_i48357_1_);
    }

    @Override
    protected int xpOnDrop(Random p_220281_1_) {
        if (this.is(RegistryEvents.BlockRegistry.sky_block_ore.get())) {
            return MathHelper.nextInt(p_220281_1_, 0, 0);
        }
        else{
            return 0;
        }
    }
}
