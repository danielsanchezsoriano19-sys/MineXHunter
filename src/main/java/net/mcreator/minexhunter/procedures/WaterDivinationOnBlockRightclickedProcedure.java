package net.mcreator.minexhunter.procedures;

import net.minecraftforge.eventbus.api.Event;

public class WaterDivinationOnBlockRightclickedProcedure {
public static void execute(
LevelAccessor world,
double x,
double y,
double z ) {
if ((getPropertyByName((world.getBlockState(BlockPos.containing(x,y,z))), "fase") instanceof IntegerProperty _getip1 ? (world.getBlockState(BlockPos.containing(x,y,z))).getValue(_getip1) : -1)==0) {if (<=26.967) {}}
}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}