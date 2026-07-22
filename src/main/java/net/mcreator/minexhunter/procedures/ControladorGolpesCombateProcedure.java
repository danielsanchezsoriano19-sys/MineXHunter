package net.mcreator.minexhunter.procedures;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.SectionPos;

import net.mcreator.minexhunter.network.MineXHunterModVariables;
import net.mcreator.minexhunter.MineXHunterMod;

import javax.annotation.Nullable;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ControladorGolpesCombateProcedure {
	@SubscribeEvent
	public static void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
		MineXHunterMod.PACKET_HANDLER.sendToServer(new ControladorGolpesCombateMessage());
		execute(event.getLevel(), event.getEntity());
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ControladorGolpesCombateMessage {
		public ControladorGolpesCombateMessage() {
		}

		public ControladorGolpesCombateMessage(FriendlyByteBuf buffer) {
		}

		public static void buffer(ControladorGolpesCombateMessage message, FriendlyByteBuf buffer) {
		}

		public static void handler(ControladorGolpesCombateMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getSender().level().getChunkSource().hasChunk(SectionPos.blockToSectionCoord(context.getSender().getX()), SectionPos.blockToSectionCoord(context.getSender().getZ())))
					return;
				execute(context.getSender().level(), context.getSender());
			});
			context.setPacketHandled(true);
		}

		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			MineXHunterMod.addNetworkMessage(ControladorGolpesCombateMessage.class, ControladorGolpesCombateMessage::buffer, ControladorGolpesCombateMessage::new, ControladorGolpesCombateMessage::handler);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).modopelea == true) {
			if (entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).orElseGet(MineXHunterModVariables.PlayerVariables::new).combostep == 1) {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.combostep = 0;
						capability.markSyncDirty();
					});
				}
			} else {
				{
					entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.combostep = 1;
						capability.markSyncDirty();
					});
				}
			}
			if (entity instanceof LivingEntity) {
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 4);
			}
		}
	}
}