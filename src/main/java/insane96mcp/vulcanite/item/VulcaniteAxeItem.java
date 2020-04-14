package insane96mcp.vulcanite.item;

import insane96mcp.vulcanite.item.materials.ModMaterial;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class VulcaniteAxeItem extends AxeItem {
	public VulcaniteAxeItem() {
		super(ModMaterial.TOOL_VULCANITE, 6f, -3.1f, new Item.Properties().group(ItemGroup.TOOLS));
	}
}
