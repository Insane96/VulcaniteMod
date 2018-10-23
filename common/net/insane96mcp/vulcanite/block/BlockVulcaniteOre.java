package net.insane96mcp.vulcanite.block;

import java.util.Random;

import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.lib.Properties;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVulcaniteOre extends BlockOre{

	public BlockVulcaniteOre() {
		super();
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.vulcaniteNuggetItem;
	}

	@Override
	public int quantityDropped(Random random) {
		return MathHelper.getInt(random, Properties.config.oreGeneration.overworld.minNuggetDrop, Properties.config.oreGeneration.overworld.maxNuggetDrop);
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		if (fortune > 0) {
			int maxNugget = Properties.config.oreGeneration.overworld.maxNuggetDrop + fortune;
			return MathHelper.getInt(random, Properties.config.oreGeneration.overworld.minNuggetDrop, maxNugget);
		}
		else {
			return this.quantityDropped(random);
		}
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World)world).rand : new Random();
		return MathHelper.getInt(rand, 1, 3);
	}
}
