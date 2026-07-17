package net.mcreator.minexhunter.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.minexhunter.MineXHunterMod;

public class EchoRhythmOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		MineXHunterMod.queueServerWork(20, () -> {
			if (!entity.level().isClientSide())
				entity.discard();
		});
	}
}