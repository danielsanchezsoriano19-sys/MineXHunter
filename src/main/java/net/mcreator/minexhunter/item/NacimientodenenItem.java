package net.mcreator.minexhunter.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.minexhunter.procedures.NacimientodenenAlPresionarClickDerechoEnElAireProcedure;

public class NacimientodenenItem extends Item {
	public NacimientodenenItem() {
		super(new Item.Properties().rarity(Rarity.EPIC));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		NacimientodenenAlPresionarClickDerechoEnElAireProcedure.execute(entity);
		return ar;
	}
}