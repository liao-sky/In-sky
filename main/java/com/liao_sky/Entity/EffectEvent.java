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
        public static boolean Erosion(final LivingHurtEvent event) {
            if (event.isCanceled()) {
                return false;
            }
            LivingEntity beHurt = event.getEntityLiving();//���˶���
            DamageSource source = event.getSource();
            World world = beHurt.getCommandSenderWorld();
            Difficulty hard = world.getDifficulty();
            float amount = event.getAmount();
            int ArmorValue = beHurt.getArmorValue();
            EffectInstance Effect = beHurt.getEffect(RegistryEvents.EffectRegistry.erosion.get());
            if(Effect!=null && amount>ArmorValue*0.2f){
                int amplified = Effect.getAmplifier();
                if (Probability.P(0.25f * Probability.DtoP(hard)* P)){
                    event.setAmount(amount*((amplified+1)*Probability.DtoP(hard)+10)/10);
                    beHurt.sendMessage(new StringTextComponent("��d��ʹ����+1��d ��4�ܵ��˺�x"+(10+amplified*Probability.DtoP(hard))/10+"��4"),beHurt.getUUID());
                }
                if (Probability.P(0.25f * Probability.DtoP(hard)* P) && source.getDirectEntity()!=null) {
                    amplified++;
                    beHurt.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.erosion.get(), (int) (20 * amount * Probability.DtoP(hard)), amplified));
                    beHurt.sendMessage(new StringTextComponent("��d���ֱ�������+1��d ��4�ܵ��˺�x"+(10+amplified*Probability.DtoP(hard))/10+"(�и���)��4"),beHurt.getUUID());
                }
            }
            else if (Probability.P(0.25f * Probability.DtoP(hard)* P) && source.getDirectEntity()!=null && amount>ArmorValue*0.2f) {
                beHurt.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.erosion.get(), (int) (20 * amount * Probability.DtoP(hard)), 0));
                beHurt.sendMessage(new StringTextComponent("��d�ױ���������d"),beHurt.getUUID());
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
                if (Probability.P(0.5f*Probability.DtoP(hard))){
                    Player.hurt(new DamageSource("fracture").bypassArmor().bypassInvul(), Damage * amplified * Probability.DtoP(hard));
                    Player.sendMessage(new StringTextComponent("��d��Ӵ�������ˡ�d"),Player.getUUID());
                }
                if (Probability.P(0.25f*Probability.DtoP(hard))) {
                    Player.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.fracture.get(), (int) (20 * 10 * amplified * Probability.DtoP(hard)), amplified));
                    Player.sendMessage(new StringTextComponent("��d����~����������~��d"),Player.getUUID());
                }
            }else if (Probability.P(0.05f * P * Probability.DtoP(hard))) {
                Player.addEffect(new EffectInstance(RegistryEvents.EffectRegistry.fracture.get(), (int) (20 * 10 * Probability.DtoP(hard)), 0));
                Player.sendMessage(new StringTextComponent("��d�����ˡ�d"),Player.getUUID());
            }
            return true;
        }
    }

}

