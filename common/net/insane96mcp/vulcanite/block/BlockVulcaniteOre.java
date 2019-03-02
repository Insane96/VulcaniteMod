package net.insane96mcp.vulcanite.block;

import net.insane96mcp.vulcanite.init.ModConfig;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockVulcaniteOre extends BlockOre {

	public BlockVulcaniteOre(String id) {
		super(Properties.create(Material.ROCK).hardnessAndResistance(6.0f, 10));

		setRegistryName(id);
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (ModConfig.Misc.vulcaniteBlockTimeOnFire.get() == 0)
			return;
		
		entityIn.setFire(ModConfig.Misc.vulcaniteBlockTimeOnFire.get() / 2);
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
