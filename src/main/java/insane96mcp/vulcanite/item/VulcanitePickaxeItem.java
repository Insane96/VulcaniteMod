package insane96mcp.vulcanite.item;

import insane96mcp.vulcanite.item.materials.ModMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;

public class VulcanitePickaxeItem extends PickaxeItem {
	public VulcanitePickaxeItem() {
		super(ModMaterial.TOOL_VULCANITE, 1, -2.8f, new Item.Properties().group(ItemGroup.TOOLS));
	}
}
