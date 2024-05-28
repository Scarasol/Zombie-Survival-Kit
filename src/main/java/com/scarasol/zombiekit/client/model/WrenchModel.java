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

// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class WrenchModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("zombiekit", "modelmonkey_wrench"), "main");
	public final ModelPart bb_main;

	public WrenchModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main",
				CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -19.75F, -1.0F, 4.0F, 20.0F, 2.0F, new CubeDeformation(-0.002F)).texOffs(24, 25).addBox(-2.5122F, -26.8659F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(-0.001F)).texOffs(26, 19)
						.addBox(-1.6122F, -24.8659F, -0.975F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 29).addBox(-2.0F, 0.25F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(28, 23)
						.addBox(1.0F, 0.25F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(20, 16).addBox(-2.0F, 2.25F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(11, 28).addBox(-3.0F, -15.0F, -0.9875F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.6003F, -12.5906F, -9.0F, -0.7854F, 0.0F, -0.4363F));
		PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(11, 28).addBox(-3.0F, -15.0F, -1.0125F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.6003F, -12.5906F, 9.0F, 0.7854F, 0.0F, -0.4363F));
		PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(20, 19).addBox(12.0F, -10.8F, -1.0125F, 1.0F, 6.0F, 2.0F, new CubeDeformation(-0.002F)),
				PartPose.offsetAndRotation(-1.6776F, -11.3382F, 0.0125F, 0.0F, 0.0F, -0.6981F));
		PartDefinition cube_r4 = bb_main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 22).addBox(11.0F, -11.8F, -1.0125F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.2321F, -12.4737F, 0.0125F, 0.0F, 0.0F, -0.3927F));
		PartDefinition cube_r5 = bb_main.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(6, 22).addBox(1.0F, -16.0F, -1.0125F, 1.0F, 5.0F, 2.0F, new CubeDeformation(-0.002F)),
				PartPose.offsetAndRotation(2.6869F, -11.7012F, 0.0125F, 0.0F, 0.0F, -0.3927F));
		PartDefinition cube_r6 = bb_main.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(20, 0).addBox(0.0F, -14.8F, -1.0F, 4.0F, 8.0F, 2.0F, new CubeDeformation(-0.001F)),
				PartPose.offsetAndRotation(-5.2321F, -12.4737F, 0.0F, 0.0F, 0.0F, 0.5236F));
		PartDefinition cube_r7 = bb_main.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(20, 10).addBox(-3.0F, -15.0F, -1.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.4003F, -9.6406F, 0.0F, 0.0F, 0.0F, -0.4363F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
