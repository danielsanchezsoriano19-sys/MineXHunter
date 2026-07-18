package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class ShadowStepPassiveProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

private static void execute(
@Nullable Event event,
LevelAccessor world,
double x,
double y,
double z,
Entity entity ) {
if (
entity == null ) return ;
if (>0) {}if ((entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(MobEffects.INVISIBILITY))==true) {if (world instanceof ServerLevel _level)
_level.sendParticles(ParticleTypes.SMOKE, x, y, z, 5, 0.2, 0.2, 0.2, 0.01);if (world instanceof ServerLevel _level)
_level.sendParticles(ParticleTypes.SQUID_INK, x, y, z, 3, 0.1, 0.1, 0.1, 0.01);}
}
}