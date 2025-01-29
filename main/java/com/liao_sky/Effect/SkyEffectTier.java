package com.liao_sky.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class SkyEffectTier extends Effect {
    private final boolean instant;
    private boolean isRegistered = false;

    public SkyEffectTier(EffectType type, int color, boolean isInstant) {
        super(type, color);
        this.instant = isInstant;
    }

    public boolean isInstantenous() {
        return false;
    }//是否为瞬间效果

    @Override
    public boolean isDurationEffectTick(int remainingTicks, int level) {
        if (isInstantenous()) {
            return true;
        }
        return canApplyEffect(remainingTicks, level);
    }

    protected boolean canApplyEffect(int remainingTicks, int level) {
        if (!isInstantenous()) {
            //Log.w("Non-instant effects should override canApplyEffect!");
            Thread.dumpStack();
        }
        return false;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (isInstantenous()) {
            applyInstantenousEffect(null, null, entity, amplifier, 1.0d);
        }
    }

    public SkyEffectTier onRegister() {
        isRegistered = true;
        return this;
    }

    public boolean isRegistered() {
        return isRegistered;
    }
}

