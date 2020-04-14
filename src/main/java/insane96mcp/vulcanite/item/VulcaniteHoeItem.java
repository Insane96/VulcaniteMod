package insane96mcp.vulcanite.item;

import insane96mcp.vulcanite.item.materials.ModMaterial;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class VulcaniteHoeItem extends HoeItem {
	public VulcaniteHoeItem() {
		super(ModMaterial.TOOL_VULCANITE, -1.0f, new Item.Properties().group(ItemGroup.TOOLS));
	}
}
