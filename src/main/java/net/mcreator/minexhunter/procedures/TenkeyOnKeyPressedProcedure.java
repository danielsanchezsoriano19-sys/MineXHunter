package net.mcreator.minexhunter.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.minexhunter.network.MineXHunterModVariables;
import net.mcreator.minexhunter.init.MineXHunterModEntities;
import net.mcreator.minexhunter.entity.AuraTenEntityEntity;

import java.util.Comparator;

public class TenkeyOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).Nendesbloqueado) {
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).TenActive == false) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.TenActive = true;
						capability.markSyncDirty();
					});
				}
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = MineXHunterModEntities.AURA_TEN_ENTITY.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
					}
				}
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.RenActive = false;
						capability.markSyncDirty();
					});
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Ten activated!"), true);
			} else {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.TenActive = false;
						capability.markSyncDirty();
					});
				}
				if (!(findEntityInWorldRange(world, AuraTenEntityEntity.class, x, y, z, 4)).level().isClientSide())
					(findEntityInWorldRange(world, AuraTenEntityEntity.class, x, y, z, 4)).discard();
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Ten deactivated!"), true);
			}
		}
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}