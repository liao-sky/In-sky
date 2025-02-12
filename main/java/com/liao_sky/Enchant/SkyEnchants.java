package com.liao_sky.Enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class SkyEnchants {
    public static class ErosionProtection extends SkyEnchantBase{
        private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
        public ErosionProtection() {
            super(Enchantment.Rarity.UNCOMMON, EnchantmentType.ARMOR, ARMOR_SLOTS);
        }

        public int getMinLevel() {
            return super.getMinLevel();
        }

        public int getMaxLevel() {
      return 3;
   }
    }

    public static int getLevelOnCreature(Enchantment enchantment, LivingEntity entity){
        int  level=0;
        for (ItemStack slot : entity.getAllSlots()) {
            level+= EnchantmentHelper.getItemEnchantmentLevel(enchantment,slot);
        }
        return level;
    }

}
