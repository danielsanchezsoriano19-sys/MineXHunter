package net.mcreator.minexhunter.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.minexhunter.MineXHunterMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MineXHunterModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		MineXHunterMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handleData);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> MineXHunterMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability)));
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> MineXHunterMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability)));
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> MineXHunterMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability)));
		}

		@SubscribeEvent
		public static void onPlayerTickUpdateSyncPlayerVariables(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END && event.player instanceof ServerPlayer player) {
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> {
					if (capability._syncDirty) {
						MineXHunterMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability));
						capability._syncDirty = false;
					}
				});
			}
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			event.getOriginal().getCapability(PLAYER_VARIABLES).ifPresent(original -> {
				event.getEntity().getCapability(PLAYER_VARIABLES).ifPresent(clone -> {
					clone.Nendesbloqueado = original.Nendesbloqueado;
					clone.nentype = original.nentype;
					clone.auramax = original.auramax;
					clone.aura_actual = original.aura_actual;
					clone.TenActive = original.TenActive;
					clone.RenActive = original.RenActive;
					clone.medidor_entrenamiento_corriendo = original.medidor_entrenamiento_corriendo;
					clone.nivel_velocidad_entrenando = original.nivel_velocidad_entrenando;
					clone.limite_velocidad_maxima = original.limite_velocidad_maxima;
					clone.medidor_dano_recibido = original.medidor_dano_recibido;
					clone.vida_extra_entrenada = original.vida_extra_entrenada;
					clone.vida_extra_maxima = original.vida_extra_maxima;
					clone.zoldyck_clan = original.zoldyck_clan;
					clone.unlocked_claws = original.unlocked_claws;
					clone.claws_active = original.claws_active;
					clone.XPTEN = original.XPTEN;
					clone.NivelTen = original.NivelTen;
					clone.unlocked_inmunity = original.unlocked_inmunity;
					if (!event.isWasDeath()) {
					}
				});
			});
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<CompoundTag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("mine_x_hunter", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public CompoundTag serializeNBT() {
			return playerVariables.serializeNBT();
		}

		@Override
		public void deserializeNBT(CompoundTag nbt) {
			playerVariables.deserializeNBT(nbt);
		}
	}

	public static class PlayerVariables implements INBTSerializable<CompoundTag> {
		boolean _syncDirty = false;
		public boolean Nendesbloqueado = false;
		public String nentype = "\"\"";
		public double auramax = 100.0;
		public double aura_actual = 0.0;
		public boolean TenActive = false;
		public boolean RenActive = false;
		public double medidor_entrenamiento_corriendo = 0;
		public double nivel_velocidad_entrenando = 0;
		public double limite_velocidad_maxima = 4.0;
		public double medidor_dano_recibido = 0;
		public double vida_extra_entrenada = 0;
		public double vida_extra_maxima = 20.0;
		public boolean zoldyck_clan = false;
		public boolean unlocked_claws = false;
		public boolean claws_active = false;
		public double XPTEN = 0;
		public double NivelTen = 1.0;
		public boolean unlocked_inmunity = false;

		@Override
		public CompoundTag serializeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putBoolean("Nendesbloqueado", Nendesbloqueado);
			nbt.putString("nentype", nentype);
			nbt.putDouble("auramax", auramax);
			nbt.putDouble("aura_actual", aura_actual);
			nbt.putBoolean("TenActive", TenActive);
			nbt.putBoolean("RenActive", RenActive);
			nbt.putDouble("medidor_entrenamiento_corriendo", medidor_entrenamiento_corriendo);
			nbt.putDouble("nivel_velocidad_entrenando", nivel_velocidad_entrenando);
			nbt.putDouble("limite_velocidad_maxima", limite_velocidad_maxima);
			nbt.putDouble("medidor_dano_recibido", medidor_dano_recibido);
			nbt.putDouble("vida_extra_entrenada", vida_extra_entrenada);
			nbt.putDouble("vida_extra_maxima", vida_extra_maxima);
			nbt.putBoolean("zoldyck_clan", zoldyck_clan);
			nbt.putBoolean("unlocked_claws", unlocked_claws);
			nbt.putBoolean("claws_active", claws_active);
			nbt.putDouble("XPTEN", XPTEN);
			nbt.putDouble("NivelTen", NivelTen);
			nbt.putBoolean("unlocked_inmunity", unlocked_inmunity);
			return nbt;
		}

		@Override
		public void deserializeNBT(CompoundTag nbt) {
			Nendesbloqueado = nbt.getBoolean("Nendesbloqueado");
			nentype = nbt.getString("nentype");
			auramax = nbt.getDouble("auramax");
			aura_actual = nbt.getDouble("aura_actual");
			TenActive = nbt.getBoolean("TenActive");
			RenActive = nbt.getBoolean("RenActive");
			medidor_entrenamiento_corriendo = nbt.getDouble("medidor_entrenamiento_corriendo");
			nivel_velocidad_entrenando = nbt.getDouble("nivel_velocidad_entrenando");
			limite_velocidad_maxima = nbt.getDouble("limite_velocidad_maxima");
			medidor_dano_recibido = nbt.getDouble("medidor_dano_recibido");
			vida_extra_entrenada = nbt.getDouble("vida_extra_entrenada");
			vida_extra_maxima = nbt.getDouble("vida_extra_maxima");
			zoldyck_clan = nbt.getBoolean("zoldyck_clan");
			unlocked_claws = nbt.getBoolean("unlocked_claws");
			claws_active = nbt.getBoolean("claws_active");
			XPTEN = nbt.getDouble("XPTEN");
			NivelTen = nbt.getDouble("NivelTen");
			unlocked_inmunity = nbt.getBoolean("unlocked_inmunity");
		}

		public void markSyncDirty() {
			_syncDirty = true;
		}
	}

	public record PlayerVariablesSyncMessage(PlayerVariables data) {
		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this(new PlayerVariables());
			data.deserializeNBT(buffer.readNbt());
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt(message.data().serializeNBT());
		}

		public static void handleData(final PlayerVariablesSyncMessage message, final Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null)
					Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES).ifPresent(cap -> {
						cap.Nendesbloqueado = message.data().Nendesbloqueado;
						cap.nentype = message.data().nentype;
						cap.auramax = message.data().auramax;
						cap.aura_actual = message.data().aura_actual;
						cap.TenActive = message.data().TenActive;
						cap.RenActive = message.data().RenActive;
						cap.medidor_entrenamiento_corriendo = message.data().medidor_entrenamiento_corriendo;
						cap.nivel_velocidad_entrenando = message.data().nivel_velocidad_entrenando;
						cap.limite_velocidad_maxima = message.data().limite_velocidad_maxima;
						cap.medidor_dano_recibido = message.data().medidor_dano_recibido;
						cap.vida_extra_entrenada = message.data().vida_extra_entrenada;
						cap.vida_extra_maxima = message.data().vida_extra_maxima;
						cap.zoldyck_clan = message.data().zoldyck_clan;
						cap.unlocked_claws = message.data().unlocked_claws;
						cap.claws_active = message.data().claws_active;
						cap.XPTEN = message.data().XPTEN;
						cap.NivelTen = message.data().NivelTen;
						cap.unlocked_inmunity = message.data().unlocked_inmunity;
					});
			});
			context.setPacketHandled(true);
		}
	}
}