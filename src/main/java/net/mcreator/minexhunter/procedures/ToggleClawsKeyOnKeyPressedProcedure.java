package net.mcreator.minexhunter.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.minexhunter.network.MineXHunterModVariables;

public class ToggleClawsKeyOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (MineXHunterModVariables.MapVariables.get(world).is_zoldyck == true && entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).unlocked_claws == true) {
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).claws_active == false) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.claws_active = true;
						capability.markSyncDirty();
					});
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.ambient")), SoundSource.NEUTRAL, 1, (float) 1.5);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.ambient")), SoundSource.NEUTRAL, 1, (float) 1.5, false);
					}
				}
			} else {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.claws_active = false;
						capability.markSyncDirty();
					});
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.item.break")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.item.break")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			}
		}
	}
}