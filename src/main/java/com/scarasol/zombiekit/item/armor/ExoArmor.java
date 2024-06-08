package com.scarasol.zombiekit.item.armor;

import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.client.model.ExoSuitModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExoArmor extends ArmorItem {

    public static AttributeModifier COMBAT_MOVEMENT = new AttributeModifier(UUID.fromString("5987CE5F-1549-3D44-EA5E-33BA82D76A95"), "ExoCombat", 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL);

    public ExoArmor(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Override
    public void onArmorTick(ItemStack itemStack, Level level, Player player){
        if (getSlot() == EquipmentSlot.CHEST && numberOfSuit(player) == 4){
            if (player.getLevel() instanceof ServerLevel serverLevel){
                if (getPower(itemStack) > 0){
                    fallProtect(itemStack, player, serverLevel);
                    modeFunction(itemStack, player, serverLevel);
                }else {
                    setReactiveArmor(itemStack, -1);
                    switchRadar(itemStack, false);
                    switchMode(itemStack, 0);
                }
            }
        }
    }

    public void fallProtect(ItemStack itemStack, Player player, ServerLevel serverLevel){
        if (!player.isInWaterOrBubble() && !player.isFallFlying() && player.fallDistance > 5 && !player.hasEffect(MobEffects.SLOW_FALLING)){
            serverLevel.sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 100, 0.5, 0.2, 0.5, 0.1);
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0, false, false));
            addPower(itemStack, -2);
        }
        if (!player.isOnGround() && player.hasEffect(MobEffects.SLOW_FALLING) && serverLevel.getGameTime() % 10 == 0){
            serverLevel.sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 5, 0, 0.2, 0, 0.05);
        }
    }

    public void modeFunction(ItemStack itemStack, Player player, ServerLevel serverLevel){
        switch (getMode(itemStack)){
            case 1 -> {
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20, 0, false, false));
                player.addEffect(new MobEffectInstance(SonaMobEffects.CAMOUFLAGE.get(), 20, 4, false, false));
                if (serverLevel.getGameTime() % 120 == 0)
                    addPower(itemStack, -1);
            }
            case 2 -> {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20, 1, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 0, false, false));
                if (serverLevel.getGameTime() % 60 == 0)
                    addPower(itemStack, -1);
            }
        }
        int reactiveArmor = getReactiveArmor(itemStack);
        if (reactiveArmor >= 0){
            addReactiveArmor(itemStack, 1);
            if (reactiveArmor >= 200){
                if (serverLevel.getGameTime() % 20 == 0)
                    serverLevel.playSound(null, player, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:reactive_armor_ready")), SoundSource.PLAYERS, 1, 1);
                serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK, player.getX(), player.getY() + 0.5, player.getZ(), 1, 0.2, 0.5, 0.2, 0.05);
            }
            if (serverLevel.getGameTime() % 60 == 0)
                addPower(itemStack, -1);
        }
        if (getRadar(itemStack))
            if (serverLevel.getGameTime() % 120 == 0)
                addPower(itemStack, -1);
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return numberOfSuit(entity) == 4 && getMode(stack) != 1 && getPower(stack) > 5;
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (numberOfSuit(entity) < 4)
            return false;
        if (entity.getLevel() instanceof ServerLevel serverLevel) {
            if (flightTicks % 60 == 0)
                addPower(stack, -1);
            if (flightTicks % 10 == 0)
                serverLevel.playSound(null, entity, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:exo_fly")), SoundSource.PLAYERS, 1, 1);
            if (entity.isSprinting()){
                if (flightTicks % 2 == 0)
                    serverLevel.sendParticles(ParticleTypes.FIREWORK, entity.getX(), entity.getY(), entity.getZ(), 1, entity.getRandom().nextGaussian() * 0.05D, -entity.getDeltaMovement().y * 0.5D, entity.getRandom().nextGaussian() * 0.05D, 0.01);
            }else return !entity.isShiftKeyDown();
        }
        return true;
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot slot, String type) {
        return "zombiekit:textures/entities/exo_suit.png";
    }



    public HumanoidModel getArmorModel(LivingEntity livingEntity){
        Map<String, ModelPart> map = new HashMap<>(Map.of(
                "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
        ));
        if (!(livingEntity.hasEffect(MobEffects.INVISIBILITY) && numberOfSuit(livingEntity) == 4 && getMode(livingEntity.getItemBySlot(EquipmentSlot.CHEST)) == 1)){
            switch (getSlot()){
                case HEAD -> map.put("head", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).Head);
                case CHEST -> {
                    map.put("body", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).Body);
                    map.put("right_arm", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).RightArm);
                    map.put("left_arm", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).LeftArm);
                }
                case LEGS -> {
                    map.put("right_leg", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).RightLeg);
                    map.put("left_leg", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).LeftLeg);
                }
                default -> {
                    map.put("right_leg", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).RightShoes);
                    map.put("left_leg", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).LeftShoes);
                }
            }
        }
        return new HumanoidModel(new ModelPart(Collections.emptyList(), Map.copyOf(map)));
    }

    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = ExoArmor.this.getArmorModel(living);
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }

    public static int numberOfSuit(LivingEntity livingEntity){
        int count = 0;
        for (ItemStack itemStack : livingEntity.getArmorSlots()){
            if (itemStack.getItem() instanceof ExoArmor)
                count++;
        }
        return count;
    }

    public static void switchMode(Player livingEntity, int mode){
        if (ExoArmor.numberOfSuit(livingEntity) < 4)
            return;
        ItemStack chest = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (getPower(chest) <= 0){
            livingEntity.displayClientMessage(new TranslatableComponent("item.zombiekit.exo.no_power"), true);
            return;
        }
        int currentMode = getMode(chest);
        if (mode == 3){
            if (currentMode == 1){
                livingEntity.displayClientMessage(new TranslatableComponent("item.zombiekit.exo.no_reactive_armor"), true);
            }else {
                if (getReactiveArmor(chest) >= 0){
                    livingEntity.displayClientMessage(new TranslatableComponent("item.zombiekit.exo.reactive_armor_off"), true);
                    setReactiveArmor(chest, -1);
                }else {
                    livingEntity.displayClientMessage(new TranslatableComponent("item.zombiekit.exo.reactive_armor_on"), true);
                    setReactiveArmor(chest, 0);
                }

            }
        }else if (mode == 4){
            if (getRadar(chest)){
                livingEntity.displayClientMessage(new TranslatableComponent("item.zombiekit.exo.radar_off"), true);
                switchRadar(chest, false);
            }else {
                livingEntity.displayClientMessage(new TranslatableComponent("item.zombiekit.exo.radar_on"), true);
                switchRadar(chest, true);
            }
        }else {
            if (currentMode == mode){
                switchMode(chest, 0);
                livingEntity.getLevel().playSound(null, livingEntity, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:mode_off")), SoundSource.PLAYERS, 1, 1);
                livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(COMBAT_MOVEMENT);
                livingEntity.displayClientMessage(new TranslatableComponent("item.zombiekit.exo.exit_mode"), true);
            }else {
                switchMode(chest, mode);
                livingEntity.getLevel().playSound(null, livingEntity, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:mode_on")), SoundSource.PLAYERS, 1, 1);
                if (mode == 1){
                    setReactiveArmor(chest, -1);
                    livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(COMBAT_MOVEMENT);
                    livingEntity.displayClientMessage(new TranslatableComponent("item.zombiekit.exo.sneak_mode"), true);
                }else if (mode == 2){
                    livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(COMBAT_MOVEMENT);
                    livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(COMBAT_MOVEMENT);
                    livingEntity.displayClientMessage(new TranslatableComponent("item.zombiekit.exo.combat_mode"), true);
                }
            }
        }
    }

    public static boolean reactiveArmor(LivingEntity target, Entity attacker) {
        int count = numberOfSuit(target);
        if (count == 4 && getReactiveArmor(target.getItemBySlot(EquipmentSlot.CHEST)) >= 200){
            Vec3 vec3;
            if (attacker instanceof Projectile projectile && projectile.getOwner() != null){
                vec3 = new Vec3(projectile.getOwner().getX() - target.getX(), projectile.getOwner().getY() - target.getY(), projectile.getOwner().getZ() - target.getZ());
            }else {
                vec3 = new Vec3(attacker.getX() - target.getX(), attacker.getY() - target.getY(), attacker.getZ() - target.getZ()).scale(1 / attacker.distanceTo(target));
            }

            attacker.setDeltaMovement(vec3.scale(1.5));
            setReactiveArmor(target.getItemBySlot(EquipmentSlot.CHEST), 0);
            target.getLevel().playSound(null, target, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:reactive_armor_release")), SoundSource.PLAYERS, 1, 1);
            if (attacker instanceof LivingEntity livingEntity)
                livingEntity.addEffect(new MobEffectInstance(SonaMobEffects.STUN.get(), 100, 0, false, false));
            return true;
        }
        return false;
    }

    public static void updateModifier(LivingEntity livingEntity) {
        int count = numberOfSuit(livingEntity);
        if (count < 4 || getMode(livingEntity.getItemBySlot(EquipmentSlot.CHEST)) != 2){
            livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(COMBAT_MOVEMENT);
        }
    }
    
    public static void addPower(ItemStack itemStack, int power){
        int currentPower = itemStack.getOrCreateTag().getInt("Power");
        if (power > 0){
            power = Math.min(currentPower + power, 100);
        }else {
            power = Math.max(currentPower + power, 0);
        }
        setPower(itemStack, power);
    }

    public static void setPower(ItemStack itemStack, int power){
        itemStack.getOrCreateTag().putInt("Power", power);
    }

    public static int getPower(ItemStack itemStack){
        return itemStack.getOrCreateTag().getInt("Power");
    }

    public static void switchMode(ItemStack itemStack, int mode){
        itemStack.getOrCreateTag().putInt("Mode", mode);
    }

    public static int getMode(ItemStack itemStack){
        return itemStack.getOrCreateTag().getInt("Mode");
    }

    public static boolean getRadar(ItemStack itemStack){
        return itemStack.getOrCreateTag().getBoolean("Radar");
    }

    public static void switchRadar(ItemStack itemStack, boolean radar){
        itemStack.getOrCreateTag().putBoolean("Radar", radar);
    }

    public static int getReactiveArmor(ItemStack itemStack){
        return itemStack.getOrCreateTag().getInt("ReactiveArmor");
    }

    public static void setReactiveArmor(ItemStack itemStack, int reactiveArmor){
        itemStack.getOrCreateTag().putInt("ReactiveArmor", reactiveArmor);
    }

    public static void addReactiveArmor(ItemStack itemStack, int reactiveArmor){
        int coolDown = itemStack.getOrCreateTag().getInt("ReactiveArmor");
        if (reactiveArmor > 0){
            reactiveArmor = Math.min(coolDown + reactiveArmor, 200);
        }else {
            reactiveArmor = Math.max(coolDown + reactiveArmor, 0);
        }
        setReactiveArmor(itemStack, reactiveArmor);
    }
}
