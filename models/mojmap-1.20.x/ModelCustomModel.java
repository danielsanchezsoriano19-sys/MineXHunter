// Made with Blockbench 5.1.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ModelCustomModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "custommodel"), "main");
	private final ModelPart body;

	public ModelCustomModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 35)
						.addBox(-12.0F, -35.0F, -8.0F, 24.0F, 35.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-12.0F, -35.0F, -24.0F, 24.0F, 35.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 16.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(48, 0).addBox(-11.0F, -35.0F, -8.0F, 16.0F, 35.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 0.0F, -19.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(48, 0).addBox(-11.0F, -35.0F, -8.0F, 16.0F, 35.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(20.0F, 0.0F, -19.0F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}