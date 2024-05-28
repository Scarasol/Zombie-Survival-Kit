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
public class TacticalSuitModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("zombiekit", "modeltactical_suit"), "main");
	public final ModelPart Head;
	public final ModelPart Body;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	public final ModelPart LeftShoes;
	public final ModelPart RightShoes;

	public TacticalSuitModel(ModelPart root) {
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
		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -8.4847F, -4.0463F, 9.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = Head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(54, 41).addBox(-4.0F, -1.539F, -0.5598F, 9.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -9.0689F, -2.4007F, -1.1781F, 0.0F, 0.0F));
		PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(50, 24).addBox(-4.0F, -2.539F, 0.4402F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -3.6826F, -2.6725F, 0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r3 = Head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(53, 7).addBox(-4.0F, -2.539F, 1.0402F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -3.2863F, -4.0918F, 0.0436F, 0.0F, 0.0F));
		PartDefinition cube_r4 = Head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 75).addBox(-5.0F, -5.539F, 0.4402F, 0.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.039F, -5.0471F, -2.7612F, 0.0F, -0.0436F, -1.5708F));
		PartDefinition cube_r5 = Head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 9).addBox(-5.0F, -5.539F, 0.4402F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.75F, 0.2692F, -2.9933F, -0.0436F, -0.0019F, -0.0436F));
		PartDefinition cube_r6 = Head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 38).addBox(-5.0F, -5.539F, 1.4402F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7625F, -3.8308F, -2.4933F, -1.1344F, -0.0019F, -0.0436F));
		PartDefinition cube_r7 = Head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(2, 38).addBox(5.0F, -5.539F, 1.4402F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.7625F, -3.8308F, -2.4933F, -1.1344F, 0.0019F, 0.0436F));
		PartDefinition cube_r8 = Head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(35, 5).addBox(5.0F, -5.539F, 0.4402F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.75F, 0.2692F, -2.9933F, -0.0436F, 0.0019F, 0.0436F));
		PartDefinition cube_r9 = Head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(50, 21).addBox(-4.0F, -5.539F, 0.4402F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -3.9952F, -1.6931F, -1.0908F, 0.0F, 0.0F));
		PartDefinition cube_r10 = Head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(26, 0).addBox(-4.0F, -5.539F, 0.4402F, 10.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -1.5808F, -2.6933F, -0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r11 = Head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(53, 10).addBox(-4.0F, -0.539F, -0.5598F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -3.964F, 3.0831F, -2.2689F, 0.0F, 0.0F));
		PartDefinition cube_r12 = Head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(56, 27).addBox(-4.0F, -2.539F, -0.5598F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -3.964F, 3.0831F, -2.9671F, 0.0F, 0.0F));
		PartDefinition cube_r13 = Head.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(20, 54).addBox(-4.0F, -2.539F, 0.4402F, 9.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -2.3247F, 5.7787F, 2.4871F, 0.0F, 0.0F));
		PartDefinition cube_r14 = Head.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(51, 57).addBox(-4.0F, -1.539F, 0.4402F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -3.5038F, 5.7427F, 3.0543F, 0.0F, 0.0F));
		PartDefinition cube_r15 = Head.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(51, 0).addBox(-4.0F, -2.539F, 0.4402F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -3.591F, 4.7465F, 3.0543F, 0.0F, 0.0F));
		PartDefinition cube_r16 = Head.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(18, 48).addBox(-4.0F, -2.539F, -0.5598F, 9.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -7.3553F, 4.3355F, -2.9234F, 0.0F, 0.0F));
		PartDefinition cube_r17 = Head.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(56, 31).addBox(-4.0F, -0.539F, -0.5598F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -8.6552F, 3.8292F, -2.0508F, 0.0F, 0.0F));
		PartDefinition cube_r18 = Head.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(75, 8).addBox(-2.0F, -4.639F, 7.5402F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -9.981F, 3.5904F, -1.8762F, 0.0F, 0.0F));
		PartDefinition cube_r19 = Head.addOrReplaceChild("cube_r19",
				CubeListBuilder.create().texOffs(63, 61).addBox(-4.3F, 1.161F, 7.0402F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(65, 50).addBox(-0.7F, 1.161F, 7.0402F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -9.981F, 3.5904F, -1.309F, 0.0F, 0.0F));
		PartDefinition cube_r20 = Head.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 29).addBox(-4.0F, -2.639F, 7.0402F, 9.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -9.49F, 0.2136F, -1.6581F, 0.0F, 0.0F));
		PartDefinition cube_r21 = Head.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(48, 35).addBox(-4.0F, -2.539F, -0.5598F, 9.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -9.49F, 0.2136F, -1.6581F, 0.0F, 0.0F));
		PartDefinition cube_r22 = Head.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(56, 5).addBox(-4.0F, 0.461F, 0.4402F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -7.1978F, -4.4354F, -0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r23 = Head.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 55).addBox(-4.0F, -0.539F, -0.5598F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -7.0987F, -4.7962F, 0.0436F, 0.0F, 0.0F));
		PartDefinition cube_r24 = Head.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(71, 56).addBox(0.0F, -0.539F, -0.5598F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.4968F, -6.1532F, -4.7799F, 0.0421F, -0.0113F, 0.2616F));
		PartDefinition cube_r25 = Head.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(73, 0).addBox(-4.0F, -0.539F, -0.5598F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.4968F, -6.1532F, -4.7799F, 0.0421F, 0.0113F, -0.2616F));
		PartDefinition cube_r26 = Head.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(26, 66).addBox(-2.0F, -1.539F, -0.5598F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -8.7102F, -3.3833F, -1.2217F, 0.0F, 0.0F));
		PartDefinition cube_r27 = Head.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(84, 0).addBox(-1.0F, 0.761F, -0.5598F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.7854F, -7.8731F, -4.4563F, -0.4996F, 0.27F, -0.1446F));
		PartDefinition cube_r28 = Head.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(84, 16).addBox(-1.0F, 0.761F, -0.5598F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.7854F, -7.8731F, -4.4563F, -0.4996F, -0.27F, 0.1446F));
		PartDefinition cube_r29 = Head.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(21, 29).addBox(-1.0F, -0.539F, -0.3598F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -6.6788F, -5.1478F, 0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r30 = Head.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(84, 33).addBox(-1.0F, -0.539F, -0.5598F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -8.0F, -4.7F, -0.48F, 0.0F, 0.0F));
		PartDefinition cube_r31 = Head.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(57, 13).addBox(-4.0F, -0.539F, -0.5598F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -8.25F, -3.95F, -0.7854F, 0.0F, 0.0F));
		PartDefinition Body = partdefinition.addOrReplaceChild(
				"Body", CubeListBuilder.create().texOffs(0, 45).addBox(-3.5F, 3.9639F, -4.1281F, 7.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 37).addBox(-3.4968F, 2.9639F, 1.6022F, 7.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(42, 21).addBox(-4.5032F, 3.9639F, -2.3978F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(40, 37).addBox(3.5032F, 3.9639F, -2.3978F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r32 = Body.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 78).addBox(-5.0F, -5.0F, -3.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.4126F, 8.9639F, 1.7022F, 0.0F, -1.0454F, 0.0F));
		PartDefinition cube_r33 = Body.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(60, 80).addBox(-1.5F, -0.5F, -2.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.5032F, 8.9639F, 1.6022F, 0.0F, 0.0F, -0.0436F));
		PartDefinition cube_r34 = Body.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(68, 80).addBox(-0.5F, -0.5F, -2.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.5032F, 8.9639F, 1.6022F, 0.0F, 0.0F, 0.0436F));
		PartDefinition cube_r35 = Body.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(44, 83).addBox(-2.0F, -3.0F, 1.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.9303F, 8.9639F, 0.2241F, 0.0F, 0.1745F, 0.0F));
		PartDefinition cube_r36 = Body.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(50, 83).addBox(1.0F, -3.0F, 1.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.967F, 8.9639F, 0.2241F, 0.0F, -0.1745F, 0.0F));
		PartDefinition cube_r37 = Body.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(60, 46).addBox(-5.0F, 1.0F, 1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5032F, 0.3811F, 0.2362F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r38 = Body.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(6, 78).addBox(3.0F, -5.0F, -3.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.4126F, 8.9639F, 1.7022F, 0.0F, 1.0454F, 0.0F));
		PartDefinition cube_r39 = Body.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(12, 81).addBox(-5.0F, -1.0F, -3.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.75F, 8.2139F, -2.6281F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r40 = Body.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(0, 0).addBox(-5.75F, -0.65F, -2.35F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.75F, 8.2139F, -6.6281F, 0.1151F, 1.0057F, 0.0441F));
		PartDefinition cube_r41 = Body.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(24, 71).addBox(-1.25F, -0.4F, -11.6F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.75F, 7.9639F, -6.3781F, 3.1025F, 0.0857F, -3.1093F));
		PartDefinition cube_r42 = Body.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(74, 17).addBox(-2.75F, -0.4F, -11.6F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.75F, 7.9639F, -6.3781F, 3.1025F, -0.0857F, 3.1093F));
		PartDefinition cube_r43 = Body.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(74, 74).addBox(3.25F, -0.65F, -2.85F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.75F, 8.2139F, -6.6281F, 0.1151F, -1.0057F, -0.0441F));
		PartDefinition cube_r44 = Body.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(76, 81).addBox(3.0F, -1.0F, -3.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.25F, 8.2139F, -2.8781F, 0.132F, -0.1298F, -0.0172F));
		PartDefinition cube_r45 = Body.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(82, 51).addBox(-5.0F, -1.0F, -3.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.75F, 8.2139F, -2.8781F, 0.132F, 0.1298F, 0.0172F));
		PartDefinition cube_r46 = Body.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(40, 37).addBox(-1.5F, -6.1F, -4.6F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 8.9639F, -1.1281F, -0.0436F, 0.0F, 0.0F));
		PartDefinition cube_r47 = Body.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(60, 74).addBox(-4.0F, -5.85F, -4.5F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 8.9639F, -1.1281F, -0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r48 = Body.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(70, 35).addBox(-5.0F, -2.0F, -3.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, 3.5F, -1.0F, -0.1745F, 0.0F, 0.0F));
		PartDefinition cube_r49 = Body.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(49, 46).addBox(-5.0F, -3.0F, -3.0F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.25F, 0.5F, 0.6F, 0.0F, 0.0F, -0.4363F));
		PartDefinition cube_r50 = Body.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(35, 52).addBox(2.0F, -3.0F, -3.0F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.25F, 0.5F, 0.6F, 0.0F, 0.0F, 0.4363F));
		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm",
				CubeListBuilder.create().texOffs(12, 66).addBox(-3.5F, -2.2F, -2.25F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 39).addBox(-4.0F, 2.55F, -2.5F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(46, 77)
						.addBox(-2.4F, -2.4F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(36, 72).addBox(-2.4F, 3.3F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 79)
						.addBox(-3.6F, 3.3F, -0.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 21).addBox(-3.6F, -2.3F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(63, 67)
						.addBox(-3.5F, -2.2F, 0.25F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(20, 57).addBox(-3.5F, 3.1F, 0.25F, 5.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 59)
						.addBox(-3.5F, 3.1F, -2.25F, 5.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition cube_r51 = RightArm.addOrReplaceChild("cube_r51",
				CubeListBuilder.create().texOffs(79, 64).addBox(-1.2F, 2.55F, 0.65F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(79, 13).addBox(-3.8F, 2.55F, 0.65F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.6107F, -0.6898F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r52 = RightArm.addOrReplaceChild("cube_r52",
				CubeListBuilder.create().texOffs(76, 60).addBox(-3.8F, -41.15F, -0.6625F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(15, 11).addBox(-1.2F, -41.15F, -0.6625F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 44.6107F, -0.6898F, -0.0436F, 0.0F, 0.0F));
		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm",
				CubeListBuilder.create().texOffs(34, 63).addBox(-1.5F, 3.1F, -2.25F, 5.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(49, 61).addBox(-1.5F, 3.1F, 0.25F, 5.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 71)
						.addBox(-1.5F, -2.2F, 0.25F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(36, 80).addBox(0.6F, -2.3F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 79)
						.addBox(1.6F, 3.3F, -0.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(14, 73).addBox(-1.6F, 3.3F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(77, 67)
						.addBox(-1.6F, -2.4F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 15).addBox(-2.0F, 2.55F, -2.5F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(46, 70)
						.addBox(-1.5F, -2.2F, -2.25F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition cube_r53 = LeftArm.addOrReplaceChild("cube_r53",
				CubeListBuilder.create().texOffs(78, 47).addBox(-1.8F, -41.15F, -0.6625F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(78, 25).addBox(0.8F, -41.15F, -0.6625F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 44.6107F, -0.6898F, -0.0436F, 0.0F, 0.0F));
		PartDefinition cube_r54 = LeftArm.addOrReplaceChild("cube_r54",
				CubeListBuilder.create().texOffs(82, 73).addBox(0.8F, 2.55F, 0.65F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(82, 79).addBox(-1.8F, 2.55F, 0.65F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.6107F, -0.6898F, 0.1309F, 0.0F, 0.0F));
		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg",
				CubeListBuilder.create().texOffs(24, 29).addBox(-3.2F, 4.1F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 11).addBox(-2.7F, -0.9F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition cube_r55 = RightLeg.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(76, 4).addBox(1.2F, -5.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.3F, 8.1185F, 4.74F, -0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r56 = RightLeg.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(62, 17).addBox(1.1F, 3.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.3F, 2.0815F, 4.74F, 0.1309F, 0.0F, 0.0F));
		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg",
				CubeListBuilder.create().texOffs(20, 11).addBox(-2.3F, -0.9F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(35, 7).addBox(-2.8F, 4.1F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition cube_r57 = LeftLeg.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(76, 29).addBox(-5.1F, 3.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.3F, 2.0815F, 4.74F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r58 = LeftLeg.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(76, 40).addBox(-5.2F, -5.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.3F, 8.1185F, 4.74F, -0.1309F, 0.0F, 0.0F));
		PartDefinition LeftShoes = partdefinition.addOrReplaceChild("LeftShoes", CubeListBuilder.create().texOffs(79, 82).addBox(-2.3F, 9.1F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition cube_r59 = LeftShoes.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(76, 44).addBox(-6.0F, 1.0F, 6.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.7F, 8.5013F, -9.2119F, -0.0873F, 0.0F, 0.0F));
		PartDefinition RightShoes = partdefinition.addOrReplaceChild("RightShoes", CubeListBuilder.create().texOffs(85, 52).addBox(-2.7F, 9.1F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition cube_r60 = RightShoes.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(0, 68).addBox(1.0F, 1.0F, 6.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.7F, 8.5013F, -9.2119F, -0.0873F, 0.0F, 0.0F));
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
