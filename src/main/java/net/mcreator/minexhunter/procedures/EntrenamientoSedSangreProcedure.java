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

private static void execute(
@Nullable Event event,
Entity entity ) {
if (
entity == null ) return ;
if (>=100) {}if (>=500&&==false) {if (entity instanceof Player _player && !_player.level().isClientSide())
_player.displayClientMessage(Component.literal("Your rage knows no bounds..."), true);}
}
}