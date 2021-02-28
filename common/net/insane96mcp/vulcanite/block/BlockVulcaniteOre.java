package net.insane96mcp.vulcanite.block;

import java.util.Random;

import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.lib.Properties;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVulcaniteOre extends BlockOre{

	//TODO: Update this!
	public BlockVulcaniteOre() {
		super();
		this.blockHardness = 3f;
		this.blockResistance = 3;
	}

	//Updated, but used EntityLivingBase instead of LivingEntity. TEST!
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase) entityIn)) {
			entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
		}
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

	//Functionality perfectly copied from 1.15.
	@Override
	public int getHarvestLevel(IBlockState state) {
		return 2;
	}


	//TODO: Update this?
//	@Override
//	public String getHarvestTool(IBlockState state) {
//		return super.getHarvestTool(state);
//	}
}
