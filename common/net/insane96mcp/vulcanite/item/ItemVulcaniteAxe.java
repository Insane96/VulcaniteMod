package net.insane96mcp.vulcanite.item;

import java.util.List;

import net.insane96mcp.vulcanite.init.Strings.Tooltips;
import net.insane96mcp.vulcanite.item.materials.ModMaterial;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemVulcaniteAxe extends ItemAxe {
	public ItemVulcaniteAxe(String id) {
		super(ModMaterial.TOOL_VULCANITE, 7f, -3.2f, new Item.Properties().group(ItemGroup.TOOLS));
		
		setRegistryName(id);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation(Tooltips.Tool.smelting));
		tooltip.add(new TextComponentTranslation(Tooltips.Tool.bonusEfficiency));
		tooltip.add(new TextComponentTranslation(Tooltips.Weapon.moreDamage));
	}
}
