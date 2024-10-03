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
public class BombSuitModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("zombiekit", "modelbomb_suit"), "main");
	public final ModelPart Head;
	public final ModelPart Body;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	public final ModelPart LeftShoes;
	public final ModelPart RightShoes;

	public BombSuitModel(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.LeftShoes = root.getChild("LeftShoes");
		this.RightShoes = root.getChild("RightShoes");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Head = partdefinition.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(79, 67).addBox(-3.0F, -6.428F, -5.6382F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(45, 48).addBox(-3.5F, -10.8847F, -3.247F, 7.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-4.5F, -9.7366F, -4.4754F, 9.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(90, 86).addBox(1.25F, -8.8866F, 4.5246F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-4.25F, -8.8866F, 4.5246F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 90).addBox(-1.5F, -9.1366F, 3.7746F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(71, 62)
						.addBox(-3.0F, -9.428F, -5.9382F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(69, 50).addBox(-3.0F, -2.828F, -5.9382F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = Head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 99).addBox(3.0F, -12.0125F, -2.3F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-12.0827F, 1.9886F, 2.5751F, 0.4391F, 0.3715F, 0.9128F));
		PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(77, 43).addBox(-1.0F, -12.0125F, -2.3F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.6681F, 3.7907F, -1.7573F, -1.9538F, 1.3957F, -2.2191F));
		PartDefinition cube_r3 = Head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(76, 55).addBox(-1.0F, -12.0125F, -6.3F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.1605F, 5.8908F, 5.2161F, 1.131F, 1.1176F, 1.8326F));
		PartDefinition cube_r4 = Head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(78, 29).addBox(-5.0F, -12.0125F, -6.3F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.1605F, 5.8908F, 5.2161F, 1.131F, -1.1176F, -1.8326F));
		PartDefinition cube_r5 = Head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(99, 49).addBox(-5.0F, -12.0125F, -2.3F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(12.0827F, 1.9886F, 2.5751F, 0.4391F, -0.3715F, -0.9128F));
		PartDefinition cube_r6 = Head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(50, 68).addBox(3.0F, -12.4F, -2.3F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.373F, 9.572F, -1.5894F, 0.0F, 0.48F, 0.0F));
		PartDefinition cube_r7 = Head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(26, 97).addBox(-5.0F, -12.4F, -2.3F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.373F, 9.572F, -1.5894F, 0.0F, -0.48F, 0.0F));
		PartDefinition cube_r8 = Head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(34, 97).addBox(-5.0F, -12.7625F, -2.325F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.9812F, 3.572F, -1.3119F, 0.0F, -0.5672F, 0.0F));
		PartDefinition cube_r9 = Head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(42, 97).addBox(3.0F, -12.7625F, -2.325F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.9812F, 3.572F, -1.3119F, 0.0F, 0.5672F, 0.0F));
		PartDefinition cube_r10 = Head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(72, 97).addBox(3.0F, -12.0125F, -2.3F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.2467F, 4.6121F, -3.054F, -0.1893F, 0.538F, -0.3578F));
		PartDefinition cube_r11 = Head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(78, 33).addBox(-5.0F, -12.0125F, -2.3F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.6681F, 3.7907F, -1.7573F, -1.9538F, -1.3957F, 2.2191F));
		PartDefinition cube_r12 = Head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(98, 88).addBox(-5.0F, -12.0125F, -2.3F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.2467F, 4.6121F, -3.054F, -0.1893F, -0.538F, 0.3578F));
		PartDefinition cube_r13 = Head.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 55).addBox(-6.0F, -11.0F, -2.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -4.1883F, 5.4799F, 0.9163F, 0.0F, 0.0F));
		PartDefinition cube_r14 = Head.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(83, 3).addBox(-2.75F, -10.9F, 0.25F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.25F, 3.8474F, -1.9796F, -0.5672F, 0.0F, 0.0F));
		PartDefinition cube_r15 = Head.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(47, 77).addBox(-5.8F, -9.0F, -6.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.2841F, 0.4402F, 3.2861F, 0.1384F, 0.5848F, -0.0813F));
		PartDefinition cube_r16 = Head.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(20, 77).addBox(-6.0F, -8.5F, -6.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.2546F, 0.2315F, 5.0494F, 0.1201F, -0.2815F, -0.1916F));
		PartDefinition cube_r17 = Head.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(71, 77).addBox(4.0F, -8.5F, -6.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2546F, 0.2315F, 5.0494F, 0.1201F, 0.2815F, 0.1916F));
		PartDefinition cube_r18 = Head.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(59, 77).addBox(3.8F, -9.0F, -6.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.2841F, 0.4402F, 3.2861F, 0.1384F, -0.5848F, 0.0813F));
		PartDefinition cube_r19 = Head.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(20, 98).addBox(3.0F, -8.0F, -7.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5858F, 1.572F, 2.847F, 0.0F, 0.7854F, 0.0F));
		PartDefinition cube_r20 = Head.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(99, 59).addBox(-5.0F, -8.0F, -7.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5858F, 1.572F, 2.847F, 0.0F, -0.7854F, 0.0F));
		PartDefinition cube_r21 = Head.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(66, 0).addBox(-5.0F, -11.0F, -2.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, 0.0434F, -0.6088F, -0.3927F, 0.0F, 0.0F));
		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 19).addBox(-4.5F, -1.0F, -3.0F, 9.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(31, 54).addBox(0.5F, 2.0F, -1.0F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(50, 18)
						.addBox(-1.5F, 2.0F, -0.75F, 3.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(15, 54).addBox(-3.5F, 2.0F, -1.0F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(81, 0)
						.addBox(-3.0F, 4.0F, -4.25F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(8, 101).addBox(-1.0F, 12.0F, -4.0125F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 52)
						.addBox(-3.5F, 10.3045F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(29, 67).addBox(-2.5F, 1.3045F, -4.0F, 5.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(92, 8)
						.addBox(-2.5F, 5.3545F, -4.2F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(91, 77).addBox(-2.5F, 8.1545F, -4.2F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(45, 51)
						.addBox(2.5F, 10.3045F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r22 = Body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(64, 97).addBox(-2.0F, -3.0F, -28.9875F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9552F, -22.3406F, 12.2546F, 0.8682F, -0.3419F, 0.2766F));
		PartDefinition cube_r23 = Body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(98, 84).addBox(-1.0F, -3.0F, -28.9875F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.9552F, -22.3406F, 12.2546F, 0.8682F, 0.3419F, -0.2766F));
		PartDefinition cube_r24 = Body
				.addOrReplaceChild(
						"cube_r24", CubeListBuilder.create().texOffs(78, 87).addBox(-3.0F, -4.0F, -29.0125F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(80, 99).mirror()
								.addBox(-5.0F, -5.0F, -29.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(80, 99).addBox(2.0F, -5.0F, -29.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.5F, -21.4665F, 13.3938F, 0.9163F, 0.0F, 0.0F));
		PartDefinition cube_r25 = Body.addOrReplaceChild("cube_r25",
				CubeListBuilder.create().texOffs(0, 19).addBox(-1.0F, -5.0F, -29.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(58, 97).addBox(5.95F, -5.0F, -29.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.475F, -9.5313F, 22.3038F, 0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r26 = Body.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(58, 0).addBox(-1.5F, -2.0F, -29.2F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(17.087F, 13.2967F, 9.4534F, -1.0409F, 0.3931F, -0.8012F));
		PartDefinition cube_r27 = Body.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 38).addBox(-1.5F, -4.0F, -29.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.9448F, -11.078F, 21.4108F, 0.3554F, 0.3931F, -0.8012F));
		PartDefinition cube_r28 = Body.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(45, 48).addBox(-1.525F, -2.0F, -29.2F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.7372F, 5.3031F, 17.677F, -0.5609F, 0.3931F, -0.8012F));
		PartDefinition cube_r29 = Body.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 62).addBox(0.5F, -2.0F, -29.2F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-17.087F, 13.2967F, 9.4534F, -1.0409F, -0.3931F, 0.8012F));
		PartDefinition cube_r30 = Body.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(64, 50).addBox(0.525F, -2.0F, -29.2F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.7372F, 5.3031F, 17.677F, -0.5609F, -0.3931F, 0.8012F));
		PartDefinition cube_r31 = Body.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(40, 37).addBox(0.5F, -4.0F, -29.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.9448F, -11.078F, 21.4108F, 0.3554F, -0.3931F, 0.8012F));
		PartDefinition cube_r32 = Body.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(20, 42).addBox(-2.0F, -10.7F, -23.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.4188F, 12.0045F, 17.9575F, 0.0F, 0.2618F, 0.0F));
		PartDefinition cube_r33 = Body.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(64, 55).addBox(1.0F, -10.7F, -22.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.9913F, 12.3883F, 19.9627F, 0.3927F, -0.2618F, 0.0F));
		PartDefinition cube_r34 = Body.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(65, 38).addBox(-2.0F, -10.7F, -22.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.9913F, 12.3883F, 19.9627F, 0.3927F, 0.2618F, 0.0F));
		PartDefinition cube_r35 = Body.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(16, 95).addBox(1.0F, -10.7F, -23.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.4188F, 12.0045F, 17.9575F, 0.0F, -0.2618F, 0.0F));
		PartDefinition cube_r36 = Body.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(83, 47).addBox(3.0F, -15.45F, -19.2F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 2.1258F, 18.4736F, 0.6109F, 0.0F, 0.0F));
		PartDefinition cube_r37 = Body.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(87, 59).addBox(1.0F, -16.7F, -16.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, 3.8465F, 16.0162F, 0.6109F, 0.0F, 0.0F));
		PartDefinition cube_r38 = Body.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(100, 16).addBox(-4.0F, -13.0F, -16.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.1397F, 22.7842F, 12.0125F, 0.0F, 0.0F, -0.3927F));
		PartDefinition cube_r39 = Body.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(100, 92).addBox(2.0F, -13.0F, -16.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.1397F, 22.7842F, 12.0125F, 0.0F, 0.0F, 0.3927F));
		PartDefinition cube_r40 = Body.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(47, 58).addBox(-2.0F, -8.0F, -22.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.4044F, 12.0F, 10.7819F, 0.0F, 0.829F, 0.0F));
		PartDefinition cube_r41 = Body.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(63, 36).addBox(0.0F, -8.0F, -22.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.4044F, 12.0F, 10.7819F, 0.0F, -0.829F, 0.0F));
		PartDefinition cube_r42 = Body.addOrReplaceChild("cube_r42",
				CubeListBuilder.create().texOffs(27, 0).addBox(-3.0F, -13.0F, -18.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(82, 17).addBox(-7.8F, -13.0F, -18.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.4F, -1.5492F, 17.0418F, 0.7418F, 0.0F, 0.0F));
		PartDefinition cube_r43 = Body.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(79, 73).addBox(6.0F, -7.975F, -21.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-12.3971F, -3.6815F, 16.0128F, 0.0F, -0.5672F, 0.48F));
		PartDefinition cube_r44 = Body.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(32, 81).addBox(5.0F, -6.9F, -17.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(72, 20)
				.addBox(1.4F, -6.3F, -16.9F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 83).addBox(-1.2F, -6.9F, -17.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.4F, 12.7339F, 13.9592F, -0.7418F, 0.0F, 0.0F));
		PartDefinition cube_r45 = Body.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(93, 11).addBox(5.8F, -7.0F, -21.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.2209F, -5.8796F, 14.9007F, -0.2391F, -0.5194F, 0.9365F));
		PartDefinition cube_r46 = Body.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(93, 19).addBox(-7.8F, -7.0F, -21.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.2209F, -5.8796F, 14.9007F, -0.2391F, 0.5194F, -0.9365F));
		PartDefinition cube_r47 = Body.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(10, 81).addBox(-8.0F, -7.975F, -21.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(12.3971F, -3.6815F, 16.0128F, 0.0F, 0.5672F, -0.48F));
		PartDefinition cube_r48 = Body.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(47, 55).addBox(-8.0F, -7.0F, -21.0F, 2.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 0.95F, 17.6F, 0.0F, 0.0F, -0.48F));
		PartDefinition cube_r49 = Body.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(56, 0).addBox(6.0F, -7.0F, -21.0F, 2.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, 0.95F, 17.6F, 0.0F, 0.0F, 0.48F));
		PartDefinition cube_r50 = Body.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(45, 37).addBox(-1.0F, -13.0F, -17.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, 12.0F, 18.0F, -0.1745F, 0.0F, 0.0F));
		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm",
				CubeListBuilder.create().texOffs(68, 91).addBox(-2.4628F, 7.4619F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(91, 71).addBox(-2.4628F, 7.3619F, 0.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(92, 53)
						.addBox(-2.4628F, 7.3619F, -2.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(57, 55).addBox(-0.35F, 6.1F, -0.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(61, 20)
						.addBox(-3.6375F, 5.8F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(92, 30).addBox(-0.25F, 6.1F, -2.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(58, 63)
						.addBox(-3.7F, 6.0F, -2.9F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(64, 43).addBox(-3.8F, 2.45F, -2.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(92, 35)
						.addBox(-0.35F, 2.35F, -0.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(92, 40).addBox(-0.25F, 2.35F, -2.9F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(65, 36)
						.addBox(-3.9F, 2.25F, -3.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(36, 0).addBox(-3.5F, -2.75F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition cube_r51 = RightArm.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(13, 67).mirror().addBox(-15.1F, -3.85F, -1.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(11.3233F, -0.6818F, -1.645F, 0.0F, 0.0F, -0.3054F));
		PartDefinition cube_r52 = RightArm.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(60, 26).mirror().addBox(-16.0F, -5.75F, -1.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(13.0F, 0.1F, -2.0F, 0.0F, 0.0F, -0.1745F));
		PartDefinition cube_r53 = RightArm.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(83, 37).mirror().addBox(-14.6F, -5.75F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(10.208F, 3.5998F, -1.745F, 0.0F, 0.0F, 0.0436F));
		PartDefinition cube_r54 = RightArm.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(72, 5).mirror().addBox(-14.5F, -5.75F, -2.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(9.8318F, 3.5912F, 3.3F, 0.0F, -0.1309F, 0.0436F));
		PartDefinition cube_r55 = RightArm.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(97, 0).addBox(-19.5F, 3.25F, -0.2F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(86, 95)
				.addBox(-19.5F, 3.25F, 3.2F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 92).addBox(-19.7F, 2.45F, 1.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, -1.0F, -2.5F, 0.0F, 0.0F, -0.3054F));
		PartDefinition cube_r56 = RightArm.addOrReplaceChild("cube_r56",
				CubeListBuilder.create().texOffs(96, 24).addBox(-20.0F, 3.25F, -0.1625F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(94, 95).addBox(-20.0F, 3.25F, 3.2375F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(18.0058F, 0.0632F, -2.5375F, 0.0F, 0.0F, -0.2182F));
		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm",
				CubeListBuilder.create().texOffs(30, 19).addBox(-1.5F, -2.75F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(61, 13).addBox(-0.1F, 2.25F, -3.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(50, 32)
						.addBox(-1.75F, 2.35F, -2.9F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(51, 0).addBox(-1.65F, 2.35F, -0.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 62)
						.addBox(-0.2F, 2.45F, -2.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(63, 55).addBox(-0.3F, 6.0F, -2.9F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(26, 54)
						.addBox(-1.75F, 6.1F, -2.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(42, 55).addBox(-0.3625F, 5.8F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(56, 13)
						.addBox(-1.65F, 6.1F, -0.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(48, 91).addBox(-0.5372F, 7.3619F, -2.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(38, 91)
						.addBox(-0.5372F, 7.3619F, 0.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(58, 91).addBox(-0.5372F, 7.4619F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition cube_r57 = LeftArm.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(20, 91).addBox(17.7F, 2.45F, -1.8F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(93, 45)
				.addBox(17.5F, 3.25F, -0.1F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(78, 93).addBox(17.5F, 3.25F, -3.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, -1.0F, 0.8F, 0.0F, 0.0F, 0.3054F));
		PartDefinition cube_r58 = LeftArm.addOrReplaceChild("cube_r58",
				CubeListBuilder.create().texOffs(93, 65).addBox(18.0F, 3.25F, -1.8375F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(8, 95).addBox(18.0F, 3.25F, -5.2375F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-18.0058F, 0.0632F, 2.5375F, 0.0F, 0.0F, 0.2182F));
		PartDefinition cube_r59 = LeftArm.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(72, 5).addBox(10.5F, -5.75F, -2.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-9.8318F, 3.5912F, 3.3F, 0.0F, 0.1309F, -0.0436F));
		PartDefinition cube_r60 = LeftArm.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(83, 37).addBox(11.6F, -5.75F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.208F, 3.5998F, -1.745F, 0.0F, 0.0F, -0.0436F));
		PartDefinition cube_r61 = LeftArm.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(13, 67).addBox(12.1F, -3.85F, -1.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-11.3233F, -0.6818F, -1.645F, 0.0F, 0.0F, 0.3054F));
		PartDefinition cube_r62 = LeftArm.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(60, 26).addBox(13.0F, -5.75F, -1.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.0F, 0.1F, -2.0F, 0.0F, 0.0F, 0.1745F));
		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(25, 37).addBox(-2.7F, 0.1F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition cube_r63 = RightLeg.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(15, 38).addBox(1.0F, -9.0F, 6.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.9F, 9.9367F, -3.2654F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r64 = RightLeg.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(79, 81).addBox(3.0F, 1.0F, 2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.4839F, 6.0402F, -4.2907F, 0.0532F, 0.1091F, -0.3252F));
		PartDefinition cube_r65 = RightLeg.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(42, 85).addBox(3.0F, 1.0F, 2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.6F, 3.1F, -4.65F, 0.0835F, 0.0881F, -0.0189F));
		PartDefinition cube_r66 = RightLeg.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(55, 77).addBox(3.0F, 1.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.222F, 2.4114F, -0.5045F, -0.091F, 0.0881F, -0.0189F));
		PartDefinition cube_r67 = RightLeg.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(18, 85).addBox(-5.0F, 1.0F, 2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.9F, 3.1F, -4.85F, 0.0833F, -0.0446F, 0.0226F));
		PartDefinition cube_r68 = RightLeg.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(65, 70).addBox(-5.0F, 1.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.73F, 2.4152F, -0.6923F, -0.0912F, -0.0446F, 0.0226F));
		PartDefinition cube_r69 = RightLeg.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(27, 5).addBox(-5.0F, 1.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.93F, 2.4152F, -0.6923F, -0.0912F, -0.0446F, 0.0226F));
		PartDefinition cube_r70 = RightLeg.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(87, 80).addBox(2.0F, -4.0F, 2.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.2F, 7.85F, -4.5F, 0.3054F, 0.0F, 0.0F));
		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 38).addBox(-2.3F, 0.1F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition cube_r71 = LeftLeg.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(8, 90).addBox(-6.0F, -4.0F, 2.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.2F, 7.85F, -4.5F, 0.3054F, 0.0F, 0.0F));
		PartDefinition cube_r72 = LeftLeg.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(67, 77).addBox(3.0F, 1.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.93F, 2.4152F, -0.6923F, -0.0912F, 0.0446F, -0.0226F));
		PartDefinition cube_r73 = LeftLeg.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(50, 85).addBox(3.0F, 1.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.73F, 2.4152F, -0.6923F, -0.0912F, 0.0446F, -0.0226F));
		PartDefinition cube_r74 = LeftLeg.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(85, 47).addBox(3.0F, 1.0F, 2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.9F, 3.1F, -4.85F, 0.0833F, 0.0446F, -0.0226F));
		PartDefinition cube_r75 = LeftLeg.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(50, 97).addBox(-5.0F, 1.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.222F, 2.4114F, -0.5045F, -0.091F, -0.0881F, 0.0189F));
		PartDefinition cube_r76 = LeftLeg.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(84, 8).mirror().addBox(-5.0F, 1.0F, 2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(6.4839F, 6.0402F, -4.2907F, 0.0532F, -0.1091F, 0.3252F));
		PartDefinition cube_r77 = LeftLeg.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(54, 85).addBox(-5.0F, 1.0F, 2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.6F, 3.1F, -4.65F, 0.0835F, -0.0881F, 0.0189F));
		PartDefinition cube_r78 = LeftLeg.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(0, 58).addBox(-6.0F, -9.0F, 6.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.9F, 9.9367F, -3.2654F, 0.1309F, 0.0F, 0.0F));
		PartDefinition LeftShoes = partdefinition.addOrReplaceChild("LeftShoes",
				CubeListBuilder.create().texOffs(69, 70).addBox(-0.1F, 9.1F, -2.9F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(45, 18).addBox(-0.1F, 9.1F, 1.1F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(66, 85)
						.addBox(-2.6F, 9.0F, 0.3F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 77).addBox(-2.6F, 10.0F, -3.7F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition cube_r79 = LeftShoes.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(0, 70).addBox(-4.0F, 1.0F, 2.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.8705F, 9.0F, -3.8221F, 0.0F, -0.4363F, 0.0F));
		PartDefinition cube_r80 = LeftShoes.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(37, 75).addBox(-4.0F, 1.0F, 2.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.375F, 10.0365F, -5.9358F, 0.48F, 0.0F, 0.0F));
		PartDefinition RightShoes = partdefinition.addOrReplaceChild("RightShoes",
				CubeListBuilder.create().texOffs(10, 75).addBox(-0.4F, 10.0F, -3.7F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(84, 22).addBox(-0.4F, 9.0F, 0.3F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(24, 19)
						.addBox(-2.9F, 9.1F, 1.1F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(55, 70).addBox(-2.9F, 9.1F, -2.9F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition cube_r81 = RightShoes.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(74, 11).addBox(1.0F, 1.0F, 2.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.375F, 10.0365F, -5.9358F, 0.48F, 0.0F, 0.0F));
		PartDefinition cube_r82 = RightShoes.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(41, 68).addBox(2.0F, 1.0F, 2.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.8705F, 9.0F, -3.8221F, 0.0F, 0.4363F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftShoes.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightShoes.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
