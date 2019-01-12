package net.insane96mcp.vulcanite.item;

import java.util.List;

import javax.annotation.Nullable;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.lib.Strings.Names;
import net.insane96mcp.vulcanite.lib.Strings.Tooltips;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLootBonus;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemVulcaniteAxe extends ItemAxe{
	public ItemVulcaniteAxe(String name, ToolMaterial material, CreativeTabs tab) {
		super(material, 7f, -3.2f);
		setRegistryName(name);
		setCreativeTab(tab);
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		return "item." + Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_AXE;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(Tooltips.Tool.base_smelting));
		tooltip.add(I18n.format(Tooltips.Weapon.base_moreDamage));
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return ItemStack.areItemsEqualIgnoreDurability(repair, new ItemStack(ModItems.vulcaniteIngotItem)) ? true : super.getIsRepairable(toRepair, repair);
  	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if (enchantment instanceof EnchantmentLootBonus) return false;
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}
}
