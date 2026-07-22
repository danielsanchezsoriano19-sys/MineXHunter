package net.mcreator.minexhunter.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.minexhunter.entity.AuraTenEntityEntity;
import net.mcreator.minexhunter.client.model.ModelCustomModel;

public class AuraTenEntityRenderer extends MobRenderer<AuraTenEntityEntity, ModelCustomModel<AuraTenEntityEntity>> {
	private final ResourceLocation entityTexture = new ResourceLocation("mine_x_hunter:textures/entities/aura_nen_layer_1.png.png");

	public AuraTenEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelCustomModel<AuraTenEntityEntity>(context.bakeLayer(ModelCustomModel.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(AuraTenEntityEntity entity) {
		return entityTexture;
	}
}