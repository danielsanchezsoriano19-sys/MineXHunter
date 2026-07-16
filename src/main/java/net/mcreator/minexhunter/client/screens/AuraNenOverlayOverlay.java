package net.mcreator.minexhunter.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

import net.mcreator.minexhunter.procedures.AuraTextoLogicaProcedure;
import net.mcreator.minexhunter.procedures.AuraNenOverlayDisplayOverlayIngameProcedure;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class AuraNenOverlayOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getWindow().getGuiScaledWidth();
		int h = event.getWindow().getGuiScaledHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		if (AuraNenOverlayDisplayOverlayIngameProcedure.execute(entity)) {
			event.getGuiGraphics().drawString(Minecraft.getInstance().font, Component.translatable("gui.mine_x_hunter.aura_nen_overlay.label_aura"), w / 2 + -213, h / 2 + -120, -39424, false);
			event.getGuiGraphics().drawString(Minecraft.getInstance().font,

					AuraTextoLogicaProcedure.execute(entity), w / 2 + -185, h / 2 + -120, -1, false);
		}
	}
}