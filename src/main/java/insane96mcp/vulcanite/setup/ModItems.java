package insane96mcp.vulcanite.setup;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.item.*;
import insane96mcp.vulcanite.setup.Strings.Names;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ModItems {

    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_INGOT)
    public static Item vulcaniteIngot;

    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_PICKAXE)
    public static ItemVulcanitePickaxe vulcanitePickaxe;

    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_AXE)
    public static ItemVulcaniteAxe vulcaniteAxe;

    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_SHOVEL)
    public static ItemVulcaniteShovel vulcaniteShovel;

    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_HOE)
    public static HoeItem vulcaniteHoe;

    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.FLINT_AND_VULCANITE)
    public static ItemFlintAndVulcanite flintAndVulcanite;

    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_SWORD)
    public static ItemVulcaniteSword vulcaniteSword;


    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_HELMET)
    public static ArmorItem vulcaniteHelmet;

    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_CHESTPLATE)
    public static ArmorItem vulcaniteChestplate;

    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_LEGGINGS)
    public static ArmorItem vulcaniteLeggings;

    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BOOTS)
    public static ArmorItem vulcaniteBoots;
}
