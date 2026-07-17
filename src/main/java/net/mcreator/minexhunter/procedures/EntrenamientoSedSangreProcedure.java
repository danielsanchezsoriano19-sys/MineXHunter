package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class EntrenamientoSedSangreProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		{
			entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.BarraSedSangre = entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).BarraSedSangre + 1;
				capability.markSyncDirty();
			});
		}
		if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).BarraSedSangre >= 100) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.SedSangreActiva = true;
					capability.markSyncDirty();
				});
			}
		}
		if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).BarraSedSangre >= 500
				&& !entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).SedSangreDesbloqueada) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.SedSangreDesbloqueada = true;
					capability.markSyncDirty();
				});
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Your rage knows no bounds..."), true);
		}
	}
}