package com.liao_sky.Events;

import com.liao_sky.Enchant.SkyEnchants;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EnchantmentEvents {
    public static class ErosionProtectionEvent{

        public static boolean ErosionProtection(LivingHurtEvent event){
            int Erosion = SkyEnchants.getLevelOnCreature(
                    RegistryEvents.EnchantRegistry.erosion_protection.get(), event.getEntityLiving());
            return event.getEntityLiving().getRandom().nextFloat()< (float) Erosion/20;
        }
    }
}
