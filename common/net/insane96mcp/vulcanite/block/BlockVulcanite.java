package net.insane96mcp.vulcanite.block;

import java.util.List;

import net.insane96mcp.vulcanite.init.ModConfig;
import net.insane96mcp.vulcanite.init.Strings.Tooltips;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockVulcanite extends Block{

	public BlockVulcanite(String id) {
		super(Block.Properties.create(Material.IRON, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(6.25f, 7.5f).sound(SoundType.METAL));
		
		setRegistryName(id);
	}

	@Override
	public boolean isBeaconBase(IBlockState state, IWorldReader world, BlockPos pos, BlockPos beacon) {
		return true;
	}
	
	@Override
	public boolean isFireSource(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing side) {
		return true;
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (ModConfig.Misc.vulcaniteBlockTimeOnFire.get() == 0)
			return;
		
		entityIn.setFire(ModConfig.Misc.vulcaniteBlockTimeOnFire.get());
	}
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		if (ModConfig.Misc.vulcaniteBlockTimeOnFire.get() == 0)
			return;
		

		tooltip.add(new TextComponentTranslation(Tooltips.VulcaniteBlock.setOnFire));
	}
	
	@Override
	public int getHarvestLevel(IBlockState state) {
		return 2;
	}
	
	@Override
	public ToolType getHarvestTool(IBlockState state) {
		return ToolType.PICKAXE;
	}
}