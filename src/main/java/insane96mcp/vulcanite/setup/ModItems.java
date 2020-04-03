package insane96mcp.vulcanite.setup;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.item.*;
import insane96mcp.vulcanite.setup.Strings.RegistryNames;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Vulcanite.MOD_ID);

    public static final RegistryObject<Item> VULCANITE_INGOT = ITEMS.register(RegistryNames.VULCANITE_INGOT, () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> VULCANITE_NUGGET = ITEMS.register(RegistryNames.VULCANITE_NUGGET, () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<VulcanitePickaxeItem> VULCANITE_PICKAXE = ITEMS.register(RegistryNames.VULCANITE_PICKAXE, VulcanitePickaxeItem::new);
    public static final RegistryObject<VulcaniteAxeItem> VULCANITE_AXE = ITEMS.register(RegistryNames.VULCANITE_AXE, VulcaniteAxeItem::new);
    public static final RegistryObject<VulcaniteShovelItem> VULCANITE_SHOVEL = ITEMS.register(RegistryNames.VULCANITE_SHOVEL, VulcaniteShovelItem::new);
    public static final RegistryObject<VulcaniteHoeItem> VULCANITE_HOE = ITEMS.register(RegistryNames.VULCANITE_HOE, VulcaniteHoeItem::new);
    public static final RegistryObject<FlintAndVulcaniteItem> FLINT_AND_VULCANITE = ITEMS.register(RegistryNames.FLINT_AND_VULCANITE, FlintAndVulcaniteItem::new);
    public static final RegistryObject<VulcaniteSwordItem> VULCANITE_SWORD = ITEMS.register(RegistryNames.VULCANITE_SWORD, VulcaniteSwordItem::new);

    public static final RegistryObject<VulcaniteArmorItem> VULCANITE_HELMET = ITEMS.register(RegistryNames.VULCANITE_HELMET, () -> new VulcaniteArmorItem(EquipmentSlotType.HEAD));
    public static final RegistryObject<VulcaniteArmorItem> VULCANITE_CHESTPLATE = ITEMS.register(RegistryNames.VULCANITE_CHESTPLATE, () -> new VulcaniteArmorItem(EquipmentSlotType.CHEST));
    public static final RegistryObject<VulcaniteArmorItem> VULCANITE_LEGGINGS = ITEMS.register(RegistryNames.VULCANITE_LEGGINGS, () -> new VulcaniteArmorItem(EquipmentSlotType.LEGS));
    public static final RegistryObject<VulcaniteArmorItem> VULCANITE_BOOTS = ITEMS.register(RegistryNames.VULCANITE_BOOTS, () -> new VulcaniteArmorItem(EquipmentSlotType.FEET));

    public static final RegistryObject<BlockItem> NETHER_VULCANITE_ORE = ITEMS.register(RegistryNames.NETHER_VULCANITE_ORE, () -> new BlockItem(ModBlocks.NETHER_VULCANITE_ORE.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> VULCANITE_BLOCK = ITEMS.register(RegistryNames.VULCANITE_BLOCK, () -> new BlockItem(ModBlocks.VULCANITE_BLOCK.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
}
