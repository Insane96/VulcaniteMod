package net.insane96mcp.vulcanite.item;

import java.util.List;

import net.insane96mcp.vulcanite.init.Strings.Tooltips;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemVulcaniteShovel extends ItemSpade {
	public ItemVulcaniteShovel(IItemTier tier, float attackDamage, float attackSpeed, Item.Properties builder) {
		super(tier, attackDamage, attackSpeed, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation(Tooltips.Tool.smelting));
		/*tooltip.add(I18n.format(Tooltips.Tool.bonusEfficiency));
		tooltip.add(I18n.format(Tooltips.Weapon.moreDamage));*/
	}
}
