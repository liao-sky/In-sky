package com.liao_sky.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectType;


public class SkyEffects {
    public static class ErosionEffect extends SkyEffectBase {
        public ErosionEffect(EffectType type, int color, boolean isInstant) {
            super(type, color, isInstant);
        }

        @Override
        protected boolean canApplyEffect(int remainingTicks, int level) {
            return remainingTicks % 7 == 0;
        }

        //持续时间
        @Override
        public void applyEffectTick(LivingEntity living, int amplified) {
            amplified++;
            for (EquipmentSlotType slot : EquipmentSlotType.values()) {
                DamageItemInSlot(slot, living, amplified);
            }

        }

        public void DamageItemInSlot(EquipmentSlotType slot, LivingEntity livingBase, int amount) {
            if (slot != EquipmentSlotType.MAINHAND && slot != EquipmentSlotType.OFFHAND) {
                ItemStack stack = livingBase.getItemBySlot(slot);
                stack.hurtAndBreak(amount, livingBase, (p_220287_1_) -> p_220287_1_.broadcastBreakEvent(slot));
            }
        }

        //Tell buff or de-buff
        @Override
        public boolean isBeneficial() {
            return false;
        }
    }

    public static class FractureEffect extends SkyEffectBase {
        public FractureEffect(EffectType type, int color, boolean isInstant) {
            super(type, color, isInstant);
        }

        @Override
        protected boolean canApplyEffect(int remainingTicks, int level) {
            return remainingTicks % 7 == 0;
        }

        //Tell buff or de-buff
        @Override
        public boolean isBeneficial() {
            return false;
        }
    }
}