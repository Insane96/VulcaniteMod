package insane96mcp.vulcanite.setup;

import com.zimonishim.ziheasymodding.modItems.item.ZIHItem;
import com.zimonishim.ziheasymodding.util.KeyBoardHandler;
import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.item.FlintAndVulcaniteItem;
import insane96mcp.vulcanite.item.materials.ModMaterial;
import insane96mcp.vulcanite.setup.Strings.RegistryNames;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Vulcanite.MOD_ID);

    public static final RegistryObject<Item> VULCANITE_INGOT = ITEMS.register(RegistryNames.VULCANITE_INGOT, () -> new ZIHItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).fireResistant()));
    public static final RegistryObject<Item> VULCANITE_NUGGET = ITEMS.register(RegistryNames.VULCANITE_NUGGET, () -> new ZIHItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).fireResistant()));
    public static final RegistryObject<PickaxeItem> VULCANITE_PICKAXE = ITEMS.register(RegistryNames.VULCANITE_PICKAXE, () -> new PickaxeItem(ModMaterial.TOOL_VULCANITE, 1, -2.8f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).fireResistant()) {
        @Override
        public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            if (KeyBoardHandler.isHoldingShift()){
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Tool.smelting)));
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Tool.bonusEfficiency)));
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Weapon.moreDamage)));
            } else {
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.HoldShiftForMoreInfo.holdShiftForMoreInfo)));
            }
        }
    });
    public static final RegistryObject<AxeItem> VULCANITE_AXE = ITEMS.register(RegistryNames.VULCANITE_AXE, () -> new AxeItem(ModMaterial.TOOL_VULCANITE, 6f, -3.1f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).fireResistant()) {
        @Override
        public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            if (KeyBoardHandler.isHoldingShift()){
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Tool.smelting)));
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Tool.bonusEfficiency)));
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Weapon.moreDamage)));
            } else {
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.HoldShiftForMoreInfo.holdShiftForMoreInfo)));
            }
        }
    });
    public static final RegistryObject<ShovelItem> VULCANITE_SHOVEL = ITEMS.register(RegistryNames.VULCANITE_SHOVEL, () -> new ShovelItem(ModMaterial.TOOL_VULCANITE, 1.5f, -3f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).fireResistant()) {
        @Override
        public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            if (KeyBoardHandler.isHoldingShift()){
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Tool.smelting)));
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Tool.bonusEfficiency)));
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Weapon.moreDamage)));
            } else {
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.HoldShiftForMoreInfo.holdShiftForMoreInfo)));
            }
        }
    });
    public static final RegistryObject<HoeItem> VULCANITE_HOE = ITEMS.register(RegistryNames.VULCANITE_HOE, () -> new HoeItem(ModMaterial.TOOL_VULCANITE, 0,-1.0f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).fireResistant()));
    public static final RegistryObject<FlintAndVulcaniteItem> FLINT_AND_VULCANITE = ITEMS.register(RegistryNames.FLINT_AND_VULCANITE, FlintAndVulcaniteItem::new);
    public static final RegistryObject<SwordItem> VULCANITE_SWORD = ITEMS.register(RegistryNames.VULCANITE_SWORD, () -> new SwordItem(ModMaterial.TOOL_VULCANITE, 3, -2.4f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()) {
        @Override
        public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            if (KeyBoardHandler.isHoldingShift()){
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Weapon.moreDamage)));
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Weapon.moreDamageFireAspect)));
            } else {
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.HoldShiftForMoreInfo.holdShiftForMoreInfo)));
            }
        }
    });

    public static final RegistryObject<ArmorItem> VULCANITE_HELMET = ITEMS.register(RegistryNames.VULCANITE_HELMET, () -> new ArmorItem(ModMaterial.ARMOR_VULCANITE, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()) {
        @Override
        public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            if (KeyBoardHandler.isHoldingShift()){
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Armor.damageReduction)));
            } else {
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.HoldShiftForMoreInfo.holdShiftForMoreInfo)));
            }
        }
    });
    public static final RegistryObject<ArmorItem> VULCANITE_CHESTPLATE = ITEMS.register(RegistryNames.VULCANITE_CHESTPLATE, () -> new ArmorItem(ModMaterial.ARMOR_VULCANITE, EquipmentSlot.CHEST, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()) {
        @Override
        public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            if (KeyBoardHandler.isHoldingShift()){
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Armor.damageReduction)));
            } else {
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.HoldShiftForMoreInfo.holdShiftForMoreInfo)));
            }
        }
    });
    public static final RegistryObject<ArmorItem> VULCANITE_LEGGINGS = ITEMS.register(RegistryNames.VULCANITE_LEGGINGS, () -> new ArmorItem(ModMaterial.ARMOR_VULCANITE, EquipmentSlot.LEGS, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()) {
        @Override
        public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            if (KeyBoardHandler.isHoldingShift()){
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Armor.damageReduction)));
            } else {
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.HoldShiftForMoreInfo.holdShiftForMoreInfo)));
            }
        }
    });
    public static final RegistryObject<ArmorItem> VULCANITE_BOOTS = ITEMS.register(RegistryNames.VULCANITE_BOOTS, () -> new ArmorItem(ModMaterial.ARMOR_VULCANITE, EquipmentSlot.FEET, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()) {
        @Override
        public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            if (KeyBoardHandler.isHoldingShift()){
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.Armor.damageReduction)));
            } else {
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.HoldShiftForMoreInfo.holdShiftForMoreInfo)));
            }
        }
    });

    public static final RegistryObject<BlockItem> NETHER_VULCANITE_ORE = ITEMS.register(RegistryNames.NETHER_VULCANITE_ORE, () -> new BlockItem(ModBlocks.NETHER_VULCANITE_ORE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS))); // NOTE: Not immune to fire!
    public static final RegistryObject<BlockItem> VULCANITE_BLOCK = ITEMS.register(RegistryNames.VULCANITE_BLOCK, () -> new BlockItem(ModBlocks.VULCANITE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS).fireResistant()) {
        @Override
        public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            if (KeyBoardHandler.isHoldingShift()){
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.VulcaniteBlock.setOnFire)));
            } else {
                tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.HoldShiftForMoreInfo.holdShiftForMoreInfo)));
            }
        }
    });
}