package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

public class GestorAnimacionesCombateProcedure {
public static void execute(
LevelAccessor world,
double x,
double y,
double z,
Entity entity ) {
if (
entity == null ) return ;
if (==true) {if (==true) {if (entity.getDeltaMovement().x()+entity.getDeltaMovement().z()!=0) {if (world instanceof ServerLevel _level)
_level.getServer().getCommands().performPrefixedCommand(
new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO,
_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "playeranimator play animation.player.caminar_defendiendo");}else{if (world instanceof ServerLevel _level)
_level.getServer().getCommands().performPrefixedCommand(
new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO,
_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "playeranimator play animation.player.defender");}}else{if (entity.getDeltaMovement().x()+entity.getDeltaMovement().z()!=0) {if (world instanceof ServerLevel _level)
_level.getServer().getCommands().performPrefixedCommand(
new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO,
_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "playeranimator play animation.player.caminar");}else{if (world instanceof ServerLevel _level)
_level.getServer().getCommands().performPrefixedCommand(
new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO,
_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "playeranimator play animation.player.pose_de_pelea");}}}
}
}