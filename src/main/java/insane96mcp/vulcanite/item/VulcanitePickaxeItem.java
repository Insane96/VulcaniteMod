package insane96mcp.vulcanite.item;

import insane96mcp.vulcanite.item.materials.ModMaterial;
import insane96mcp.vulcanite.setup.Strings.Translatable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class VulcanitePickaxeItem extends PickaxeItem {
	public VulcanitePickaxeItem() {
		super(ModMaterial.TOOL_VULCANITE, 1, -2.8f, new Item.Properties().group(ItemGroup.TOOLS));
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent(Translatable.Tool.smelting));
		tooltip.add(new TranslationTextComponent(Translatable.Tool.bonusEfficiency));
		tooltip.add(new TranslationTextComponent(Translatable.Weapon.moreDamage));
	}
}
