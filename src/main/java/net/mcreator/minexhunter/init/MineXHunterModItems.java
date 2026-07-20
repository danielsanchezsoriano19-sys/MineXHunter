/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minexhunter.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.minexhunter.item.NacimientodenenItem;
import net.mcreator.minexhunter.item.ContratoSangreZoldyckItem;
import net.mcreator.minexhunter.item.BettlePhoneItemItem;
import net.mcreator.minexhunter.MineXHunterMod;

public class MineXHunterModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MineXHunterMod.MODID);
	public static final RegistryObject<Item> AWAKENING_OF_NEN;
	public static final RegistryObject<Item> CONTRATO_SANGRE_ZOLDYCK;
	public static final RegistryObject<Item> WATER_DIVINATION;
	public static final RegistryObject<Item> BETTLE_PHONE_ITEM;
	static {
		AWAKENING_OF_NEN = REGISTRY.register("awakening_of_nen", NacimientodenenItem::new);
		CONTRATO_SANGRE_ZOLDYCK = REGISTRY.register("contrato_sangre_zoldyck", ContratoSangreZoldyckItem::new);
		WATER_DIVINATION = block(MineXHunterModBlocks.WATER_DIVINATION);
		BETTLE_PHONE_ITEM = REGISTRY.register("bettle_phone_item", BettlePhoneItemItem::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return block(block, new Item.Properties());
	}

	private static RegistryObject<Item> block(RegistryObject<Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}