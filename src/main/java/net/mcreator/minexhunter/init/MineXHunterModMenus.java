/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minexhunter.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.Minecraft;

import net.mcreator.minexhunter.world.inventory.SkillMenuMenu;
import net.mcreator.minexhunter.world.inventory.BettlePhoneMenu;
import net.mcreator.minexhunter.network.MenuStateUpdateMessage;
import net.mcreator.minexhunter.MineXHunterMod;

import java.util.Map;

public class MineXHunterModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MineXHunterMod.MODID);
	public static final RegistryObject<MenuType<SkillMenuMenu>> SKILL_MENU = REGISTRY.register("skill_menu", () -> IForgeMenuType.create(SkillMenuMenu::new));
	public static final RegistryObject<MenuType<BettlePhoneMenu>> BETTLE_PHONE = REGISTRY.register("bettle_phone", () -> IForgeMenuType.create(BettlePhoneMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				MineXHunterMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof MineXHunterModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				MineXHunterMod.PACKET_HANDLER.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}