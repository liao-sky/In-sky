package com.liao_sky.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class SkyEffectBase extends Effect {
    private final boolean instant;
    private boolean isRegistered = false;

    public SkyEffectBase(EffectType type, int color, boolean isInstant) {
        super(type, color);
        this.instant = isInstant;
    }

    public boolean isInstantenous() {
        return instant;
    }//是否为即时效果

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

    public SkyEffectBase onRegister() {
        isRegistered = true;
        return this;
    }

    public boolean isRegistered() {
        return isRegistered;
    }
}

