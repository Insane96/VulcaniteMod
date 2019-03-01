package net.insane96mcp.vulcanite.item;

import java.util.List;
import java.util.Map;

import net.insane96mcp.vulcanite.init.Strings.Tooltips;
import net.insane96mcp.vulcanite.item.materials.ModMaterial;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemVulcaniteArmor extends ItemArmor{

	public ItemVulcaniteArmor(EntityEquipmentSlot slot, String id) {
		super(ModMaterial.ARMOR_VULCANITE, slot, new Properties().group(ItemGroup.COMBAT));
		
		setRegistryName(id);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation(Tooltips.Armor.damageReduction));
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if (enchantment.equals(Enchantments.FIRE_PROTECTION))
			return false;
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(book);
		
		if (enchantments.containsKey(Enchantments.FIRE_PROTECTION))
			return false;

		return super.isBookEnchantable(stack, book);
	}
}
