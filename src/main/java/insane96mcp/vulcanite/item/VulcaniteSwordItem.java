package insane96mcp.vulcanite.item;

import insane96mcp.vulcanite.item.materials.ModMaterial;
import insane96mcp.vulcanite.setup.Strings.Translatable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class VulcaniteSwordItem extends SwordItem {

	public VulcaniteSwordItem() {
		super(ModMaterial.TOOL_VULCANITE, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent(Translatable.Weapon.moreDamage));
		tooltip.add(new TranslationTextComponent(Translatable.Weapon.moreDamageFireAspect));
	}
}