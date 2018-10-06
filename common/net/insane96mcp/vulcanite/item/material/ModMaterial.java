package net.insane96mcp.vulcanite.item.material;

import net.insane96mcp.vulcanite.lib.Properties;
import net.insane96mcp.vulcanite.lib.Properties.Armor;
import net.insane96mcp.vulcanite.lib.Properties.ToolsAndWeapons;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterial {
	public static ToolMaterial tool = EnumHelper.addToolMaterial("vulcanite", Properties.ToolsAndWeapons.MaterialStats.harvestLevel, Properties.ToolsAndWeapons.MaterialStats.maxUses, Properties.ToolsAndWeapons.MaterialStats.efficency, Properties.ToolsAndWeapons.MaterialStats.damage, Properties.ToolsAndWeapons.MaterialStats.enchantability);
	public static ArmorMaterial armor = EnumHelper.addArmorMaterial("vulcanite", "vulcanite:vulcanite_armor", Properties.Armor.baseDurability, Properties.Armor.armorPoints, Properties.Armor.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_IRON, Properties.Armor.toughness);
}