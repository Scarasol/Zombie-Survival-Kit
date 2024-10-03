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
public class ExoSuitModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("zombiekit", "modelexo_suit"), "main");
	public final ModelPart Head;
	public final ModelPart Body;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	public final ModelPart LeftShoes;
	public final ModelPart RightShoes;

	public ExoSuitModel(ModelPart root) {
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
		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(55, 99).addBox(-1.0F, -7.0F, -5.25F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = Head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -32.5F, -22.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.0312F, 13.5968F, -33.2284F, -1.5708F, 0.0F, 1.0472F));
		PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(28, 43).addBox(3.0F, -32.5F, -22.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.0312F, 13.5968F, -33.2284F, -1.5708F, 0.0F, -1.0472F));
		PartDefinition cube_r3 = Head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(74, 89).addBox(3.0F, -31.5F, -21.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(19.0F, 18.2488F, 20.5216F, 1.5708F, 0.7418F, 1.5708F));
		PartDefinition cube_r4 = Head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(24, 86).addBox(3.0F, -31.5F, -20.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(19.0F, 29.6632F, -6.8384F, -1.5708F, 1.3963F, -1.5708F));
		PartDefinition cube_r5 = Head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(31, 92).addBox(3.0F, -32.5F, -22.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.5F, 18.9187F, -33.0284F, -1.5708F, 0.0F, 0.0436F));
		PartDefinition cube_r6 = Head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(9, 95).addBox(3.0F, -31.5F, -22.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.7934F, 27.8291F, -19.837F, -1.2217F, 0.0F, 0.0436F));
		PartDefinition cube_r7 = Head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(100, 61).addBox(3.0F, -31.5F, -22.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.9855F, 32.2293F, -8.5725F, -0.9163F, 0.0F, 0.0436F));
		PartDefinition cube_r8 = Head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(17, 95).addBox(-4.0F, -31.5F, -22.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.7934F, 27.8291F, -19.837F, -1.2217F, 0.0F, -0.0436F));
		PartDefinition cube_r9 = Head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(39, 95).addBox(-4.0F, -32.5F, -22.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.5F, 18.9187F, -33.0284F, -1.5708F, 0.0F, -0.0436F));
		PartDefinition cube_r10 = Head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(68, 100).addBox(-4.0F, -31.5F, -22.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.9855F, 32.2293F, -8.5725F, -0.9163F, 0.0F, -0.0436F));
		PartDefinition cube_r11 = Head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(65, 23).addBox(-3.0F, -32.75F, -25.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -42.5104F, -11.3456F, 2.8798F, 0.0F, 0.0F));
		PartDefinition cube_r12 = Head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(30, 49).addBox(-4.0F, -32.75F, -25.5F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -11.2492F, -35.0377F, -2.3126F, 0.0F, 0.0F));
		PartDefinition cube_r13 = Head.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(50, 50).addBox(-1.0F, -34.75F, -25.5F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(20.2951F, -15.5963F, -30.2486F, -1.5367F, -0.1264F, -1.8348F));
		PartDefinition cube_r14 = Head.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(53, 36).addBox(-4.0F, -34.75F, -25.5F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-20.2951F, -15.5963F, -30.2486F, -1.5367F, 0.1264F, 1.8348F));
		PartDefinition cube_r15 = Head.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(54, 72).addBox(1.0F, -34.75F, -25.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(11.9528F, 5.6944F, -33.0516F, -1.6636F, -0.0924F, -0.7811F));
		PartDefinition cube_r16 = Head.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 74).addBox(-4.0F, -34.75F, -25.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-11.9528F, 5.6944F, -33.0516F, -1.6636F, 0.0924F, 0.7811F));
		PartDefinition cube_r17 = Head.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(87, 101).addBox(2.0F, -32.75F, -25.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(11.7028F, 17.5216F, -27.0686F, -1.3835F, 0.1841F, -0.7681F));
		PartDefinition cube_r18 = Head.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(101, 96).addBox(-4.0F, -32.75F, -25.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-11.7028F, 17.5216F, -27.0686F, -1.3835F, -0.1841F, 0.7681F));
		PartDefinition cube_r19 = Head.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(81, 97).addBox(-4.0F, -33.0F, -26.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.5F, 17.1989F, -34.1316F, -1.6611F, -0.2608F, 0.5033F));
		PartDefinition cube_r20 = Head.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(14, 102).addBox(-4.0F, -31.0F, -26.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-21.0563F, 8.7984F, -31.919F, -1.6636F, 0.3477F, 0.4483F));
		PartDefinition cube_r21 = Head.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(39, 102).addBox(2.0F, -31.0F, -26.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(21.0563F, 8.7984F, -31.919F, -1.6636F, -0.3477F, -0.4483F));
		PartDefinition cube_r22 = Head.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(47, 98).addBox(2.0F, -33.0F, -26.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.5F, 17.1989F, -34.1316F, -1.6611F, 0.2608F, -0.5033F));
		PartDefinition cube_r23 = Head.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(47, 77).addBox(3.0F, -34.5F, -22.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.8272F, 16.4307F, -29.0024F, -1.5227F, 0.2473F, -0.0696F));
		PartDefinition cube_r24 = Head.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(96, 18).addBox(3.0F, -30.5F, -22.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0503F, 15.1984F, -31.6784F, -1.5209F, -0.3629F, -0.0991F));
		PartDefinition cube_r25 = Head.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(63, 79).addBox(-5.0F, -34.5F, -22.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.8272F, 16.4307F, -29.0024F, -1.5227F, -0.2473F, 0.0696F));
		PartDefinition cube_r26 = Head.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 96).addBox(-4.0F, -32.5F, -22.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.4997F, 12.8484F, -35.3784F, -1.7017F, 0.0F, 0.1309F));
		PartDefinition cube_r27 = Head.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(25, 96).addBox(3.0F, -32.5F, -22.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.4997F, 12.8484F, -35.3784F, -1.7017F, 0.0F, -0.1309F));
		PartDefinition cube_r28 = Head.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(97, 27).addBox(-5.0F, -30.5F, -22.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0503F, 15.1984F, -31.6784F, -1.5209F, 0.3629F, 0.0991F));
		PartDefinition cube_r29 = Head.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(40, 65).addBox(1.0F, -32.5F, -23.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.6687F, -34.6284F, -1.6581F, 0.0F, -0.2618F));
		PartDefinition cube_r30 = Head.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(65, 40).addBox(-4.0F, -32.5F, -23.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.6687F, -34.6284F, -1.6581F, 0.0F, 0.2618F));
		PartDefinition cube_r31 = Head.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -38.0F, -26.25F, 9.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 18.7687F, -33.1284F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cube_r32 = Head.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(26, 15).addBox(-5.0F, -38.0F, -26.25F, 9.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 14.4489F, -35.1316F, -1.6581F, 0.0F, 0.0F));
		PartDefinition cube_r33 = Head.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(45, 21).addBox(-4.0F, -34.75F, -25.5F, 7.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 10.2951F, -33.6573F, -1.7017F, 0.0F, 0.0F));
		PartDefinition cube_r34 = Head.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(30, 55).addBox(-4.0F, -32.75F, -25.5F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 22.0038F, -25.8676F, -1.309F, 0.0F, 0.0F));
		PartDefinition cube_r35 = Head.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(86, 64).addBox(-3.0F, -31.75F, -9.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 16.9291F, -23.3435F, -0.9163F, 0.0F, 0.0F));
		PartDefinition cube_r36 = Head.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(46, 55).addBox(-1.0F, -31.75F, -9.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(11.5741F, 23.2073F, -0.0456F, -0.121F, -0.05F, -0.3897F));
		PartDefinition cube_r37 = Head.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(57, 29).addBox(0.0F, -31.75F, -9.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-11.5741F, 23.2073F, -0.0456F, -0.121F, 0.05F, 0.3897F));
		PartDefinition cube_r38 = Head.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(54, 58).addBox(-1.0F, -31.75F, -9.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 19.8506F, 14.0006F, 0.3491F, 0.0F, 0.0F));
		PartDefinition cube_r39 = Head.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(97, 5).addBox(-2.0F, -31.75F, -9.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 24.0F, -0.25F, -0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r40 = Head.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(81, 26).addBox(-1.0F, -31.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.3848F, 20.2058F, 0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r41 = Head.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(30, 6).addBox(-1.0F, -31.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0027F, 24.7268F, 10.7704F, 0.3927F, -0.3054F, 0.0F));
		PartDefinition cube_r42 = Head.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(32, 24).addBox(0.0F, -31.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0027F, 24.7268F, 10.7704F, 0.3927F, 0.3054F, 0.0F));
		PartDefinition cube_r43 = Head.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(73, 100).addBox(-2.0F, -31.0F, -5.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0027F, 22.7268F, 10.7704F, 0.3927F, 0.3054F, 0.0F));
		PartDefinition cube_r44 = Head.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(101, 82).addBox(-1.0F, -31.0F, -5.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0027F, 22.7268F, 10.7704F, 0.3927F, -0.3054F, 0.0F));
		PartDefinition cube_r45 = Head.addOrReplaceChild("cube_r45",
				CubeListBuilder.create().texOffs(33, 33).addBox(-1.0F, -31.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(102, 77).addBox(-1.0F, -34.0F, -5.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.4502F, 27.0F, -0.1807F, 0.0F, -0.3054F, 0.0F));
		PartDefinition cube_r46 = Head.addOrReplaceChild("cube_r46",
				CubeListBuilder.create().texOffs(0, 39).addBox(0.0F, -31.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(102, 66).addBox(-2.0F, -34.0F, -5.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.4502F, 27.0F, -0.1807F, 0.0F, 0.3054F, 0.0F));
		PartDefinition cube_r47 = Head.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(40, 13).addBox(-1.0F, -31.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(12.7206F, 21.0345F, 3.7007F, 0.1325F, -0.276F, -0.4548F));
		PartDefinition cube_r48 = Head.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(47, 7).addBox(-2.0F, -31.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-12.7206F, 21.0345F, 3.7007F, 0.1325F, 0.276F, 0.4548F));
		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(48, 17).addBox(-4.5F, 1.7228F, -2.0639F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 15).addBox(-4.5F, 0.2228F, -1.1889F, 9.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r49 = Body.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(56, 29).addBox(0.5F, 1.0F, 2.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.7821F, 9.0378F, -4.4625F, 0.0F, 0.0F, 0.6109F));
		PartDefinition cube_r50 = Body.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(59, 58).addBox(-3.5F, 1.0F, 2.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.7821F, 9.0378F, -4.4625F, 0.0F, 0.0F, -0.6109F));
		PartDefinition cube_r51 = Body.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(0, 15).addBox(6.0F, -2.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0854F, 6.2758F, -1.2092F, -0.0715F, -0.05F, 0.9617F));
		PartDefinition cube_r52 = Body.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -12.0F, 0.0F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, 16.8797F, -8.5026F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r53 = Body.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(22, 15).addBox(-7.0F, -2.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0854F, 6.2758F, -1.2092F, -0.0715F, 0.05F, -0.9617F));
		PartDefinition cube_r54 = Body.addOrReplaceChild("cube_r54",
				CubeListBuilder.create().texOffs(30, 9).addBox(-7.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(47, 9).addBox(-6.4F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.2F, 7.5382F, -0.4453F, 0.0F, 0.0F, 0.0F));
		PartDefinition cube_r55 = Body.addOrReplaceChild("cube_r55",
				CubeListBuilder.create().texOffs(33, 36).addBox(-7.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(48, 39).addBox(-6.4F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.2F, 6.0382F, -0.4453F, 0.0F, 0.0F, 0.0F));
		PartDefinition cube_r56 = Body.addOrReplaceChild("cube_r56",
				CubeListBuilder.create().texOffs(40, 24).addBox(-7.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 26).addBox(-6.4F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.2F, 9.0382F, -0.4453F, 0.0F, 0.0F, 0.0F));
		PartDefinition cube_r57 = Body.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(48, 41).addBox(5.75F, -2.5F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, 10.5882F, -0.7453F, -0.0873F, -0.829F, 0.0F));
		PartDefinition cube_r58 = Body.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(0, 49).addBox(-6.75F, -2.5F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, 10.5882F, -0.7453F, -0.0873F, 0.829F, 0.0F));
		PartDefinition cube_r59 = Body.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(27, 50).addBox(-6.75F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, 7.5882F, -0.6453F, -0.0873F, 0.829F, 0.0F));
		PartDefinition cube_r60 = Body.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(47, 50).addBox(-6.75F, -2.5F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, 7.6882F, -0.7453F, -0.0873F, 0.829F, 0.0F));
		PartDefinition cube_r61 = Body.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(0, 51).addBox(5.75F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, 7.5882F, -0.6453F, -0.0873F, -0.829F, 0.0F));
		PartDefinition cube_r62 = Body.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(55, 7).addBox(5.75F, -2.5F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, 7.6882F, -0.7453F, -0.0873F, -0.829F, 0.0F));
		PartDefinition cube_r63 = Body.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(100, 56).addBox(5.0F, 4.5F, 0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.7051F, -4.402F, -0.3205F, 0.124F, -0.1231F, 0.2105F));
		PartDefinition cube_r64 = Body.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(101, 37).addBox(-7.0F, 4.5F, 0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7051F, -4.402F, -0.3205F, 0.124F, 0.1231F, -0.2105F));
		PartDefinition cube_r65 = Body.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(9, 50).addBox(-8.0F, 4.5F, 0.75F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.6569F, -0.2772F, -0.6889F, 0.124F, 0.1231F, 0.4004F));
		PartDefinition cube_r66 = Body.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(11, 61).addBox(5.0F, 4.5F, 0.75F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.6569F, -0.2772F, -0.6889F, 0.124F, -0.1231F, -0.4004F));
		PartDefinition cube_r67 = Body.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(13, 30).addBox(-7.0F, -2.0F, 3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.9632F, 6.8836F, -0.6692F, 0.0F, 0.0F, -0.7418F));
		PartDefinition cube_r68 = Body.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(9, 67).addBox(5.0F, -2.0F, 3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.9632F, 6.8836F, -0.6692F, 0.0F, 0.0F, 0.7418F));
		PartDefinition cube_r69 = Body.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(14, 39).addBox(5.0F, -3.0F, 3.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, 11.0497F, -0.6567F, 0.0F, 0.0F, 0.0F));
		PartDefinition cube_r70 = Body.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(46, 0).addBox(5.0F, -3.0F, 3.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, 7.7882F, -0.7453F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r71 = Body.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(63, 50).addBox(5.0F, -3.0F, 3.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.5F, 5.9728F, -0.4389F, -0.0436F, 0.0F, 0.0F));
		PartDefinition cube_r72 = Body.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(44, 93).addBox(5.0F, -3.0F, 1.8F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.6569F, -0.2772F, -0.6889F, 0.124F, -0.1231F, 0.7777F));
		PartDefinition cube_r73 = Body.addOrReplaceChild("cube_r73",
				CubeListBuilder.create().texOffs(23, 50).addBox(-3.0F, -4.0F, 0.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(57, 80).addBox(7.0F, -4.0F, 0.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, 12.4728F, -0.5889F, 0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r74 = Body.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(19, 24).addBox(-5.0F, -2.0F, 5.0F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.8581F, 12.4125F, -1.3446F, 1.6646F, 0.3472F, -0.0196F));
		PartDefinition cube_r75 = Body.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(0, 30).addBox(2.0F, -2.0F, 5.0F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.8581F, 12.4125F, -1.3446F, 1.6646F, -0.3472F, 0.0196F));
		PartDefinition cube_r76 = Body.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(24, 0).addBox(2.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(36, 61)
				.addBox(-0.75F, -4.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(75, 61).addBox(-3.25F, -4.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 12.4728F, -3.8389F, 0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r77 = Body.addOrReplaceChild("cube_r77",
				CubeListBuilder.create().texOffs(30, 13).addBox(-3.0F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 29).addBox(2.6955F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.8478F, 9.9779F, 2.203F, -0.0436F, 0.0F, 0.0F));
		PartDefinition cube_r78 = Body.addOrReplaceChild("cube_r78",
				CubeListBuilder.create().texOffs(84, 38).addBox(2.0F, -1.0F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(84, 48).addBox(-6.6955F, -1.0F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.8478F, 10.6229F, -2.5801F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r79 = Body.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(0, 88).addBox(-3.0F, -1.0F, 0.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0761F, 10.7728F, -3.7183F, 0.1415F, 0.3892F, 0.054F));
		PartDefinition cube_r80 = Body.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(88, 22).addBox(1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0761F, 10.7728F, -3.7183F, 0.1415F, -0.3892F, -0.054F));
		PartDefinition cube_r81 = Body.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(48, 45).addBox(1.0F, -1.0F, 0.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 10.7228F, -3.3389F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r82 = Body.addOrReplaceChild("cube_r82",
				CubeListBuilder.create().texOffs(48, 9).addBox(-3.0F, -10.0F, 0.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(34, 39).addBox(-6.4F, -12.0F, 0.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.2F, 18.0039F, -2.8928F, -0.0852F, 0.0F, 0.0F));
		PartDefinition cube_r83 = Body.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(94, 42).addBox(-5.0F, -2.0F, 5.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.743F, 13.6772F, -1.5274F, 1.6166F, 0.3563F, -0.1587F));
		PartDefinition cube_r84 = Body.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(96, 52).addBox(2.0F, -2.0F, 5.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.743F, 13.6772F, -1.5274F, 1.6166F, -0.3563F, 0.1587F));
		PartDefinition cube_r85 = Body.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(61, 1).addBox(-3.0F, -2.0F, 5.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.393F, 12.6772F, -1.9274F, 1.6194F, 0.4871F, -0.1519F));
		PartDefinition cube_r86 = Body.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(62, 15).addBox(2.0F, -2.0F, 5.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.393F, 12.6772F, -1.9274F, 1.6194F, -0.4871F, 0.1519F));
		PartDefinition cube_r87 = Body.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(47, 87).addBox(1.0F, -2.0F, 6.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.269F, 14.6163F, -1.4179F, 1.4096F, 0.4323F, 0.0608F));
		PartDefinition cube_r88 = Body.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(33, 99).addBox(1.0F, -2.0F, 6.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.4674F, 17.0453F, 0.6358F, 1.715F, 0.4323F, 0.0608F));
		PartDefinition cube_r89 = Body.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(89, 1).addBox(-2.0F, -2.0F, 6.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.269F, 14.6163F, -1.4179F, 1.4096F, -0.4323F, -0.0608F));
		PartDefinition cube_r90 = Body.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(100, 9).addBox(-2.0F, -2.0F, 6.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.4674F, 17.0453F, 0.6358F, 1.715F, -0.4323F, -0.0608F));
		PartDefinition cube_r91 = Body.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(16, 86).addBox(-2.0F, -2.0F, 7.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.1433F, -4.1318F, -5.324F, -0.6623F, 0.0447F, -0.3463F));
		PartDefinition cube_r92 = Body.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(86, 30).addBox(0.0F, -2.0F, 7.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.1433F, -4.1318F, -5.324F, -0.6623F, -0.0447F, 0.3463F));
		PartDefinition cube_r93 = Body.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(30, 6).addBox(-2.0F, -2.0F, 5.0F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 18.1311F, -0.0164F, 1.7017F, 0.0F, 0.0F));
		PartDefinition cube_r94 = Body.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(79, 44).addBox(-2.0F, -2.0F, 8.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 16.7228F, -3.6933F, 1.309F, 0.0F, 0.0F));
		PartDefinition cube_r95 = Body.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(84, 17).addBox(-2.0F, -2.0F, 8.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 14.9951F, -0.0609F, 1.7017F, 0.0F, 0.0F));
		PartDefinition cube_r96 = Body.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(38, 73).addBox(-3.0F, -2.0F, 8.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 13.2468F, -1.152F, 1.6144F, 0.0F, 0.0F));
		PartDefinition cube_r97 = Body.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(101, 47).addBox(0.0F, -2.0F, 8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.4092F, 10.2303F, -4.4741F, 1.2053F, 0.147F, 0.3655F));
		PartDefinition cube_r98 = Body.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(6, 102).addBox(-2.0F, -2.0F, 8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.4092F, 10.2303F, -4.4741F, 1.2053F, -0.147F, -0.3655F));
		PartDefinition cube_r99 = Body.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(84, 54).addBox(-2.0F, -2.0F, 8.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 11.5F, -5.0F, 1.1781F, 0.0F, 0.0F));
		PartDefinition cube_r100 = Body.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(77, 71).addBox(0.0F, -2.0F, 11.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-9.4927F, 3.8412F, -2.3572F, 1.5103F, 0.6768F, 1.3001F));
		PartDefinition cube_r101 = Body.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(70, 79).addBox(-2.0F, -2.0F, 11.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(9.4927F, 3.8412F, -2.3572F, 1.5103F, -0.6768F, -1.3001F));
		PartDefinition cube_r102 = Body.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(81, 92).addBox(0.0F, -2.0125F, 9.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.0465F, 0.9439F, -5.0431F, 1.1176F, 0.6768F, 1.3001F));
		PartDefinition cube_r103 = Body.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(64, 93).addBox(0.0F, -2.0F, 9.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.8929F, 8.0908F, -8.6963F, 0.7776F, -0.0447F, 0.3463F));
		PartDefinition cube_r104 = Body.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(81, 34).addBox(-2.0F, -2.0F, 8.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.081F, 14.7016F, -0.4805F, 1.6158F, 0.123F, -1.219F));
		PartDefinition cube_r105 = Body.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(96, 98).addBox(1.0F, -2.0F, 7.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.5F, 11.5692F, -0.8408F, 1.5708F, -0.1309F, 1.5708F));
		PartDefinition cube_r106 = Body.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(80, 11).addBox(1.0F, -2.0F, 8.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.081F, 14.7016F, -0.4805F, 1.6158F, -0.123F, 1.219F));
		PartDefinition cube_r107 = Body.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(91, 93).addBox(0.025F, -2.0F, 7.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.25F, 1.5F, -8.25F, 0.1231F, -0.0447F, 0.3463F));
		PartDefinition cube_r108 = Body.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(54, 94).addBox(-2.0F, -2.0125F, 9.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.0465F, 0.9439F, -5.0431F, 1.1176F, -0.6768F, -1.3001F));
		PartDefinition cube_r109 = Body.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(95, 0).addBox(-2.0F, -2.0F, 9.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.8929F, 8.0908F, -8.6963F, 0.7776F, 0.0447F, -0.3463F));
		PartDefinition cube_r110 = Body.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(71, 95).addBox(-2.0125F, -2.0F, 7.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.25F, 1.5F, -8.25F, 0.1231F, 0.0447F, -0.3463F));
		PartDefinition cube_r111 = Body.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(22, 55).addBox(3.0F, -32.5F, -22.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(19.0F, 3.9988F, -32.7284F, -1.5708F, 0.0F, -1.5708F));
		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm",
				CubeListBuilder.create().texOffs(91, 49).addBox(-3.0F, -3.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(70, 55).addBox(-3.5F, 6.25F, -2.15F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(13, 44)
						.addBox(-3.65F, 6.9F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(16, 65).addBox(-3.7F, 1.45F, -0.5F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(78, 21)
						.addBox(-3.334F, 4.0605F, -1.95F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(76, 29).addBox(-3.334F, 4.0605F, -0.05F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(66, 9)
						.addBox(-3.5F, 6.25F, 0.15F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition cube_r112 = RightArm.addOrReplaceChild("cube_r112",
				CubeListBuilder.create().texOffs(33, 77).addBox(-3.0F, 1.0F, -1.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(78, 0).addBox(-3.0F, 1.0F, 1.1F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.675F, 0.25F, -1.05F, 0.0F, 0.0F, -0.0873F));
		PartDefinition cube_r113 = RightArm.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(90, 9).addBox(-3.0F, -1.0F, 0.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.3F, -0.2827F, 2.0354F, -1.6134F, 0.0094F, 0.218F));
		PartDefinition cube_r114 = RightArm.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(90, 12).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.4477F, 1.5879F, -1.8326F, 0.0F, 0.0F));
		PartDefinition cube_r115 = RightArm.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(90, 58).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.5173F, -1.5354F, 1.6144F, 0.0F, 0.0F));
		PartDefinition cube_r116 = RightArm.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(90, 90).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.4477F, -1.5879F, 1.8326F, 0.0F, 0.0F));
		PartDefinition cube_r117 = RightArm.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(91, 36).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.6711F, -1.4837F, 0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r118 = RightArm.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(91, 39).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.6711F, 1.4837F, -0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r119 = RightArm.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(13, 33).addBox(-3.0F, -1.0F, -2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.9686F, -0.4341F, 1.7F, 0.0F, 0.0F, -1.309F));
		PartDefinition cube_r120 = RightArm.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(91, 46).addBox(-3.0F, -1.0F, 0.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.3734F, -0.5282F, -1.5362F, 0.2182F, 0.0F, -1.309F));
		PartDefinition cube_r121 = RightArm.addOrReplaceChild("cube_r121",
				CubeListBuilder.create().texOffs(96, 15).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(96, 23).addBox(-2.0F, -1.0F, -2.5F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5686F, 0.0659F, 0.25F, 0.0F, 0.0F, -1.4399F));
		PartDefinition cube_r122 = RightArm.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(76, 75).addBox(-2.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5686F, -1.6841F, 0.0F, 0.0F, 0.0F, -1.8326F));
		PartDefinition cube_r123 = RightArm.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(54, 58).addBox(0.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.1846F, -1.5979F, 0.0F, 0.0F, 0.0F, -0.6545F));
		PartDefinition cube_r124 = RightArm.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(98, 32).addBox(-3.0F, -1.0F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.1619F, -2.9484F, 0.0F, 0.0F, 0.0F, -0.3054F));
		PartDefinition cube_r125 = RightArm.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(0, 30).addBox(-3.0F, -3.0F, 0.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.7F, 4.45F, -1.0F, 0.0F, 0.0F, -0.1745F));
		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm",
				CubeListBuilder.create().texOffs(0, 93).addBox(-1.0F, -3.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(72, 65).addBox(-1.5F, 6.25F, -2.15F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(28, 65)
						.addBox(-1.3F, 1.45F, -0.5F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(47, 1).addBox(-1.35F, 6.9F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(75, 38)
						.addBox(-1.666F, 4.0605F, -1.95F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(19, 75).addBox(-1.666F, 4.0605F, -0.05F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(70, 15)
						.addBox(-1.5F, 6.25F, 0.15F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition cube_r126 = LeftArm.addOrReplaceChild("cube_r126",
				CubeListBuilder.create().texOffs(66, 74).addBox(-2.0F, 1.0F, -1.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(75, 48).addBox(-2.0F, 1.0F, -3.1F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.675F, 0.25F, 1.05F, 0.0F, 0.0F, 0.0873F));
		PartDefinition cube_r127 = LeftArm.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(28, 88).addBox(-1.0F, -1.0F, -2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.9686F, -0.4341F, 1.7F, 0.0F, 0.0F, 1.309F));
		PartDefinition cube_r128 = LeftArm.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(62, 98).addBox(2.0F, -3.0F, 0.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 4.45F, -1.0F, 0.0F, 0.0F, 0.1745F));
		PartDefinition cube_r129 = LeftArm.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(98, 74).addBox(0.0F, -1.0F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.1619F, -2.9484F, 0.0F, 0.0F, 0.0F, 0.3054F));
		PartDefinition cube_r130 = LeftArm.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(55, 91).addBox(-1.0F, -1.0F, 0.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.3734F, -0.5282F, -1.5362F, 0.2182F, 0.0F, 1.309F));
		PartDefinition cube_r131 = LeftArm.addOrReplaceChild("cube_r131",
				CubeListBuilder.create().texOffs(89, 98).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(98, 93).addBox(-1.0F, -1.0F, -2.5F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5686F, 0.0659F, 0.25F, 0.0F, 0.0F, 1.4399F));
		PartDefinition cube_r132 = LeftArm.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(91, 83).addBox(-1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.4477F, 1.5879F, -1.8326F, 0.0F, 0.0F));
		PartDefinition cube_r133 = LeftArm.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(10, 92).addBox(-1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.3F, 0.7173F, 2.0354F, -1.6134F, -0.0094F, -0.218F));
		PartDefinition cube_r134 = LeftArm.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(22, 92).addBox(-1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.5173F, -1.5354F, 1.6144F, 0.0F, 0.0F));
		PartDefinition cube_r135 = LeftArm.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(8, 78).addBox(-1.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5686F, -1.6841F, 0.0F, 0.0F, 0.0F, 1.8326F));
		PartDefinition cube_r136 = LeftArm.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(36, 90).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.1846F, -1.5979F, 0.0F, 0.0F, 0.0F, 0.6545F));
		PartDefinition cube_r137 = LeftArm.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(92, 68).addBox(-1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.4477F, -1.5879F, 1.8326F, 0.0F, 0.0F));
		PartDefinition cube_r138 = LeftArm.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(92, 79).addBox(-1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.6711F, -1.4837F, 0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r139 = LeftArm.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(92, 87).addBox(-1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.6711F, 1.4837F, -0.4363F, 0.0F, 0.0F));
		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(38, 29).addBox(-2.7F, 6.1F, -2.5F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition cube_r140 = RightLeg.addOrReplaceChild("cube_r140",
				CubeListBuilder.create().texOffs(9, 70).addBox(3.6F, 0.8F, 2.0125F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(43, 58).addBox(1.0F, 1.0F, 2.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.3271F, -2.1002F, -4.45F, 0.0F, 0.0F, 0.2182F));
		PartDefinition cube_r141 = RightLeg.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(20, 33).mirror().addBox(0.2653F, 0.0757F, -0.05F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-3.2685F, 0.981F, -2.4F, 0.0F, 0.0F, -0.0436F));
		PartDefinition cube_r142 = RightLeg.addOrReplaceChild("cube_r142",
				CubeListBuilder.create().texOffs(52, 29).mirror().addBox(6.0F, -2.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(50, 65).addBox(6.0F, -2.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.2964F, -6.3227F, -1.1315F, -0.0872F, -0.0038F, 1.5273F));
		PartDefinition cube_r143 = RightLeg.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(32, 24).mirror().addBox(6.0F, -2.0F, 0.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-2.8889F, -6.6396F, -1.033F, 0.0F, 0.0F, 1.7882F));
		PartDefinition cube_r144 = RightLeg.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(93, 61).mirror().addBox(6.0F, -2.0F, 0.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-5.569F, -4.3457F, -0.783F, 0.0F, 0.0F, 1.3955F));
		PartDefinition cube_r145 = RightLeg.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(95, 71).mirror().addBox(6.0F, -2.0F, 0.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-3.871F, -1.9127F, -0.783F, 0.0F, 0.0F, 1.6136F));
		PartDefinition cube_r146 = RightLeg.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(82, 80).mirror().addBox(6.0F, -2.0F, 0.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-4.7811F, 1.2331F, -0.783F, 0.0F, 0.0F, 1.4827F));
		PartDefinition cube_r147 = RightLeg.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(80, 5).addBox(-6.0F, 0.0F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.972F, 3.6114F, -0.9045F, 0.0002F, -0.0919F, -0.0247F));
		PartDefinition cube_r148 = RightLeg.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(19, 80).addBox(-6.0F, 0.0F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.7193F, 3.7034F, -4.279F, 0.0437F, 0.0388F, -0.019F));
		PartDefinition cube_r149 = RightLeg.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(73, 80).addBox(3.0F, 0.0F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.1788F, 3.6668F, -3.4917F, 0.0438F, 0.0919F, 0.0247F));
		PartDefinition cube_r150 = RightLeg.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(81, 58).addBox(3.0F, 0.0F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.022F, 3.2989F, -0.7045F, -0.0871F, 0.0919F, 0.0247F));
		PartDefinition cube_r151 = RightLeg.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(0, 49).addBox(1.0F, -4.0F, 2.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.792F, 3.5245F, -4.4F, 0.0F, 0.0F, 0.0873F));
		PartDefinition cube_r152 = RightLeg.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(86, 74).addBox(0.9875F, -1.0F, 2.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.7F, 6.9675F, -0.5782F, -0.0873F, 0.0F, 0.0F));
		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 39).addBox(-2.3F, 6.1F, -2.5F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition cube_r153 = LeftLeg.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(54, 85).addBox(-6.0F, 0.0F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.022F, 3.2989F, -0.7045F, -0.0871F, -0.0919F, -0.0247F));
		PartDefinition cube_r154 = LeftLeg.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(82, 83).addBox(3.0F, 0.0F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.972F, 3.6114F, -0.9045F, 0.0002F, 0.0919F, 0.0247F));
		PartDefinition cube_r155 = LeftLeg.addOrReplaceChild("cube_r155", CubeListBuilder.create().texOffs(83, 68).addBox(-6.0F, 0.0F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.1788F, 3.6668F, -3.4917F, 0.0438F, -0.0919F, -0.0247F));
		PartDefinition cube_r156 = LeftLeg.addOrReplaceChild("cube_r156", CubeListBuilder.create().texOffs(31, 82).addBox(3.0F, 0.0F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.7193F, 3.7034F, -4.279F, 0.0437F, -0.0388F, 0.019F));
		PartDefinition cube_r157 = LeftLeg.addOrReplaceChild("cube_r157",
				CubeListBuilder.create().texOffs(0, 60).addBox(-4.0F, 1.0F, 2.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(9, 70).mirror().addBox(-4.6F, 0.8F, 2.0125F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(3.3271F, -2.1002F, -4.45F, 0.0F, 0.0F, -0.2182F));
		PartDefinition cube_r158 = LeftLeg.addOrReplaceChild("cube_r158", CubeListBuilder.create().texOffs(20, 33).addBox(-4.2653F, 0.0757F, -0.05F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.2685F, 0.981F, -2.4F, 0.0F, 0.0F, 0.0436F));
		PartDefinition cube_r159 = LeftLeg.addOrReplaceChild("cube_r159", CubeListBuilder.create().texOffs(66, 88).addBox(-5.9875F, -1.0F, 2.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.7F, 6.9675F, -0.5782F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r160 = LeftLeg.addOrReplaceChild("cube_r160", CubeListBuilder.create().texOffs(14, 50).addBox(-3.0F, -4.0F, 2.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.792F, 3.5245F, -4.4F, 0.0F, 0.0F, -0.0873F));
		PartDefinition cube_r161 = LeftLeg.addOrReplaceChild("cube_r161", CubeListBuilder.create().texOffs(82, 80).addBox(-10.0F, -2.0F, 0.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.7811F, 1.2331F, -0.783F, 0.0F, 0.0F, -1.4827F));
		PartDefinition cube_r162 = LeftLeg.addOrReplaceChild("cube_r162", CubeListBuilder.create().texOffs(95, 71).addBox(-9.0F, -2.0F, 0.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.871F, -1.9127F, -0.783F, 0.0F, 0.0F, -1.6136F));
		PartDefinition cube_r163 = LeftLeg.addOrReplaceChild("cube_r163", CubeListBuilder.create().texOffs(32, 24).addBox(-8.0F, -2.0F, 0.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.8889F, -6.6396F, -1.033F, 0.0F, 0.0F, -1.7882F));
		PartDefinition cube_r164 = LeftLeg.addOrReplaceChild("cube_r164", CubeListBuilder.create().texOffs(52, 29).addBox(-7.0F, -2.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.2964F, -6.3227F, -1.1315F, -0.0872F, 0.0038F, -1.5273F));
		PartDefinition cube_r165 = LeftLeg.addOrReplaceChild("cube_r165", CubeListBuilder.create().texOffs(93, 61).addBox(-9.0F, -2.0F, 0.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.569F, -4.3457F, -0.783F, 0.0F, 0.0F, -1.3955F));
		PartDefinition LeftShoes = partdefinition.addOrReplaceChild("LeftShoes", CubeListBuilder.create().texOffs(39, 84).addBox(-0.7F, 10.1375F, -3.7F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition cube_r166 = LeftShoes.addOrReplaceChild("cube_r166", CubeListBuilder.create().texOffs(8, 84).addBox(-3.0F, 1.0F, 2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.275F, 10.174F, -5.9358F, 0.48F, 0.0F, 0.0F));
		PartDefinition cube_r167 = LeftShoes.addOrReplaceChild("cube_r167", CubeListBuilder.create().texOffs(69, 0).addBox(-4.0F, 1.0F, 2.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.7705F, 9.1375F, -3.8221F, 0.0F, -0.4363F, 0.0F));
		PartDefinition cube_r168 = LeftShoes.addOrReplaceChild("cube_r168", CubeListBuilder.create().texOffs(63, 67).addBox(2.0F, 1.0F, 2.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.1705F, 9.1375F, -3.8221F, 0.0F, 0.4363F, 0.0F));
		PartDefinition cube_r169 = LeftShoes.addOrReplaceChild("cube_r169", CubeListBuilder.create().texOffs(80, 89).addBox(-6.0F, 1.0F, 2.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.7F, 9.4929F, -0.9906F, 0.1745F, 0.0F, 0.0F));
		PartDefinition cube_r170 = LeftShoes.addOrReplaceChild("cube_r170", CubeListBuilder.create().texOffs(67, 31).addBox(-4.0F, 1.0F, 2.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.2793F, 9.025F, -4.6728F, 0.0F, -0.0873F, 0.0F));
		PartDefinition RightShoes = partdefinition.addOrReplaceChild("RightShoes", CubeListBuilder.create().texOffs(0, 82).addBox(-1.3F, 10.1375F, -3.7F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition cube_r171 = RightShoes.addOrReplaceChild("cube_r171", CubeListBuilder.create().texOffs(82, 11).addBox(1.0F, 1.0F, 2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.275F, 10.174F, -5.9358F, 0.48F, 0.0F, 0.0F));
		PartDefinition cube_r172 = RightShoes.addOrReplaceChild("cube_r172", CubeListBuilder.create().texOffs(54, 65).addBox(-4.0F, 1.0F, 2.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.1705F, 9.1375F, -3.8221F, 0.0F, -0.4363F, 0.0F));
		PartDefinition cube_r173 = RightShoes.addOrReplaceChild("cube_r173", CubeListBuilder.create().texOffs(66, 48).addBox(2.0F, 1.0F, 2.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.2793F, 9.025F, -4.6728F, 0.0F, 0.0873F, 0.0F));
		PartDefinition cube_r174 = RightShoes.addOrReplaceChild("cube_r174", CubeListBuilder.create().texOffs(0, 67).addBox(2.0F, 1.0F, 2.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.7705F, 9.1375F, -3.8221F, 0.0F, 0.4363F, 0.0F));
		PartDefinition cube_r175 = RightShoes.addOrReplaceChild("cube_r175", CubeListBuilder.create().texOffs(88, 27).addBox(1.0F, 1.0F, 2.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.7F, 9.4929F, -0.9906F, 0.1745F, 0.0F, 0.0F));
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
