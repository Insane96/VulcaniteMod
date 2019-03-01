package net.insane96mcp.vulcanite.init;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.Strings.Names;
import net.insane96mcp.vulcanite.item.ItemFlintAndVulcanite;
import net.insane96mcp.vulcanite.item.ItemVulcaniteAxe;
import net.insane96mcp.vulcanite.item.ItemVulcanitePickaxe;
import net.insane96mcp.vulcanite.item.ItemVulcaniteShovel;
import net.insane96mcp.vulcanite.item.ItemVulcaniteSword;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.registries.ObjectHolder;

public class ModItems {

	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_INGOT)
	public static Item vulcaniteIngot;
	
	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_PICKAXE)
	public static ItemVulcanitePickaxe vulcanitePickaxe;
	
	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_AXE)
	public static ItemVulcaniteAxe vulcaniteAxe;
	
	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_SHOVEL)
	public static ItemVulcaniteShovel vulcaniteShovel;

	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_HOE)
	public static ItemHoe vulcaniteHoe;

	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.FLINT_AND_VULCANITE)
	public static ItemFlintAndVulcanite flintAndVulcanite;
	
	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_SWORD)
	public static ItemVulcaniteSword vulcaniteSword;

	
	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_HELMET)
	public static ItemArmor vulcaniteHelmet;
	
	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_CHESTPLATE)
	public static ItemArmor vulcaniteChestplate;
	
	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_LEGGINGS)
	public static ItemArmor vulcaniteLeggings;
	
	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BOOTS)
	public static ItemArmor vulcaniteBoots;
}
