package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

public class ShadowStepOnKeyPressedProcedure {
public static void execute(
LevelAccessor world,
double x,
double y,
double z,
Entity entity ) {
if (
entity == null ) return ;
if (MineXHunterModVariables.MapVariables.get(world).is_zoldyck==true&&==true&&true) {if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY,100,1, false, false));if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,3, false, false));if (entity instanceof Player _player) _player.getFoodData().setFoodLevel((entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel():0)-8);if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.phantom.swoop")),
SoundSource.NEUTRAL, 1, (float)1.5);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.phantom.swoop")),
SoundSource.NEUTRAL, 1, (float)1.5, false);
}
}if (entity instanceof Player _player && !_player.level().isClientSide())
_player.displayClientMessage(Component.literal("[Zoldyck] Paso de Sombra activado..."), false);}
}
}