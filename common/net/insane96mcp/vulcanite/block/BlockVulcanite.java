package net.insane96mcp.vulcanite.block;

import java.util.List;

import javax.annotation.Nullable;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.lib.Names;
import net.insane96mcp.vulcanite.lib.Properties;
import net.insane96mcp.vulcanite.lib.Tooltips;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVulcanite extends Block{

	public BlockVulcanite() {
		super(Material.IRON);
		
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
		if (Properties.General.vulcaniteBlockTimeOnFire == 0)
			return;
		
		entityIn.setFire(Properties.General.vulcaniteBlockTimeOnFire);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (Properties.General.vulcaniteBlockTimeOnFire == 0)
			return;
		
		if (GuiScreen.isShiftKeyDown() && Properties.General.showMoreInfo) {
			tooltip.add(I18n.format(Tooltips.VulcaniteBlock.adv_setOnFire, Properties.General.vulcaniteBlockTimeOnFire));
		}
		else {
			tooltip.add(I18n.format(Tooltips.VulcaniteBlock.base_setOnFire));
			if (Properties.General.showMoreInfo)
				tooltip.add(I18n.format(Tooltips.General.shiftForMore));
		}
	}
}
