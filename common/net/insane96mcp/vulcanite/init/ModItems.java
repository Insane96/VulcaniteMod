package net.insane96mcp.vulcanite.init;

import java.util.ArrayList;

import net.insane96mcp.vulcanite.item.ItemFlintAndVulcanite;
import net.insane96mcp.vulcanite.item.ItemVulcanite;
import net.insane96mcp.vulcanite.item.ItemVulcaniteArmor;
import net.insane96mcp.vulcanite.item.ItemVulcaniteAxe;
import net.insane96mcp.vulcanite.item.ItemVulcaniteHoe;
import net.insane96mcp.vulcanite.item.ItemVulcanitePickaxe;
import net.insane96mcp.vulcanite.item.ItemVulcaniteShovel;
import net.insane96mcp.vulcanite.item.ItemVulcaniteSword;
import net.insane96mcp.vulcanite.item.material.ModMaterial;
import net.insane96mcp.vulcanite.lib.Names;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {
	public static ItemVulcanite vulcaniteItem;
	
	public static ItemVulcanitePickaxe vulcanitePickaxeItem;
	public static ItemVulcaniteShovel vulcaniteShovelItem;
	public static ItemVulcaniteAxe vulcaniteAxeItem;
	public static ItemVulcaniteHoe vulcaniteHoeItem;
	public static ItemVulcaniteSword vulcaniteSwordItem;
	
	public static ItemFlintAndVulcanite flintAndVulcaniteItem;

	public static ItemVulcaniteArmor vulcaniteHelmetItem;
	public static ItemVulcaniteArmor vulcaniteChestplateItem;
	public static ItemVulcaniteArmor vulcaniteLeggingsItem;
	public static ItemVulcaniteArmor vulcaniteBootsItem;
	
	public static ArrayList<Item> ITEMS = new ArrayList<Item>();
	
	public static void PreInit() {
		vulcaniteItem = new ItemVulcanite(Names.VULCANITE_ITEM, CreativeTabs.MATERIALS);
		ITEMS.add(vulcaniteItem);

		vulcanitePickaxeItem = new ItemVulcanitePickaxe(Names.VULCANITE_PICKAXE, ModMaterial.tool, CreativeTabs.TOOLS);
		ITEMS.add(vulcanitePickaxeItem);

		vulcaniteShovelItem = new ItemVulcaniteShovel(Names.VULCANITE_SHOVEL, ModMaterial.tool, CreativeTabs.TOOLS);
		ITEMS.add(vulcaniteShovelItem);

		vulcaniteAxeItem = new ItemVulcaniteAxe(Names.VULCANITE_AXE, ModMaterial.tool, CreativeTabs.TOOLS);
		ITEMS.add(vulcaniteAxeItem);
		
		vulcaniteHoeItem = new ItemVulcaniteHoe(Names.VULCANITE_HOE, ModMaterial.tool, CreativeTabs.TOOLS);
		ITEMS.add(vulcaniteHoeItem);
		
		vulcaniteSwordItem = new ItemVulcaniteSword(Names.VULCANITE_SWORD, ModMaterial.tool, CreativeTabs.COMBAT);
		ITEMS.add(vulcaniteSwordItem);
		
		flintAndVulcaniteItem = new ItemFlintAndVulcanite(Names.FLINT_AND_VULCANITE, ModMaterial.tool, CreativeTabs.TOOLS);
		ITEMS.add(flintAndVulcaniteItem);

		vulcaniteHelmetItem = new ItemVulcaniteArmor(Names.VULCANITE_HELMET, ModMaterial.armor, 0, EntityEquipmentSlot.HEAD);
		ITEMS.add(vulcaniteHelmetItem);

		vulcaniteChestplateItem = new ItemVulcaniteArmor(Names.VULCANITE_CHESTPLATE, ModMaterial.armor, 0, EntityEquipmentSlot.CHEST);
		ITEMS.add(vulcaniteChestplateItem);

		vulcaniteLeggingsItem = new ItemVulcaniteArmor(Names.VULCANITE_LEGGINGS, ModMaterial.armor, 1, EntityEquipmentSlot.LEGS);
		ITEMS.add(vulcaniteLeggingsItem);

		vulcaniteBootsItem = new ItemVulcaniteArmor(Names.VULCANITE_BOOTS, ModMaterial.armor, 0, EntityEquipmentSlot.FEET);
		ITEMS.add(vulcaniteBootsItem);
	}
	
	public static void Init() {
		GameRegistry.addSmelting(ModBlocks.vulcaniteOre, new ItemStack(vulcaniteItem), 3.0f);
		
		OreDictionary.registerOre("gemVulcanite", vulcaniteItem);
	}
}
