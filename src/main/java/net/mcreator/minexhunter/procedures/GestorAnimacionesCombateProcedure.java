package net.mcreator.minexhunter.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.minexhunter.network.MineXHunterModVariables;

public class GestorAnimacionesCombateProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).modopelea == true) {
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).bloqueando == true) {
				if (entity.getDeltaMovement().x() + entity.getDeltaMovement().z() != 0) {
				} else {
				}
			} else {
				if (entity.getDeltaMovement().x() + entity.getDeltaMovement().z() != 0) {
				} else {
				}
			}
		}
	}
}