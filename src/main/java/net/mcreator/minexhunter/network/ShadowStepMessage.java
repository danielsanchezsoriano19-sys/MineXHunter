package net.mcreator.minexhunter.network;

import net.mcreator.minexhunter.MineXHunterMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShadowStepMessage {

	int type, pressedms;

	public ShadowStepMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public ShadowStepMessage(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(ShadowStepMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(ShadowStepMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MineXHunterMod.addNetworkMessage(ShadowStepMessage.class, ShadowStepMessage::buffer, ShadowStepMessage::new, ShadowStepMessage::handler);
	}

}