package insane96mcp.vulcanite.item;

import insane96mcp.vulcanite.item.materials.ModMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;

public class VulcaniteShovelItem extends ShovelItem {
	public VulcaniteShovelItem() {
		super(ModMaterial.TOOL_VULCANITE, 1.5f, -3f, new Item.Properties().group(ItemGroup.TOOLS));
	}
}
