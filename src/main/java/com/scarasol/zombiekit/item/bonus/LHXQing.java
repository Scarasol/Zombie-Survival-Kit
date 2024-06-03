package com.scarasol.zombiekit.item.bonus;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class LHXQing extends SwordItem implements Bonus {
    public LHXQing(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        Level level = entity.getLevel();
        level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ()), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1, 1);
        if (level instanceof ServerLevel server)
            server.sendParticles(ParticleTypes.LAVA, entity.getX(), entity.getY(), entity.getZ(), 100, 0, 1, 0, 1);
        return retval;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.lhx_qing.description").getString()));
    }

}
