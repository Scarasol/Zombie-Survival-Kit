package com.scarasol.zombiekit.entity.goal;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.entity.mechanics.HeavyMachineGunEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class HeavyMachineGunUsingGoal<T extends Mob> extends Goal {
    private final T mob;
    private int seeTime;
    private int attackDelay;
    private int attackTimes;
    private int attackCooldown;
    private MachineGunState machineGunState = MachineGunState.UNCHARGED;
    private final float attackRadiusSqr;
    @Nullable
    private Predicate<LivingEntity> predicate;
    private boolean enemy = false;

    public HeavyMachineGunUsingGoal(T mob, float attackRadiusSqr) {
        this.mob = mob;
        this.attackRadiusSqr = attackRadiusSqr * attackRadiusSqr;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    public HeavyMachineGunUsingGoal(T mob, float attackRadiusSqr, @Nullable Predicate<LivingEntity> predicate, boolean enemy) {
        this.mob = mob;
        this.attackRadiusSqr = attackRadiusSqr * attackRadiusSqr;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        this.predicate = predicate;
        this.enemy = enemy;
    }

    @Override
    public boolean canUse() {
        return this.isValidTarget();
    }

    private boolean isInMachineGunSight() {
        if (this.mob.getVehicle() instanceof HeavyMachineGunEntity heavyMachineGun){
            return heavyMachineGun.canSee(this.mob.getTarget());
        }
        return false;
    }

    private boolean isInMachineGunSight(Entity target) {
        if (this.mob.getVehicle() instanceof HeavyMachineGunEntity heavyMachineGun){
            return heavyMachineGun.canSee(target);
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.isValidTarget();
    }

    private boolean isValidTarget() {
        if (this.mob.getTarget() != null && this.mob.getTarget().isAlive() && isInMachineGunSight()){
            return true;
        }else if (enemy && searchPlayer()){
            return true;
        }else return this.predicate != null && searchEntities();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
        this.mob.setTarget(null);
        this.seeTime = 0;
    }

    public void tick() {
        LivingEntity livingEntity = this.mob.getTarget();
        if (livingEntity != null) {
            this.mob.setAggressive(true);
            boolean flag = this.mob.getSensing().hasLineOfSight(livingEntity);
            boolean flag1 = this.seeTime > 0;
            if (flag != flag1) {
                this.seeTime = 0;
            }

            if (flag) {
                ++this.seeTime;
            } else {
                --this.seeTime;
            }
            if (this.mob.getVehicle() instanceof HeavyMachineGunEntity heavyMachineGun && heavyMachineGun.getOverload()){
                this.machineGunState = MachineGunState.OVERHEAT;
            }else if (this.machineGunState == MachineGunState.OVERHEAT){
                this.machineGunState = MachineGunState.UNCHARGED;
            }
            double distance = this.mob.distanceToSqr(livingEntity);
            boolean flag2 = (distance > (double)this.attackRadiusSqr || this.seeTime < 5) && this.attackDelay == 0;
            this.mob.getLookControl().setLookAt(livingEntity, 30.0F, 30.0F);
            if (this.machineGunState == MachineGunState.UNCHARGED && this.attackTimes > 0) {
                this.machineGunState = MachineGunState.CHARGED;
                this.attackDelay = 20 + this.mob.getRandom().nextInt(10);

            }
            if (this.machineGunState == MachineGunState.UNCHARGED) {
                if (!flag2) {
                    this.machineGunState = MachineGunState.CHARGED;
                    this.attackDelay = 30 + this.mob.getRandom().nextInt(30);
                }
            }else if (this.machineGunState == MachineGunState.CHARGED) {
                --this.attackDelay;
                if (this.attackDelay == 0) {
                    this.machineGunState = MachineGunState.READY_TO_ATTACK;
                    this.attackTimes = 4 + this.mob.getRandom().nextInt(4);
                }
            }else if (this.machineGunState == MachineGunState.READY_TO_ATTACK && flag) {
                if (this.mob.getVehicle() instanceof HeavyMachineGunEntity heavyMachineGun){
                    if (this.attackCooldown == 0){
                        if (this.attackTimes-- == 0){
                            this.machineGunState = MachineGunState.UNCHARGED;
                        }else {
                            heavyMachineGun.fire();
                            this.attackCooldown = 4;
                        }
                    }else {
                        this.attackCooldown--;
                    }
                }
            }
        }
    }

    public boolean searchEntities(){
        List<LivingEntity> list = this.mob.level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat().range(25.0), this.mob, this.mob.getBoundingBox().inflate(30.0, 15.0, 30.0));
        if (!list.isEmpty()) {
            list.sort(new Comparator<>() {
                public int compare(LivingEntity l1, LivingEntity l2) {
                    double a = Math.pow(l1.getX() - HeavyMachineGunUsingGoal.this.mob.getX(), 2) + Math.pow(l1.getZ() - HeavyMachineGunUsingGoal.this.mob.getZ(), 2);
                    double b = Math.pow(l2.getX() - HeavyMachineGunUsingGoal.this.mob.getX(), 2) + Math.pow(l2.getZ() - HeavyMachineGunUsingGoal.this.mob.getZ(), 2);
                    return Double.compare(a, b);
                }
            });
            for (LivingEntity entity : list) {
                if (this.mob.canAttack(entity, TargetingConditions.forCombat().selector(predicate))) {
                    if (isInMachineGunSight(entity) && !(entity instanceof HeavyMachineGunEntity)){
                        this.mob.setTarget(entity);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean searchPlayer(){
        List<Player> list = this.mob.level.getNearbyPlayers(TargetingConditions.forCombat().range(25.0), this.mob, this.mob.getBoundingBox().inflate(30.0, 15.0, 30.0));
        if (!list.isEmpty()) {
            list.sort(new Comparator<LivingEntity>(){
                public int compare(LivingEntity l1, LivingEntity l2){
                    double a = Math.pow(l1.getX() - HeavyMachineGunUsingGoal.this.mob.getX(), 2) + Math.pow(l1.getZ() - HeavyMachineGunUsingGoal.this.mob.getZ(), 2);
                    double b = Math.pow(l2.getX() - HeavyMachineGunUsingGoal.this.mob.getX(), 2) + Math.pow(l2.getZ() - HeavyMachineGunUsingGoal.this.mob.getZ(), 2);
                    return Double.compare(a, b);
                }
            });
            for (Player entity : list) {
                if (isInMachineGunSight(entity) && this.mob.canAttack(entity, TargetingConditions.forCombat())) {
                    this.mob.setTarget(entity);
                    return true;
                }
            }
        }
        return false;
    }



    enum MachineGunState {
        CHARGED,
        OVERHEAT,
        UNCHARGED,
        READY_TO_ATTACK;
    }
}
