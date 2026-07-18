package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

public class ComprarPasodeSombraProcedure {
public static void execute(
LevelAccessor world,
double x,
double y,
double z,
Entity entity ) {
if (
entity == null ) return ;
if ((entity instanceof Player _plr ? _plr.experienceLevel : 0)>=25&&==false) {if (entity instanceof Player _player) _player.giveExperiencePoints(-25);if(entity instanceof Player _player) _player.closeContainer();if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ui.button.click")),
SoundSource.NEUTRAL, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ui.button.click")),
SoundSource.NEUTRAL, 1, 1, false);
}
}}
}
}