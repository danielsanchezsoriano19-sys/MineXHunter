package net.mcreator.minexhunter.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.minexhunter.network.MineXHunterModVariables;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class EntrenamientoCorrerProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity.isSprinting()) {
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).medidor_entrenamiento_corriendo >= 144000) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.medidor_entrenamiento_corriendo = 0;
						capability.nivel_velocidad_entrenando = entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).nivel_velocidad_entrenando + 1;
						capability.markSyncDirty();
					});
				}
			}
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.medidor_entrenamiento_corriendo = entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).nivel_velocidad_entrenando + 0.01;
					capability.markSyncDirty();
				});
			}
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).nivel_velocidad_entrenando > entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES)
					.orElseGet(MineXHunterModVariables.PlayerVariables::new).limite_velocidad_maxima) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.nivel_velocidad_entrenando = entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).limite_velocidad_maxima;
						capability.markSyncDirty();
					});
				}
			}
		}
		if (entity.isSprinting()) {
			if (entity instanceof LivingEntity _entity) {
				AttributeModifier modifier = new AttributeModifier(UUID.fromString("8e7340dd-7ea8-3146-88d6-2ed9edb81204"), "mine_x_hunter:velocidad_sprint",
						entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).nivel_velocidad_entrenando, AttributeModifier.Operation.ADDITION);
				if (!_entity.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(modifier)) {
					_entity.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(modifier);
				}
			}
		} else {
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(UUID.fromString("8e7340dd-7ea8-3146-88d6-2ed9edb81204"));
			}
		}
	}
}