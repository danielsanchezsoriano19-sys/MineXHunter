package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SetupanimationsProcedure {
	@SubscribeEvent
	public static void init(FMLClientSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
	}
}