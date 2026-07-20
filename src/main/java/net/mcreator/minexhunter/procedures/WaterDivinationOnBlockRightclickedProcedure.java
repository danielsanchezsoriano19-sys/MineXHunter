package net.mcreator.minexhunter.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.minexhunter.network.MineXHunterModVariables;

public class WaterDivinationOnBlockRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double prob_local = 0;
		if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "fase") instanceof IntegerProperty _getip1 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip1) : -1) == 0) {
			prob_local = Math.random() * 100;
		}
		if (prob_local <= 0.033) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.TipoHatsu = "Specialization";
					capability.markSyncDirty();
				});
			}
			{
				int _value = 6;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("fase") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
			if (world instanceof Level _level)
				_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		}
		if (prob_local > 0.033 && prob_local <= 27.033) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.TipoHatsu = "Enhancement";
					capability.markSyncDirty();
				});
			}
			{
				int _value = 1;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("fase") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
			if (world instanceof Level _level)
				_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		}
		if (prob_local > 27.033 && prob_local <= 46.033) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.TipoHatsu = "Transmutation";
					capability.markSyncDirty();
				});
			}
			{
				int _value = 2;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("fase") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
			if (world instanceof Level _level)
				_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		}
		if (prob_local > 46.033 && prob_local <= 70.033) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.TipoHatsu = "Emission";
					capability.markSyncDirty();
				});
			}
			{
				int _value = 3;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("fase") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
			if (world instanceof Level _level)
				_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		}
		if (prob_local > 70.033 && prob_local <= 85.033) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.TipoHatsu = "Conjuration";
					capability.markSyncDirty();
				});
			}
			{
				int _value = 4;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("fase") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
			if (world instanceof Level _level)
				_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		}
		if (prob_local > 85.033 && prob_local <= 100) {
			{
				entity.getCapability(MineXHunterModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.TipoHatsu = "Manipulation";
					capability.markSyncDirty();
				});
			}
			{
				int _value = 5;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("fase") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
			if (world instanceof Level _level)
				_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		}
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