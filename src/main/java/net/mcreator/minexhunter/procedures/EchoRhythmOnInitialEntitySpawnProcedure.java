package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

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