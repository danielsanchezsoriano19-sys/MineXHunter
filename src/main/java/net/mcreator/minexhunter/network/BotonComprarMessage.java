package net.mcreator.minexhunter.network;

import net.mcreator.minexhunter.MineXHunterMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BotonComprarMessage {

	int type, pressedms;

	public BotonComprarMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public BotonComprarMessage(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(BotonComprarMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(BotonComprarMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MineXHunterMod.addNetworkMessage(BotonComprarMessage.class, BotonComprarMessage::buffer, BotonComprarMessage::new, BotonComprarMessage::handler);
	}

}