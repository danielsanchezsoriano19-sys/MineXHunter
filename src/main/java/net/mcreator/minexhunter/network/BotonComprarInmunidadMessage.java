package net.mcreator.minexhunter.network;

import net.mcreator.minexhunter.MineXHunterMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BotonComprarInmunidadMessage {

	int type, pressedms;

	public BotonComprarInmunidadMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public BotonComprarInmunidadMessage(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(BotonComprarInmunidadMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(BotonComprarInmunidadMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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

			BotonComprarInmunidadOnKeyPressedProcedure.execute(world, x, y, z, entity);
		}

	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MineXHunterMod.addNetworkMessage(BotonComprarInmunidadMessage.class, BotonComprarInmunidadMessage::buffer, BotonComprarInmunidadMessage::new, BotonComprarInmunidadMessage::handler);
	}

}