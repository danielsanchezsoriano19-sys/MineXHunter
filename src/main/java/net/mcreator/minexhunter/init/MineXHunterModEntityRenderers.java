/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minexhunter.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.minexhunter.client.renderer.AuraTenEntityRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MineXHunterModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MineXHunterModEntities.AURA_TEN_ENTITY.get(), AuraTenEntityRenderer::new);
	}
}