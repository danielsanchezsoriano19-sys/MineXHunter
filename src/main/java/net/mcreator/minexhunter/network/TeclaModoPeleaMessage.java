package net.mcreator.minexhunter.network;

import net.mcreator.minexhunter.MineXHunterMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TeclaModoPeleaMessage {

	int type, pressedms;

	public TeclaModoPeleaMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public TeclaModoPeleaMessage(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(TeclaModoPeleaMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(TeclaModoPeleaMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			pressAction(context.getSender(), message.type, message.pressedms);
		});
		context.setPacketHandled(true);
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		// security measure to prevent arbitrary chunk generation
		if (!world.getChunkSource().hasChunk(SectionPos.blockToSectionCoord(x), SectionPos.blockToSectionCoord(z)))
			return;

		if (type == 0) {

			TeclaModoPeleaOnKeyPressedProcedure.execute(world, x, y, z);
		}

	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MineXHunterMod.addNetworkMessage(TeclaModoPeleaMessage.class, TeclaModoPeleaMessage::buffer, TeclaModoPeleaMessage::new, TeclaModoPeleaMessage::handler);
	}

}