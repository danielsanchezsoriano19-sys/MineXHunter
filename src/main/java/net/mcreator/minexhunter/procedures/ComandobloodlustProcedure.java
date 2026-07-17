package net.mcreator.minexhunter.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.minexhunter.network.MineXHunterModVariables;

public class ComandobloodlustProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.SedSangreDesbloqueada = true;
				capability.markSyncDirty();
			});
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("Bloodlust has been forcefully unlocked!"), true);
	}
}