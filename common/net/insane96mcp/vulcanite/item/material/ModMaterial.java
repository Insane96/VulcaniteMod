package net.insane96mcp.vulcanite.item.material;

import net.insane96mcp.vulcanite.lib.Properties;
import net.insane96mcp.vulcanite.lib.Properties.Armor;
import net.insane96mcp.vulcanite.lib.Properties.Tools;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterial {
	public static ToolMaterial tool = EnumHelper.addToolMaterial("vulcanite", Properties.Tools.Base.harvestLevel, Properties.Tools.Base.maxUses, Properties.Tools.Base.efficency, Properties.Tools.Base.damage, Properties.Tools.Base.enchantability);
	public static ArmorMaterial armor = EnumHelper.addArmorMaterial("vulcanite", "vulcanite:vulcanite_armor", Properties.Armor.baseDurability, Properties.Armor.armorPoints, Properties.Armor.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_IRON, Properties.Armor.toughness);
}