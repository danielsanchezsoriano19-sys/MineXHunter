package net.mcreator.minexhunter.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.minexhunter.network.MineXHunterModVariables;

public class BotonComprarInmunidadOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof Player _plr ? _plr.experienceLevel : 0) >= 20 && entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).unlocked_inmunity == false) {
			if (entity instanceof Player _player)
				_player.giveExperienceLevels(-(20));
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.unlocked_inmunity = true;
					capability.markSyncDirty();
				});
			}
			if (entity instanceof Player _player)
				_player.closeContainer();
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ui.button.click")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ui.button.click")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
	}
}