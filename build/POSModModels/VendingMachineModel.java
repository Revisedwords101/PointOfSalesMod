// Made with Blockbench 5.2.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class VendingMachineModel<T extends VendingMachineEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "vendingmachinemodel"), "main");
	private final ModelPart VendingMachine;
	private final ModelPart MainParts;
	private final ModelPart BackPannels;
	private final ModelPart DropAreaCover;

	public VendingMachineModel(ModelPart root) {
		this.VendingMachine = root.getChild("VendingMachine");
		this.MainParts = this.VendingMachine.getChild("MainParts");
		this.BackPannels = this.MainParts.getChild("BackPannels");
		this.DropAreaCover = this.VendingMachine.getChild("DropAreaCover");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition VendingMachine = partdefinition.addOrReplaceChild("VendingMachine", CubeListBuilder.create().texOffs(24, 48).addBox(5.0F, -17.0F, -9.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 96).addBox(4.0F, -26.0F, -9.0F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 73).addBox(-6.0F, -32.0F, -7.0F, 10.0F, 25.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 73).addBox(-6.0F, -32.0F, -6.0F, 10.0F, 30.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition MainParts = VendingMachine.addOrReplaceChild("MainParts", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -34.0F, -8.0F, 2.0F, 32.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(36, 0).addBox(-8.0F, -2.0F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(52, 55).addBox(4.0F, -11.0F, -8.0F, 4.0F, 9.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(76, 18).addBox(7.0F, -13.0F, -8.0F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(44, 80).addBox(5.0F, -13.0F, -6.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(76, 36).addBox(4.0F, -13.0F, -8.0F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(36, 18).addBox(4.0F, -34.0F, -8.0F, 4.0F, 21.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 55).addBox(-6.0F, -34.0F, -8.0F, 10.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-6.0F, -7.0F, -8.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition BackPannels = MainParts.addOrReplaceChild("BackPannels", CubeListBuilder.create().texOffs(76, 80).addBox(-6.0F, -32.0F, 7.0F, 10.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(92, 54).addBox(-6.0F, -16.0F, 7.0F, 10.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition DropAreaCover = VendingMachine.addOrReplaceChild("DropAreaCover", CubeListBuilder.create().texOffs(92, 69).addBox(-6.0F, -10.0F, -11.0F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(VendingMachineEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		VendingMachine.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}