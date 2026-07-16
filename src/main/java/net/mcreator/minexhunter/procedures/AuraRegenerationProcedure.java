package net.mcreator.minexhunter.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.minexhunter.network.MineXHunterModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AuraRegenerationProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).Nendesbloqueado
				&& entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).RenActive == false) {
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).aura_actual < entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES)
					.orElseGet(MineXHunterModVariables.PlayerVariables::new).auramax) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.aura_actual = entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).aura_actual + 0.005;
						capability.markSyncDirty();
					});
				}
			}
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).aura_actual > entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES)
					.orElseGet(MineXHunterModVariables.PlayerVariables::new).auramax) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.aura_actual = entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).auramax;
						capability.markSyncDirty();
					});
				}
			}
		}
		if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).Nendesbloqueado
				&& entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).TenActive) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.aura_actual = entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).aura_actual - 0.02;
					capability.markSyncDirty();
				});
			}
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 1, false, false));
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.END_ROD, (x + entity.getLookAngle().x), (y + 1), (z + entity.getLookAngle().z), 2, 0.3, 0.4, 0.3, 0);
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).aura_actual <= 0) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.TenActive = false;
						capability.markSyncDirty();
					});
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("You have run out of aura! "), false);
			}
		}
	}
}