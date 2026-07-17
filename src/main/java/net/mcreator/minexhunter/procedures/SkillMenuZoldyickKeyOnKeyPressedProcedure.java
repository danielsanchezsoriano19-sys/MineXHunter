package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

public class SkillMenuZoldyickKeyOnKeyPressedProcedure {
public static void execute(
LevelAccessor world,
double x,
double y,
double z,
Entity entity ) {
if (
entity == null ) return ;
if (==true&&(entity instanceof Player _plr0 && _plr0.containerMenu instanceof SkillMenuMenu)==false) {if(entity instanceof ServerPlayer _ent) {
BlockPos _bpos = BlockPos.containing(x,y,z);
NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
@Override public Component getDisplayName() {
return Component.literal("SkillMenu");
}
@Override public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
return new SkillMenuMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
}
}, _bpos);
}}else{if (==false) {if (entity instanceof Player _player && !_player.level().isClientSide())
_player.displayClientMessage(Component.literal("Tu cuerpo rechaza este conocimiento. No eres un Zoldyck."), false);if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.enderman.teleport")),
SoundSource.NEUTRAL, 1, (float)0.5);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.enderman.teleport")),
SoundSource.NEUTRAL, 1, (float)0.5, false);
}
}}}
}
}