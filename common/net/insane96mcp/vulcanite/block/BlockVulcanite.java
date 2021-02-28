package net.insane96mcp.vulcanite.block;

import java.util.List;

import javax.annotation.Nullable;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.lib.Properties;
import net.insane96mcp.vulcanite.lib.Strings.Names;
import net.insane96mcp.vulcanite.lib.Strings.Tooltips;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVulcanite extends Block{

	//Updated everything but MaterialColor
	public BlockVulcanite() {
		super(Material.IRON);
		this.blockHardness = 5f;
		this.blockResistance = 6f;
		this.blockSoundType = SoundType.METAL;
	}

	@Override
	public String getTranslationKey() {
		return "tile." + Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BLOCK;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return true;
	}

	@Override
	public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (Properties.config.vulcaniteBlockTimeOnFire == 0)
			return;

		entityIn.setFire(Properties.config.vulcaniteBlockTimeOnFire);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (Properties.config.vulcaniteBlockTimeOnFire == 0)
			return;

		tooltip.add(I18n.format(Tooltips.VulcaniteBlock.setOnFire));
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
