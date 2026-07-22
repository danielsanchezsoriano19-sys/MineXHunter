package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class ManejadorClickDerechoDefensaProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

private static void execute(
@Nullable Event event,
Entity entity ) {
if (
entity == null ) return ;
if (==true) {if (entity instanceof LivingEntity _livEnt0 && _livEnt0.isBlocking()) {if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,5,2, false, false));}else{}}
}
}