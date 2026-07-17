/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minexhunter.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.minexhunter.network.TenkeyMessage;
import net.mcreator.minexhunter.network.RenkeyMessage;
import net.mcreator.minexhunter.MineXHunterMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MineXHunterModKeyMappings {
	public static final KeyMapping TENKEY = new KeyMapping("key.mine_x_hunter.tenkey", GLFW.GLFW_KEY_V, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				MineXHunterMod.PACKET_HANDLER.sendToServer(new TenkeyMessage(0, 0));
				TenkeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping SKILL_MENU_ZOLDYICK_KEY = new KeyMapping("key.mine_x_hunter.skill_menu_zoldyick_key", GLFW.GLFW_KEY_G, "key.categories.misc");
	public static final KeyMapping RENKEY = new KeyMapping("key.mine_x_hunter.renkey", GLFW.GLFW_KEY_B, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				MineXHunterMod.PACKET_HANDLER.sendToServer(new RenkeyMessage(0, 0));
				RenkeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(TENKEY);
		event.register(SKILL_MENU_ZOLDYICK_KEY);
		event.register(RENKEY);
	}

	@Mod.EventBusSubscriber(Dist.CLIENT)
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				TENKEY.consumeClick();
				RENKEY.consumeClick();
			}
		}
	}
}