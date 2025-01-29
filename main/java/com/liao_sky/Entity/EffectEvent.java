package com.liao_sky.Entity;

import com.liao_sky.Events.RegistryEvents;
import com.liao_sky.Random.Probability;
import com.liao_sky.Sky;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.*;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Sky.MOD_ID)
public class EffectEvent {
    @Cancelable
    public static class ErosionEvent{
        public static float P = 1.0f;
        public static boolean Erosion(LivingHurtEvent event) {
            if (event.isCanceled()) {
                return false;
            }
            LivingEntity beHurt = event.getEntityLiving();//受伤对象
            DamageSource source = event.getSource();
            World world = beHurt.getCommandSenderWorld();
            Difficulty hard = world.getDifficulty();
            float amount = event.getAmount();
            float armorValue = beHurt.getArmorValue();
            float maxHealth = beHurt.getMaxHealth();
            int amplified = 0;
            if (amount > 0.25 * (armorValue + 0.1 * maxHealth) + 0.5) {
                amplified = 1;
            } else if (amount > 0.5 * (armorValue + 0.1 * maxHealth) + 0.5)  {
                amplified = 2;
            } else if (amount > 0.75 * (armorValue + 0.15 * maxHealth) + 0.5) {
                amplified = 3;
            } else if (amount > armorValue + 0.2 * maxHealth + 0.5) {
                amplified = 4;
            }
            if (Probability.P(0.25f * Probability.DtoP(hard)* P * ++amplified) && source == DamageSource.GENERIC) {
                beHurt.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.erosion.get(), (int) (20 * amount), amplified));
                beHurt.sendMessage(new StringTextComponent("§d甲爆哩？！§d"+ (amplified + 1)),beHurt.getUUID());
            }
            return true;
        }
    }


    @Cancelable
    public static class FractureEvent {
        public static float P = 1.0f;
        public static float Damage = 1.0f;

        public static boolean Fracture(final PlayerEvent.BreakSpeed event) {
            if (event.isCanceled()) {
                return false;
            }
            PlayerEntity Player = event.getPlayer();
            EffectInstance Effect = Player.getEffect(RegistryEvents.EffectRegistry.fracture.get());
            if (Effect != null) {
                int amplified = Effect.getAmplifier()+1;
                float OSpeed = event.getOriginalSpeed();
                event.setNewSpeed(OSpeed / amplified);
            }
            return true;
        }

        public static boolean FractureHurt(final BlockEvent.BreakEvent event){
            if (event.isCanceled()) {
                return false;
            }
            PlayerEntity Player = event.getPlayer();
            World world = Player.getCommandSenderWorld();
            Difficulty hard = world.getDifficulty();
            EffectInstance Effect = Player.getEffect(RegistryEvents.EffectRegistry.fracture.get());
            if (Effect != null) {
                int amplified = Effect.getAmplifier()+1;
                if (Probability.P(0.5f)){
                    Player.hurt(new DamageSource("fracture").bypassArmor().bypassInvul(), Damage * amplified * Probability.DtoP(hard));
                    Player.sendMessage(new StringTextComponent("§d哎哟！受伤了§d"),Player.getUUID());
                }
                if (Probability.P(0.25f)) {
                    Player.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.fracture.get(), 20 * 10 * amplified, amplified));
                    Player.sendMessage(new StringTextComponent("§d杂鱼~骨折严重了~§d"),Player.getUUID());
                }
            }else if (Probability.P(0.05f * P * Probability.DtoP(hard))) {
                Player.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.fracture.get(), 20 * 10, 0));
                Player.sendMessage(new StringTextComponent("§d骨折了§d"),Player.getUUID());
            }
            return true;
        }
    }

}

