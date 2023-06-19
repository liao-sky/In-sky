package com.liao_sky.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectType;

import java.util.Random;

public class SkyEffects {
    public static class ErosionEffect extends SkyEffectTier{
        public ErosionEffect(EffectType type, int color, boolean isInstant) {
            super(type, color, isInstant);
        }

        @Override
        protected boolean canApplyEffect(int remainingTicks, int level) {
            return remainingTicks % 7 == 0;
        }
        //Continue times
        @Override
        public void applyEffectTick(LivingEntity living, int amplified) {
            amplified ++;
            Random ran = new Random();
            int co = ran.nextInt(5);
            for (EquipmentSlotType slot: EquipmentSlotType.values()) {
                DamageItemInSlot(slot, living, co*amplified);
            }

        }

        public void DamageItemInSlot(EquipmentSlotType slot, LivingEntity livingBase, int amount)
        {
            ItemStack stack = livingBase.getItemBySlot(slot);
            stack.hurtAndBreak(1, livingBase, (p_220287_1_) -> {
                p_220287_1_.broadcastBreakEvent(slot);
            });
        }

        //Tell buff or de-buff
        @Override
        public boolean isBeneficial() {
            return false;
        }
    }

}