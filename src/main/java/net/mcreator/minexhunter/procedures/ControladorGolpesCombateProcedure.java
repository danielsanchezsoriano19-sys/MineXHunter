package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ControladorGolpesCombateProcedure {
	@SubscribeEvent
	public static void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
		MineXHunterMod.PACKET_HANDLER.sendToServer(new ControladorGolpesCombateMessage());
		execute(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
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
				execute(context.getSender().level(), context.getSender().getX(), context.getSender().getY(), context.getSender().getZ(), context.getSender());
			});
			context.setPacketHandled(true);
		}

		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			MineXHunterMod.addNetworkMessage(ControladorGolpesCombateMessage.class, ControladorGolpesCombateMessage::buffer, ControladorGolpesCombateMessage::new, ControladorGolpesCombateMessage::handler);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

private static void execute(
@Nullable Event event,
LevelAccessor world,
double x,
double y,
double z,
Entity entity ) {
if (
entity == null ) return ;
if (==true) {if (==1) {if (world instanceof ServerLevel _level)
_level.getServer().getCommands().performPrefixedCommand(
new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO,
_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "playeranimator play animation.player.punch");}else{if (world instanceof ServerLevel _level)
_level.getServer().getCommands().performPrefixedCommand(
new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO,
_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "playeranimator play animation.player.punch2");}if (entity instanceof LivingEntity) {entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 4);}}
}
}