package net.insane96mcp.vulcanite.item;

import java.util.List;

import javax.annotation.Nullable;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.lib.Names;
import net.insane96mcp.vulcanite.lib.Properties;
import net.insane96mcp.vulcanite.lib.Tooltips;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFlintAndVulcanite extends ItemFlintAndSteel{
	public ItemFlintAndVulcanite(String name, ToolMaterial material, CreativeTabs tab) {
		this.setMaxDamage(Properties.Tools.FlintAndVulcanite.maxUses);
		this.setCreativeTab(tab);
		setRegistryName(name);
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		return "item." + Vulcanite.RESOURCE_PREFIX + Names.FLINT_AND_VULCANITE;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (GuiScreen.isShiftKeyDown() && Properties.General.showMoreInfo) {
			tooltip.add(I18n.format(Tooltips.FlintAndVulcanite.adv_setOnFire, Properties.Tools.FlintAndVulcanite.secondsOnFire));
			tooltip.add(I18n.format(Tooltips.FlintAndVulcanite.adv_damageOnUse, Properties.Tools.FlintAndVulcanite.damageOnUse));
		}
		else {
			tooltip.add(I18n.format(Tooltips.FlintAndVulcanite.base_setOnFire));
			if (Properties.General.showMoreInfo)
				tooltip.add(I18n.format(Tooltips.General.shiftForMore));
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.getBlockState(pos).getBlock() == Blocks.TNT) {
			if (!worldIn.isRemote)
			{
		        EntityTNTPrimed entityTNTPrimed = new EntityTNTPrimed(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), player);
		        if (Properties.Tools.FlintAndVulcanite.tntIgnitesFaster)
		        	entityTNTPrimed.setFuse(40);
		        worldIn.spawnEntity(entityTNTPrimed);
                worldIn.playSound((EntityPlayer)null, entityTNTPrimed.posX, entityTNTPrimed.posY, entityTNTPrimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
	        }
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            player.getHeldItem(hand).damageItem(1, player);
            player.swingArm(hand);

            return EnumActionResult.SUCCESS;
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
