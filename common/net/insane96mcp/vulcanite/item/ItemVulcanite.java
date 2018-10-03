package net.insane96mcp.vulcanite.item;

import net.insane96mcp.vulcanite.Vulcanite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemVulcanite extends Item {
	
	public ItemVulcanite(String name, CreativeTabs tab) {
		setRegistryName(name);
		setCreativeTab(tab);
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		return "item." + Vulcanite.RESOURCE_PREFIX + net.insane96mcp.vulcanite.lib.Names.VULCANITE_ITEM;
	}
}
