package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class AbsorberDanoGuardiaProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
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
if (entity instanceof Player&&==true&&==true) {if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,60,3, false, false));if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.shield.block")),
SoundSource.NEUTRAL, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.shield.block")),
SoundSource.NEUTRAL, 1, 1, false);
}
}}
}
}