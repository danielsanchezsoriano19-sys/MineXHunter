package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

public class TeclaModoPeleaOnKeyPressedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).modopelea == true) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.modopelea = false;
					capability.bloqueando = false;
					capability.markSyncDirty();
				});
			}
		} else {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.modopelea = true;
					capability.markSyncDirty();
				});
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Combat mode activated"), true);
		}
	}
}