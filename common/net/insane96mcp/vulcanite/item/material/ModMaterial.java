package net.insane96mcp.vulcanite.item.material;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterial {
	public static ToolMaterial tool = EnumHelper.addToolMaterial("vulcanite", 3, 406, 5f, 2.5f, 18);
	public static ArmorMaterial armor = EnumHelper.addArmorMaterial("vulcanite", "vulcanite:vulcanite_armor", 22, new int[] {2, 5, 4, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0f);
}