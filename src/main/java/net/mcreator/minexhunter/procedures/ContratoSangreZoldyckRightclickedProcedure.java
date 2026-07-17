package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

public class ContratoSangreZoldyckRightclickedProcedure {
public static void execute(
LevelAccessor world,
double x,
double y,
double z,
Entity entity,
ItemStack itemstack ) {
if (
entity == null ) return ;
if (==false) {itemstack.shrink(1);if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.spawn")),
SoundSource.NEUTRAL, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.spawn")),
SoundSource.NEUTRAL, 1, 1, false);
}
}if (entity instanceof Player _player && !_player.level().isClientSide())
_player.displayClientMessage(Component.literal("[Familia Zoldyck] Tu sangre ha sido aceptada. Ahora puedes usar el men\u00FA (G)."), false);}else if (true) {if (entity instanceof Player _player && !_player.level().isClientSide())
_player.displayClientMessage(Component.literal("Ya perteneces a la familia Zoldyck."), false);}
}
}