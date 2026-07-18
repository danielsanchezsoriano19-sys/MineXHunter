/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minexhunter.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.minexhunter.block.WaterDivinationBlock;
import net.mcreator.minexhunter.MineXHunterMod;

public class MineXHunterModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MineXHunterMod.MODID);
	public static final RegistryObject<Block> WATER_DIVINATION;
	static {
		WATER_DIVINATION = REGISTRY.register("water_divination", WaterDivinationBlock::new);
	}
	// Start of user code block custom blocks
	// End of user code block custom blocks
}