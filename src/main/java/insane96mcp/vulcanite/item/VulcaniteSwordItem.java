package insane96mcp.vulcanite.item;

import insane96mcp.vulcanite.item.materials.ModMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public class VulcaniteSwordItem extends SwordItem {

	public VulcaniteSwordItem() {
		super(ModMaterial.TOOL_VULCANITE, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT));
	}
}