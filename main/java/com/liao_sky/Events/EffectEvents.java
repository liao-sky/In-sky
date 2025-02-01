package com.liao_sky.Events;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.*;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EffectEvents {
    @Cancelable
    public static class ErosionEvent{
        private static float P = 1.0f;
        public static Style DARK_RED = Style.EMPTY.withColor(Color.parseColor("#AA0000"));

        @SubscribeEvent
        public static void Erosion(final LivingHurtEvent event) {
            LivingEntity beHurt = event.getEntityLiving();// ‹…À∂‘œÛ
            Iterable<ItemStack> Slots =beHurt.getArmorSlots();
            float amount = event.getAmount();
            int ArmorValue = beHurt.getArmorValue();
            EffectInstance Effect = beHurt.getEffect(RegistryEvents.EffectRegistry.erosion.get());
            if(Effect!=null
                    && amount>ArmorValue*0.3f
                    && event.getEntityLiving()!=null
                    && beHurt.getArmorValue()!=0
                ){
                int amplified = Effect.getAmplifier();
                if (beHurt.getRandom().nextFloat()<0.25f * P){
                    event.setAmount(amount*(amplified+11)/10);
                    beHurt.sendMessage(new TranslationTextComponent("effect.sky.erosion.hurt",Float.toString((float) (amplified + 11) /10)).setStyle(DARK_RED),beHurt.getUUID());
                }
                if (beHurt.getRandom().nextFloat()<0.25f * P) {
                    amplified++;
                    beHurt.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.erosion.get(), (int) (20 * amount), amplified));
                    beHurt.sendMessage(new TranslationTextComponent("effect.sky.erosion.break_again",Float.toString((float) (amplified + 11) /10)).setStyle(DARK_RED),beHurt.getUUID());
                }
                if (amplified >= 9){
                    beHurt.sendMessage(new TranslationTextComponent("effect.sky.erosion.warning"),beHurt.getUUID());
                }
                if (beHurt.getRandom().nextFloat()<0.05f * Math.abs(amplified-3) * P && amplified >= 10){
                    for (ItemStack slot : Slots) {
                        if(slot.getEquipmentSlot()!= EquipmentSlotType.MAINHAND
                                && beHurt.getRandom().nextFloat()<0.25f * P
                                && !slot.isEmpty()
                                && slot.getEquipmentSlot()!=null){
                            slot.setCount(0);
                            beHurt.broadcastBreakEvent(slot.getEquipmentSlot());
                            beHurt.sendMessage(new TranslationTextComponent("effect.sky.erosion.armor_break"),beHurt.getUUID());
                        }
                    }
                }
            }
            else if (beHurt.getRandom().nextFloat()<0.25f * P
                    && event.getEntityLiving()!=null
                    && amount>ArmorValue*0.2f
                    && beHurt.getArmorValue()!=0
                ) {
                beHurt.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.erosion.get(), (int) (20 * amount), 0));
                beHurt.sendMessage(new TranslationTextComponent("effect.sky.erosion.break"),beHurt.getUUID());
            }
        }

        public void setP(float p) {
            P = p;
        }
    }


    @Cancelable
    public static class FractureEvent {
        private static float P = 1.0f;
        private static float Damage = 1.0f;


        @SubscribeEvent
        public static void Fracture(final PlayerEvent.BreakSpeed event) {
            PlayerEntity Player = event.getPlayer();
            EffectInstance Effect = Player.getEffect(RegistryEvents.EffectRegistry.fracture.get());
            if (Effect != null) {
                int amplified = Effect.getAmplifier()+1;
                float OSpeed = event.getOriginalSpeed();
                event.setNewSpeed(OSpeed / amplified);
            }
        }


        @SubscribeEvent
        public static void FractureHurt(final BlockEvent.BreakEvent event){
            PlayerEntity Player = event.getPlayer();
            Item Tool = Player.getItemBySlot(EquipmentSlotType.MAINHAND).getItem();
            Block Target = event.getState().getBlock();
            EffectInstance Effect = Player.getEffect(RegistryEvents.EffectRegistry.fracture.get());
            boolean Flag = !Tool.isCorrectToolForDrops(Target.defaultBlockState()) || Player.getMainHandItem().isEmpty();
            if (Effect != null && Flag) {
                int amplified = Effect.getAmplifier()+1;
                if (Player.getRandom().nextFloat()<0.5f*P){
                    Player.hurt(new DamageSource("fracture").bypassArmor().bypassInvul().bypassMagic(), Damage * amplified);
                    Player.sendMessage(new TranslationTextComponent("effect.sky.fracture.hurt"),Player.getUUID());
                }
                if (Player.getRandom().nextFloat()<0.25f*P) {
                    Player.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.fracture.get(), 20 * 10 * amplified, amplified));
                    Player.sendMessage(new TranslationTextComponent("effect.sky.fracture.severe"),Player.getUUID());
                }
            }else if (Player.getRandom().nextFloat()<0.05f*P && Flag) {
                Player.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.fracture.get(), 20 * 10, 0));
                Player.sendMessage(new TranslationTextComponent("effect.sky.fracture.begin"),Player.getUUID());
            }
        }

        public void setP(float p){
            P = p;
        }

        public void setDamage(float damage){
            Damage = damage;
        }
    }

}

