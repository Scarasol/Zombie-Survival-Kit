package com.scarasol.zombiekit.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class UVlampModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("zombiekit", "model_u_vlamp"), "main");
	public final ModelPart lamp;
	public final ModelPart lamp2;
	public final ModelPart lamp3;
	public final ModelPart bb_main;

	public UVlampModel(ModelPart root) {
		this.lamp = root.getChild("lamp");
		this.lamp2 = root.getChild("lamp2");
		this.lamp3 = root.getChild("lamp3");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition lamp = partdefinition.addOrReplaceChild("lamp",
				CubeListBuilder.create().texOffs(0, 19).addBox(-4.0F, -5.0F, 5.475F, 8.0F, 7.0F, 2.0F, new CubeDeformation(-0.25F)).texOffs(46, 29).addBox(-4.5F, -5.125F, 6.725F, 9.0F, 1.0F, 2.0F, new CubeDeformation(-0.35F)).texOffs(23, 29)
						.addBox(-4.5F, 1.125F, 6.725F, 9.0F, 1.0F, 2.0F, new CubeDeformation(-0.35F)).texOffs(36, 75).addBox(3.4F, -5.0F, 5.625F, 1.0F, 7.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(27, 75)
						.addBox(-4.4F, -5.0F, 5.625F, 1.0F, 7.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(17, 55).addBox(-3.0F, -4.0F, 3.975F, 6.0F, 5.0F, 2.0F, new CubeDeformation(-0.25F)).texOffs(24, 86)
						.addBox(-2.1F, -3.6F, 3.475F, 4.0F, 4.0F, 1.0F, new CubeDeformation(-0.25F)).texOffs(27, 92).addBox(2.0F, -2.6F, 1.475F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(18, 92)
						.addBox(-3.0F, -2.6F, 1.475F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(48, 86).addBox(-2.5F, -2.6F, 1.475F, 5.0F, 2.0F, 1.0F, new CubeDeformation(-0.25F)),
				PartPose.offset(0.0F, 2.0F, 0.0F));
		PartDefinition cube_r1 = lamp.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 35).addBox(-4.0F, -0.375F, -0.6F, 8.0F, 4.0F, 1.0F, new CubeDeformation(-0.25F)),
				PartPose.offsetAndRotation(0.0F, -1.5F, 6.825F, 0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r2 = lamp.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 47).addBox(-4.0F, -3.6F, -0.6F, 8.0F, 4.0F, 1.0F, new CubeDeformation(-0.25F)),
				PartPose.offsetAndRotation(0.0F, -1.5F, 6.825F, -0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r3 = lamp.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(15, 64).addBox(-2.325F, -3.5F, -0.9F, 6.0F, 7.0F, 1.0F, new CubeDeformation(-0.251F)),
				PartPose.offsetAndRotation(0.5F, -1.5F, 7.075F, 0.0F, -0.4363F, 0.0F));
		PartDefinition cube_r4 = lamp.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(66, 55).addBox(-3.675F, -3.5F, -0.9F, 6.0F, 7.0F, 1.0F, new CubeDeformation(-0.251F)),
				PartPose.offsetAndRotation(-0.5F, -1.5F, 7.075F, 0.0F, 0.4363F, 0.0F));
		PartDefinition lamp2 = partdefinition.addOrReplaceChild("lamp2",
				CubeListBuilder.create().texOffs(49, 0).addBox(6.8011F, -5.0F, 0.8625F, 8.0F, 7.0F, 2.0F, new CubeDeformation(-0.25F)).texOffs(0, 29).addBox(6.3011F, -5.125F, 2.1125F, 9.0F, 1.0F, 2.0F, new CubeDeformation(-0.35F)).texOffs(44, 19)
						.addBox(6.3011F, 1.125F, 2.1125F, 9.0F, 1.0F, 2.0F, new CubeDeformation(-0.35F)).texOffs(18, 75).addBox(14.2011F, -5.0F, 1.0125F, 1.0F, 7.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(9, 75)
						.addBox(6.4011F, -5.0F, 1.0125F, 1.0F, 7.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(34, 55).addBox(7.8011F, -4.0F, -0.6375F, 6.0F, 5.0F, 2.0F, new CubeDeformation(-0.25F)).texOffs(13, 86)
						.addBox(8.7011F, -3.6F, -1.1375F, 4.0F, 4.0F, 1.0F, new CubeDeformation(-0.25F)).texOffs(9, 92).addBox(12.8011F, -2.6F, -3.1375F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(61, 86)
						.addBox(7.8011F, -2.6F, -3.1375F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(0, 86).addBox(8.3011F, -2.6F, -3.1375F, 5.0F, 2.0F, 1.0F, new CubeDeformation(-0.25F)),
				PartPose.offsetAndRotation(9.275F, 2.0F, 7.275F, 0.0F, 2.0944F, 0.0F));
		PartDefinition cube_r5 = lamp2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 41).addBox(-3.1989F, -1.7656F, 0.5587F, 8.0F, 4.0F, 1.0F, new CubeDeformation(-0.25F)),
				PartPose.offsetAndRotation(10.0F, 0.25F, 1.75F, 0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r6 = lamp2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(69, 29).addBox(-3.1989F, -5.3815F, -0.9204F, 8.0F, 4.0F, 1.0F, new CubeDeformation(-0.25F)),
				PartPose.offsetAndRotation(10.0F, 0.25F, 1.75F, -0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r7 = lamp2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 64).addBox(-1.4035F, -5.25F, -0.8194F, 6.0F, 7.0F, 1.0F, new CubeDeformation(-0.251F)),
				PartPose.offsetAndRotation(10.5F, 0.25F, 2.0F, 0.0F, -0.4363F, 0.0F));
		PartDefinition cube_r8 = lamp2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(45, 64).addBox(-3.1444F, -5.25F, -0.1423F, 6.0F, 7.0F, 1.0F, new CubeDeformation(-0.251F)),
				PartPose.offsetAndRotation(9.5F, 0.25F, 2.0F, 0.0F, 0.4363F, 0.0F));
		PartDefinition lamp3 = partdefinition.addOrReplaceChild("lamp3",
				CubeListBuilder.create().texOffs(70, 0).addBox(-19.8011F, -5.0F, 1.8625F, 8.0F, 7.0F, 2.0F, new CubeDeformation(-0.25F)).texOffs(21, 23).addBox(-20.3011F, -5.125F, 3.1125F, 9.0F, 1.0F, 2.0F, new CubeDeformation(-0.35F))
						.texOffs(21, 19).addBox(-20.3011F, 1.125F, 3.1125F, 9.0F, 1.0F, 2.0F, new CubeDeformation(-0.35F)).texOffs(0, 75).addBox(-12.4011F, -5.0F, 2.0125F, 1.0F, 7.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(60, 64)
						.addBox(-20.2011F, -5.0F, 2.0125F, 1.0F, 7.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(0, 55).addBox(-18.8011F, -4.0F, 0.3625F, 6.0F, 5.0F, 2.0F, new CubeDeformation(-0.25F)).texOffs(45, 75)
						.addBox(-17.9011F, -3.6F, -0.1375F, 4.0F, 4.0F, 1.0F, new CubeDeformation(-0.25F)).texOffs(0, 92).addBox(-13.8011F, -2.6F, -2.1375F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(70, 86)
						.addBox(-18.8011F, -2.6F, -2.1375F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)).texOffs(35, 86).addBox(-18.3011F, -2.6F, -2.1375F, 5.0F, 2.0F, 1.0F, new CubeDeformation(-0.25F)),
				PartPose.offsetAndRotation(-10.9F, 2.0F, 12.1F, 0.0F, -2.0944F, 0.0F));
		PartDefinition cube_r9 = lamp3.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(88, 29).addBox(-4.8011F, -1.7656F, 0.5587F, 8.0F, 4.0F, 1.0F, new CubeDeformation(-0.25F)),
				PartPose.offsetAndRotation(-15.0F, 0.25F, 2.75F, 0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r10 = lamp3.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 35).addBox(-4.8011F, -5.3815F, -0.9204F, 8.0F, 4.0F, 1.0F, new CubeDeformation(-0.25F)),
				PartPose.offsetAndRotation(-15.0F, 0.25F, 2.75F, -0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r11 = lamp3.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(51, 55).addBox(-2.8556F, -5.25F, -0.1423F, 6.0F, 7.0F, 1.0F, new CubeDeformation(-0.251F)),
				PartPose.offsetAndRotation(-14.5F, 0.25F, 3.0F, 0.0F, -0.4363F, 0.0F));
		PartDefinition cube_r12 = lamp3.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(30, 64).addBox(-4.5965F, -5.25F, -0.8194F, 6.0F, 7.0F, 1.0F, new CubeDeformation(-0.251F)),
				PartPose.offsetAndRotation(-15.5F, 0.25F, 3.0F, 0.0F, 0.4363F, 0.0F));
		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main",
				CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -7.0F, -6.0F, 12.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(62, 92).addBox(5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(57, 95)
						.addBox(5.0F, -1.0F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(57, 92).addBox(-6.0F, -1.0F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(50, 95)
						.addBox(-6.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(19, 35).addBox(-0.5F, -24.0F, -0.5F, 1.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(36, 92)
						.addBox(-0.5F, -24.0F, -0.275F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition cube_r13 = bb_main.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(50, 92).addBox(-1.8F, -0.5F, -0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -23.5F, 0.5F, 0.0F, -0.5236F, 0.0F));
		PartDefinition cube_r14 = bb_main.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(43, 92).addBox(-0.85F, -0.5F, -1.8F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -23.5F, 0.5F, 0.0F, -1.0472F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		lamp.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		lamp2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		lamp3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
