package net.mcreator.minexhunter.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.minexhunter.network.MineXHunterModVariables;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class EntrenamientoVidaProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (true) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.medidor_dano_recibido = entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).medidor_dano_recibido
							+ (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) - (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1);
					capability.markSyncDirty();
				});
			}
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).medidor_dano_recibido >= 5000) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.medidor_dano_recibido = 0;
						capability.vida_extra_entrenada = entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).vida_extra_entrenada + 2;
						capability.markSyncDirty();
					});
				}
			}
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).vida_extra_entrenada > entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES)
					.orElseGet(MineXHunterModVariables.PlayerVariables::new).vida_extra_maxima) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.vida_extra_entrenada = entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).vida_extra_maxima;
						capability.markSyncDirty();
					});
				}
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.MAX_HEALTH).removeModifier(UUID.fromString("356da47f-4fa4-3a00-95a7-ce05e248db0c"));
			}
			if (entity instanceof LivingEntity _entity) {
				AttributeModifier modifier = new AttributeModifier(UUID.fromString("356da47f-4fa4-3a00-95a7-ce05e248db0c"), "mine_x_hunter:entrenamiento_vida",
						entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).vida_extra_entrenada, AttributeModifier.Operation.ADDITION);
				if (!_entity.getAttribute(Attributes.MAX_HEALTH).hasModifier(modifier)) {
					_entity.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(modifier);
				}
			}
		}
	}
}