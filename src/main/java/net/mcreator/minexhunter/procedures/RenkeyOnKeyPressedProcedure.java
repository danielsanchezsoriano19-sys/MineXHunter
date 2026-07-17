package net.mcreator.minexhunter.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.minexhunter.network.MineXHunterModVariables;

public class RenkeyOnKeyPressedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).Nendesbloqueado) {
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).RenActive == false) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.RenActive = true;
						capability.TenActive = false;
						capability.markSyncDirty();
					});
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Ren activated!"), true);
			} else {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.RenActive = false;
						capability.markSyncDirty();
					});
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Ren deactivated!"), true);
			}
		}
	}
}