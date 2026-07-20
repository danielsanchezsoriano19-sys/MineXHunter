package net.mcreator.minexhunter.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.minexhunter.network.MineXHunterModVariables;

public class NacimientodenenAlPresionarClickDerechoEnElAireProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double prob_local = 0;
		if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).Nendesbloqueado == false) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.Nendesbloqueado = true;
					capability.markSyncDirty();
				});
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Nen Unlocked"), true);
			if ((entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).TipoHatsu).equals("Ninguno")) {
				prob_local = Math.random() * 100;
			}
			if (prob_local <= 0.033) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.TipoHatsu = "Specialization";
						capability.markSyncDirty();
					});
				}
			} else if (prob_local > 0.033 && prob_local <= 27.033) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.TipoHatsu = "Enhancement";
						capability.markSyncDirty();
					});
				}
			} else if (prob_local > 27.033 && prob_local <= 46.033) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.TipoHatsu = "Transmutation";
						capability.markSyncDirty();
					});
				}
			} else if (prob_local > 46.033 && prob_local <= 70.033) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.TipoHatsu = "Emission";
						capability.markSyncDirty();
					});
				}
			} else if (prob_local > 70.033 && prob_local <= 85.033) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.TipoHatsu = "Conjuration";
						capability.markSyncDirty();
					});
				}
			} else if (prob_local > 85.033 && prob_local <= 100) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.TipoHatsu = "Manipulation";
						capability.markSyncDirty();
					});
				}
			}
		}
	}
}