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
public class SkiingSuitModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("zombiekit", "modelskiing_suit"), "main");
	public final ModelPart Head;
	public final ModelPart Body;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	public final ModelPart LeftShoes;
	public final ModelPart RightShoes;

	public SkiingSuitModel(ModelPart root) {
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
				CubeListBuilder.create().texOffs(62, 24).addBox(-1.5F, -3.3F, -4.8F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F)).texOffs(76, 37).addBox(-3.4F, -2.7F, -4.8F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F)).texOffs(74, 71)
						.addBox(-3.5F, -4.5F, -4.4625F, 7.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 43).addBox(-4.0F, -4.7F, -4.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.2F)).texOffs(44, 28)
						.addBox(3.0F, -4.7F, -4.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.2F)).texOffs(40, 57).addBox(0.4F, -2.7F, -4.8F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F)).texOffs(24, 20)
						.addBox(-4.0F, -5.3F, -4.8F, 8.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F)).texOffs(0, 0).addBox(-4.5F, -4.7F, -4.5F, 9.0F, 2.0F, 9.0F, new CubeDeformation(-0.2F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 11).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(24, 11).addBox(-4.5F, 8.1F, -2.5F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(49, 62)
						.addBox(-2.6F, 4.35F, -2.85F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(42, 20).addBox(-2.2375F, 4.25F, -2.95F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(42, 17)
						.addBox(-0.9375F, 4.25F, -2.95F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(19, 22).addBox(-4.5F, 9.9F, -2.5F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = Body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(83, 13).addBox(-5.0F, 9.0F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.3427F, -5.0722F, -1.3347F, 0.1657F, 0.2709F, 0.0647F));
		PartDefinition cube_r2 = Body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 53).addBox(-5.0F, 1.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.0712F, -0.3773F, 0.5273F, 0.1657F, 0.2527F, -0.0692F));
		PartDefinition cube_r3 = Body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(25, 61).addBox(-5.0F, 12.0F, -7.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.4789F, -10.6085F, 8.2432F, -0.5832F, -0.973F, 0.7361F));
		PartDefinition cube_r4 = Body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(40, 61).addBox(4.0F, 12.0F, -7.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.4789F, -10.6085F, 8.2432F, -0.5832F, 0.973F, -0.7361F));
		PartDefinition cube_r5 = Body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(88, 81).addBox(4.0F, 9.0F, 2.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5165F, -5.0745F, -0.8961F, -0.0181F, 1.0817F, -0.083F));
		PartDefinition cube_r6 = Body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(89, 11).addBox(-5.0F, 9.0F, 2.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5165F, -5.0745F, -0.8961F, -0.0181F, -1.0817F, 0.083F));
		PartDefinition cube_r7 = Body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(30, 57).addBox(-5.0F, 9.0F, -1.0F, 1.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.1117F, -5.1083F, -1.6822F, -0.0088F, -0.2527F, 0.0692F));
		PartDefinition cube_r8 = Body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(6, 83).addBox(-5.0F, 9.0F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.6061F, -5.1314F, -0.4021F, -0.0088F, 0.2709F, 0.0647F));
		PartDefinition cube_r9 = Body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(89, 20).addBox(-5.0F, 1.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.0176F, -0.3699F, 0.7321F, -0.2706F, 0.2527F, -0.0692F));
		PartDefinition cube_r10 = Body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(76, 29).addBox(-5.0F, 1.0F, -1.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -0.7F, -0.5F, -0.2706F, -0.2527F, 0.0692F));
		PartDefinition cube_r11 = Body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(75, 0).addBox(-5.9F, 15.5F, 4.6F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.8324F, -5.4446F, 0.3702F, 2.7639F, 0.2854F, 2.9599F));
		PartDefinition cube_r12 = Body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(4, 75).addBox(4.9F, 15.5F, 4.6F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.8324F, -5.4446F, 0.3702F, 2.7639F, -0.2854F, -2.9599F));
		PartDefinition cube_r13 = Body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(18, 63).addBox(-5.25F, 12.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.7572F, -0.8264F, -6.3348F, 1.7205F, 1.1889F, 1.7387F));
		PartDefinition cube_r14 = Body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(63, 43).addBox(4.25F, 12.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.7572F, -0.8264F, -6.3348F, 1.7205F, -1.1889F, -1.7387F));
		PartDefinition cube_r15 = Body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(17, 72).addBox(4.0F, 9.0F, -1.0F, 1.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5036F, -4.7125F, -3.7977F, 1.9072F, -1.4016F, -1.9316F));
		PartDefinition cube_r16 = Body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(49, 72).addBox(-5.0F, 9.0F, -1.0F, 1.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5036F, -4.7125F, -3.7977F, 1.9072F, 1.4016F, 1.9316F));
		PartDefinition cube_r17 = Body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 81).addBox(-5.0F, 9.0F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.6237F, -4.7294F, -3.832F, 0.9384F, 1.3725F, 0.9489F));
		PartDefinition cube_r18 = Body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(12, 81).addBox(4.0F, 9.0F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.6237F, -4.7294F, -3.832F, 0.9384F, -1.3725F, -0.9489F));
		PartDefinition cube_r19 = Body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(18, 83).addBox(4.0F, 9.0F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.3427F, -5.0722F, -1.3347F, 0.1657F, -0.2709F, -0.0647F));
		PartDefinition cube_r20 = Body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(63, 83).addBox(4.0F, 9.0F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.6061F, -5.1314F, -0.4021F, -0.0088F, -0.2709F, -0.0647F));
		PartDefinition cube_r21 = Body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(74, 54).addBox(-5.0F, 9.0F, -1.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.9728F, 2.85F, -3.2779F, 0.1657F, -0.2527F, 0.0692F));
		PartDefinition cube_r22 = Body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 11).addBox(4.0F, 9.0F, 2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.3877F, 2.7697F, -2.5006F, 0.3337F, 1.0613F, 0.1828F));
		PartDefinition cube_r23 = Body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 27).addBox(-5.0F, 9.0F, 2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.3877F, 2.7697F, -2.5006F, 0.3337F, -1.0613F, -0.1828F));
		PartDefinition cube_r24 = Body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(75, 8).addBox(4.0F, 9.0F, -1.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.9728F, 2.85F, -3.2779F, 0.1657F, 0.2527F, -0.0692F));
		PartDefinition cube_r25 = Body.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(57, 58).addBox(4.0F, 9.0F, -1.0F, 1.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.1117F, -5.1083F, -1.6822F, -0.0088F, 0.2527F, -0.0692F));
		PartDefinition cube_r26 = Body.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 59).addBox(4.0F, 1.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0712F, -0.3773F, 0.5273F, 0.1657F, -0.2527F, 0.0692F));
		PartDefinition cube_r27 = Body.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(55, 89).addBox(4.0F, 1.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0176F, -0.3699F, 0.7321F, -0.2706F, -0.2527F, 0.0692F));
		PartDefinition cube_r28 = Body.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(88, 56).addBox(-5.0F, 3.0F, 4.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.3557F, -5.901F, -3.5507F, 0.0351F, 0.0062F, -0.3651F));
		PartDefinition cube_r29 = Body.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(86, 31).addBox(-5.0F, 3.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5194F, -6.0285F, -0.8919F, 0.1245F, -0.3426F, -0.3779F));
		PartDefinition cube_r30 = Body.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(62, 43).addBox(-5.0F, 3.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7691F, -3.1726F, -3.9012F, 1.1281F, -0.3426F, -0.3779F));
		PartDefinition cube_r31 = Body.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(44, 70).addBox(4.0F, 3.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.7691F, -3.1726F, -3.9012F, 1.1281F, 0.3426F, 0.3779F));
		PartDefinition cube_r32 = Body.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(75, 85).addBox(-5.0F, 3.0F, 4.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.8015F, -7.1234F, -0.3272F, -0.314F, 0.0062F, -0.3651F));
		PartDefinition cube_r33 = Body.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(49, 87).addBox(-9.0F, 3.0F, 4.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.8941F, -7.6916F, -1.5019F, -1.8062F, 1.2547F, -1.8143F));
		PartDefinition cube_r34 = Body.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(30, 88).addBox(8.0F, 3.0F, 4.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.8941F, -7.6916F, -1.5019F, -1.8062F, -1.2547F, 1.8143F));
		PartDefinition cube_r35 = Body.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(69, 83).addBox(8.0F, 3.0F, 4.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.5584F, -5.5114F, -1.9838F, -1.4571F, -1.2547F, 1.8143F));
		PartDefinition cube_r36 = Body.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(82, 83).addBox(-9.0F, 3.0F, 4.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.5584F, -5.5114F, -1.9838F, -1.4571F, 1.2547F, -1.8143F));
		PartDefinition cube_r37 = Body.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(0, 66).addBox(-5.0F, 3.0F, 4.0F, 1.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.662F, -4.9762F, -0.5566F, -0.6189F, 1.0094F, -0.9057F));
		PartDefinition cube_r38 = Body.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(67, 3).addBox(4.0F, 3.0F, 4.0F, 1.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.662F, -4.9762F, -0.5566F, -0.6189F, -1.0094F, 0.9057F));
		PartDefinition cube_r39 = Body.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(24, 86).addBox(4.0F, 3.0F, 4.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.8015F, -7.1234F, -0.3272F, -0.314F, -0.0062F, 0.3651F));
		PartDefinition cube_r40 = Body.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(88, 71).addBox(4.0F, 3.0F, 4.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.3557F, -5.901F, -3.5507F, 0.0351F, -0.0062F, 0.3651F));
		PartDefinition cube_r41 = Body.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(38, 86).addBox(4.0F, 3.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5194F, -6.0285F, -0.8919F, 0.1245F, 0.3426F, 0.3779F));
		PartDefinition cube_r42 = Body.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(57, 72).addBox(-5.0F, 1.0F, -1.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5763F, -2.6568F, -4.5514F, 0.2575F, 0.0538F, -0.261F));
		PartDefinition cube_r43 = Body.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(74, 63).addBox(4.0F, 1.0F, -1.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5763F, -2.6568F, -4.5514F, 0.2575F, -0.0538F, 0.261F));
		PartDefinition cube_r44 = Body.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(33, 76).addBox(4.0F, 1.0F, -1.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -0.7F, -0.5F, -0.2706F, 0.2527F, -0.0692F));
		PartDefinition cube_r45 = Body.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(56, 54).addBox(-4.0F, -9.2F, 8.9F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 23.3F, -0.5F, 0.5236F, 0.0F, 0.0F));
		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(80, 52).addBox(-4.7F, -21.0F, -9.375F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(21.3206F, 6.6701F, 5.6888F, -0.1052F, -0.2021F, -1.2616F));
		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2", CubeListBuilder.create().texOffs(80, 61).addBox(1.7F, -21.0F, -9.375F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-21.3206F, 6.6701F, 5.6888F, -0.1052F, 0.2021F, 1.2616F));
		PartDefinition Body_r3 = Body.addOrReplaceChild("Body_r3", CubeListBuilder.create().texOffs(48, 26).addBox(-4.0F, -14.9F, -10.7F, 8.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 19.7233F, 9.5111F, 0.0873F, 0.0F, 0.0F));
		PartDefinition Body_r4 = Body.addOrReplaceChild("Body_r4", CubeListBuilder.create().texOffs(56, 34).addBox(-4.0F, -17.9F, -8.7F, 8.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 19.5233F, 9.5111F, 0.2182F, 0.0F, 0.0F));
		PartDefinition Body_r5 = Body.addOrReplaceChild("Body_r5", CubeListBuilder.create().texOffs(24, 17).addBox(-4.0F, -17.9F, -8.7F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 22.9383F, -2.9435F, -0.48F, 0.0F, 0.0F));
		PartDefinition Body_r6 = Body.addOrReplaceChild("Body_r6", CubeListBuilder.create().texOffs(56, 40).addBox(-4.0F, -17.9F, -8.7F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
		PartDefinition Body_r7 = Body.addOrReplaceChild("Body_r7", CubeListBuilder.create().texOffs(59, 0).addBox(-4.0F, -23.3F, 0.8F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.1309F, 0.0F, 0.0F));
		PartDefinition Body_r8 = Body.addOrReplaceChild("Body_r8", CubeListBuilder.create().texOffs(59, 3).addBox(-0.5F, -12.4F, 6.65F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.2F, 22.2F, 0.5F, 0.7453F, 0.0F, 0.0F));
		PartDefinition Body_r9 = Body.addOrReplaceChild("Body_r9", CubeListBuilder.create().texOffs(34, 69).addBox(-0.5F, -12.4F, 6.65F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.2F, 24.0F, 0.5F, 0.7453F, 0.0F, 0.0F));
		PartDefinition Body_r10 = Body.addOrReplaceChild("Body_r10", CubeListBuilder.create().texOffs(27, 4).addBox(2.5F, -18.25F, -3.15F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.3353F, 23.3F, 0.9829F, 0.0F, 0.2182F, 0.0F));
		PartDefinition Body_r11 = Body.addOrReplaceChild("Body_r11", CubeListBuilder.create().texOffs(36, 9).addBox(-3.5F, -18.25F, -3.15F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.1353F, 23.3F, 0.9829F, 0.0F, -0.2182F, 0.0F));
		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 43).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition LeftArm_r1 = RightArm.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(16, 53).addBox(-8.0F, -24.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.7782F, 26.3455F, 0.5F, 0.0F, 0.0F, -0.0436F));
		PartDefinition LeftArm_r2 = RightArm.addOrReplaceChild("LeftArm_r2", CubeListBuilder.create().texOffs(48, 47).addBox(-8.0F, -24.1F, -3.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9865F, 26.3106F, -1.6841F, -0.0873F, 0.0F, 0.2618F));
		PartDefinition LeftArm_r3 = RightArm.addOrReplaceChild("LeftArm_r3", CubeListBuilder.create().texOffs(42, 17).addBox(-8.0F, -24.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.75F, 22.1875F, 0.4F, 0.0F, 0.0F, 0.1309F));
		PartDefinition LeftArm_r4 = RightArm.addOrReplaceChild("LeftArm_r4", CubeListBuilder.create().texOffs(82, 5).addBox(-4.0F, -24.0F, -1.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.25F, 21.55F, -0.6F, 0.0F, 0.0F, 0.3054F));
		PartDefinition LeftArm_r5 = RightArm.addOrReplaceChild("LeftArm_r5", CubeListBuilder.create().texOffs(47, 0).addBox(-8.0F, -23.0F, -4.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(18.151F, -2.2276F, 0.9F, 0.0F, 0.0F, -1.789F));
		PartDefinition LeftArm_r6 = RightArm.addOrReplaceChild("LeftArm_r6", CubeListBuilder.create().texOffs(27, 0).addBox(-8.0F, -23.0F, -4.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.6542F, 31.182F, 1.0F, 0.0F, 0.0F, 0.0873F));
		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(32, 28).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition LeftArm_r7 = LeftArm.addOrReplaceChild("LeftArm_r7", CubeListBuilder.create().texOffs(30, 47).addBox(5.0F, -23.0F, -4.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-18.151F, -2.2276F, 0.9F, 0.0F, 0.0F, 1.789F));
		PartDefinition LeftArm_r8 = LeftArm.addOrReplaceChild("LeftArm_r8", CubeListBuilder.create().texOffs(42, 38).addBox(4.0F, -23.0F, -4.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.6542F, 31.182F, 1.0F, 0.0F, 0.0F, -0.0873F));
		PartDefinition LeftArm_r9 = LeftArm.addOrReplaceChild("LeftArm_r9", CubeListBuilder.create().texOffs(43, 54).addBox(4.0F, -24.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.7782F, 26.3455F, 0.5F, 0.0F, 0.0F, 0.0436F));
		PartDefinition LeftArm_r10 = LeftArm.addOrReplaceChild("LeftArm_r10", CubeListBuilder.create().texOffs(82, 24).addBox(3.0F, -24.0F, -1.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.25F, 21.55F, -0.6F, 0.0F, 0.0F, -0.3054F));
		PartDefinition LeftArm_r11 = LeftArm.addOrReplaceChild("LeftArm_r11", CubeListBuilder.create().texOffs(48, 47).mirror().addBox(3.0F, -24.1F, -3.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(1.9865F, 26.3106F, -1.6841F, -0.0873F, 0.0F, -0.2618F));
		PartDefinition LeftArm_r12 = LeftArm.addOrReplaceChild("LeftArm_r12", CubeListBuilder.create().texOffs(16, 44).addBox(3.0F, -24.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.75F, 22.1875F, 0.4F, 0.0F, 0.0F, -0.1309F));
		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg",
				CubeListBuilder.create().texOffs(16, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(12, 44).addBox(-1.55F, 2.1F, 1.6F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(49, 65)
						.addBox(1.15F, 2.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(8, 68).addBox(-2.15F, 2.0F, -2.475F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition LeftLeg_r1 = RightLeg.addOrReplaceChild("LeftLeg_r1", CubeListBuilder.create().texOffs(82, 37).addBox(-4.9F, -2.0F, -3.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.35F, 4.5F, -0.2F, -0.2182F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r2 = RightLeg.addOrReplaceChild("LeftLeg_r2", CubeListBuilder.create().texOffs(81, 0).addBox(-4.9F, -2.0F, -3.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.35F, 4.3994F, -0.0272F, 0.1745F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r3 = RightLeg.addOrReplaceChild("LeftLeg_r3", CubeListBuilder.create().texOffs(39, 76).addBox(-4.9F, -2.0F, -3.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.35F, 5.7994F, -0.0272F, -0.0436F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r4 = RightLeg.addOrReplaceChild("LeftLeg_r4", CubeListBuilder.create().texOffs(25, 78).addBox(-3.9F, -7.0F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.9F, 12.0F, 0.225F, 0.0873F, 0.0F, -0.0436F));
		PartDefinition LeftLeg_r5 = RightLeg.addOrReplaceChild("LeftLeg_r5", CubeListBuilder.create().texOffs(64, 67).addBox(-3.9F, -7.0F, -3.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.9F, 12.0F, 2.025F, -0.0436F, 0.0F, -0.0436F));
		PartDefinition LeftLeg_r6 = RightLeg.addOrReplaceChild("LeftLeg_r6", CubeListBuilder.create().texOffs(12, 27).addBox(-3.9F, -3.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.4973F, 6.5338F, -0.2543F, 0.0865F, -0.0114F, 0.0868F));
		PartDefinition LeftLeg_r7 = RightLeg.addOrReplaceChild("LeftLeg_r7", CubeListBuilder.create().texOffs(42, 47).addBox(-3.9F, -3.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.4966F, 6.5181F, 2.2649F, -0.0433F, 0.0057F, 0.0871F));
		PartDefinition LeftLeg_r8 = RightLeg.addOrReplaceChild("LeftLeg_r8", CubeListBuilder.create().texOffs(0, 0).addBox(1.9F, -7.0F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, 12.0F, 0.225F, 0.0873F, 0.0F, 0.0436F));
		PartDefinition LeftLeg_r9 = RightLeg.addOrReplaceChild("LeftLeg_r9", CubeListBuilder.create().texOffs(20, 11).addBox(1.9F, -3.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.4973F, 6.5338F, -0.2543F, 0.0865F, 0.0114F, -0.0868F));
		PartDefinition LeftLeg_r10 = RightLeg.addOrReplaceChild("LeftLeg_r10", CubeListBuilder.create().texOffs(67, 58).addBox(1.9F, -7.0F, -3.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, 12.0F, 2.025F, -0.0436F, 0.0F, 0.0436F));
		PartDefinition LeftLeg_r11 = RightLeg.addOrReplaceChild("LeftLeg_r11", CubeListBuilder.create().texOffs(47, 10).addBox(1.9F, -3.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.4966F, 6.5181F, 2.2649F, -0.0433F, -0.0057F, -0.0871F));
		PartDefinition LeftLeg_r12 = RightLeg.addOrReplaceChild("LeftLeg_r12", CubeListBuilder.create().texOffs(77, 46).addBox(-3.9F, -12.0F, -1.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.9F, 11.6F, -0.3F, -0.0873F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r13 = RightLeg.addOrReplaceChild("LeftLeg_r13", CubeListBuilder.create().texOffs(75, 21).addBox(-3.9F, -12.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.9F, 11.5F, 0.3F, 0.0873F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r14 = RightLeg.addOrReplaceChild("LeftLeg_r14", CubeListBuilder.create().texOffs(71, 15).addBox(-4.9F, -12.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, 11.7F, -0.3F, -0.0873F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r15 = RightLeg.addOrReplaceChild("LeftLeg_r15", CubeListBuilder.create().texOffs(41, 0).addBox(-4.9F, -12.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, 11.6F, 0.3F, 0.0873F, 0.0F, 0.0F));
		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg",
				CubeListBuilder.create().texOffs(0, 27).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(70, 47).addBox(1.15F, 2.0F, -2.475F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(37, 69)
						.addBox(-2.15F, 2.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(31, 44).addBox(-1.45F, 2.1F, 1.6F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition LeftLeg_r16 = LeftLeg.addOrReplaceChild("LeftLeg_r16", CubeListBuilder.create().texOffs(31, 84).addBox(1.9F, -2.0F, -3.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.35F, 5.7994F, -0.0272F, -0.0436F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r17 = LeftLeg.addOrReplaceChild("LeftLeg_r17", CubeListBuilder.create().texOffs(84, 44).addBox(1.9F, -2.0F, -3.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.35F, 4.3994F, -0.0272F, 0.1745F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r18 = LeftLeg.addOrReplaceChild("LeftLeg_r18", CubeListBuilder.create().texOffs(84, 67).addBox(1.9F, -2.0F, -3.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.35F, 4.5F, -0.2F, -0.2182F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r19 = LeftLeg.addOrReplaceChild("LeftLeg_r19", CubeListBuilder.create().texOffs(43, 81).addBox(-3.9F, -7.0F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.9F, 12.0F, 0.225F, 0.0873F, 0.0F, -0.0436F));
		PartDefinition LeftLeg_r20 = LeftLeg.addOrReplaceChild("LeftLeg_r20", CubeListBuilder.create().texOffs(87, 4).addBox(-3.9F, -3.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.4973F, 6.5338F, -0.2543F, 0.0865F, -0.0114F, 0.0868F));
		PartDefinition LeftLeg_r21 = LeftLeg.addOrReplaceChild("LeftLeg_r21", CubeListBuilder.create().texOffs(68, 24).addBox(-3.9F, -7.0F, -3.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.9F, 12.0F, 2.025F, -0.0436F, 0.0F, -0.0436F));
		PartDefinition LeftLeg_r22 = LeftLeg.addOrReplaceChild("LeftLeg_r22", CubeListBuilder.create().texOffs(57, 17).addBox(-3.9F, -3.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.4966F, 6.5181F, 2.2649F, -0.0433F, 0.0057F, 0.0871F));
		PartDefinition LeftLeg_r23 = LeftLeg.addOrReplaceChild("LeftLeg_r23", CubeListBuilder.create().texOffs(81, 76).addBox(1.9F, -3.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.4966F, 6.5181F, 2.2649F, -0.0433F, -0.0057F, -0.0871F));
		PartDefinition LeftLeg_r24 = LeftLeg.addOrReplaceChild("LeftLeg_r24", CubeListBuilder.create().texOffs(27, 69).addBox(1.9F, -7.0F, -3.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, 12.0F, 2.025F, -0.0436F, 0.0F, 0.0436F));
		PartDefinition LeftLeg_r25 = LeftLeg.addOrReplaceChild("LeftLeg_r25", CubeListBuilder.create().texOffs(87, 48).addBox(1.9F, -3.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.4973F, 6.5338F, -0.2543F, 0.0865F, 0.0114F, -0.0868F));
		PartDefinition LeftLeg_r26 = LeftLeg.addOrReplaceChild("LeftLeg_r26", CubeListBuilder.create().texOffs(55, 81).addBox(1.9F, -7.0F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, 12.0F, 0.225F, 0.0873F, 0.0F, 0.0436F));
		PartDefinition LeftLeg_r27 = LeftLeg.addOrReplaceChild("LeftLeg_r27", CubeListBuilder.create().texOffs(74, 79).addBox(1.9F, -12.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.9F, 11.5F, 0.3F, 0.0873F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r28 = LeftLeg.addOrReplaceChild("LeftLeg_r28", CubeListBuilder.create().texOffs(64, 77).addBox(1.9F, -12.0F, -1.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.9F, 11.6F, -0.3F, -0.0873F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r29 = LeftLeg.addOrReplaceChild("LeftLeg_r29", CubeListBuilder.create().texOffs(73, 40).addBox(1.9F, -12.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, 11.7F, -0.3F, -0.0873F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r30 = LeftLeg.addOrReplaceChild("LeftLeg_r30", CubeListBuilder.create().texOffs(71, 73).addBox(1.9F, -12.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, 11.6F, 0.3F, 0.0873F, 0.0F, 0.0F));
		PartDefinition LeftShoes = partdefinition.addOrReplaceChild("LeftShoes",
				CubeListBuilder.create().texOffs(40, 62).addBox(0.25F, 10.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(62, 17).addBox(-2.25F, 10.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition LeftLeg_r31 = LeftShoes.addOrReplaceChild("LeftLeg_r31", CubeListBuilder.create().texOffs(90, 27).addBox(-4.9F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.4085F, 11.9903F, -2.2423F, -0.2399F, 0.4253F, -0.1006F));
		PartDefinition LeftLeg_r32 = LeftShoes.addOrReplaceChild("LeftLeg_r32", CubeListBuilder.create().texOffs(90, 39).addBox(2.9F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.3838F, 11.9903F, -2.2423F, -0.2399F, -0.4253F, 0.1006F));
		PartDefinition LeftLeg_r33 = LeftShoes.addOrReplaceChild("LeftLeg_r33", CubeListBuilder.create().texOffs(36, 57).addBox(3.9F, -2.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.3876F, 12.3164F, -0.7713F, -0.2182F, 0.0F, 0.0F));
		PartDefinition RightShoes = partdefinition.addOrReplaceChild("RightShoes",
				CubeListBuilder.create().texOffs(0, 59).addBox(0.25F, 10.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(9, 61).addBox(-2.25F, 10.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition LeftLeg_r34 = RightShoes.addOrReplaceChild("LeftLeg_r34", CubeListBuilder.create().texOffs(27, 0).addBox(-4.9F, -2.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.3876F, 12.3164F, -0.7713F, -0.2182F, 0.0F, 0.0F));
		PartDefinition LeftLeg_r35 = RightShoes.addOrReplaceChild("LeftLeg_r35", CubeListBuilder.create().texOffs(48, 34).addBox(-4.9F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.3838F, 11.9903F, -2.2423F, -0.2399F, 0.4253F, -0.1006F));
		PartDefinition LeftLeg_r36 = RightShoes.addOrReplaceChild("LeftLeg_r36", CubeListBuilder.create().texOffs(28, 28).addBox(2.9F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.4085F, 11.9903F, -2.2423F, -0.2399F, -0.4253F, 0.1006F));
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
