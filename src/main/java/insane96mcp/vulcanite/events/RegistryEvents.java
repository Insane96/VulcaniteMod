package insane96mcp.vulcanite.events;


import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.block.BlockVulcanite;
import insane96mcp.vulcanite.block.BlockVulcaniteOre;
import insane96mcp.vulcanite.item.*;
import insane96mcp.vulcanite.setup.ModBlocks;
import insane96mcp.vulcanite.setup.Strings.Names;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static insane96mcp.vulcanite.item.materials.ModMaterial.TOOL_VULCANITE;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        blockRegistryEvent.getRegistry().registerAll(
                new BlockVulcanite(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BLOCK),
                new BlockVulcaniteOre(Vulcanite.RESOURCE_PREFIX + Names.NETHER_VULCANITE_ORE)
        );
    }

    @SubscribeEvent
    public static void OnItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {

        itemRegistryEvent.getRegistry().registerAll(
                new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(Vulcanite.MOD_ID, Names.VULCANITE_INGOT),
                new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(Vulcanite.MOD_ID, Names.VULCANITE_NUGGET),
                new ItemVulcanitePickaxe(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_PICKAXE),
                new ItemVulcaniteAxe(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_AXE),
                new ItemVulcaniteShovel(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_SHOVEL),
                new HoeItem(TOOL_VULCANITE, -0.5f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(Vulcanite.MOD_ID, Names.VULCANITE_HOE),
                new ItemFlintAndVulcanite(Vulcanite.RESOURCE_PREFIX + Names.FLINT_AND_VULCANITE),
                new ItemVulcaniteSword(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_SWORD),

                new ItemVulcaniteArmor(EquipmentSlotType.HEAD, Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_HELMET),
                new ItemVulcaniteArmor(EquipmentSlotType.CHEST, Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_CHESTPLATE),
                new ItemVulcaniteArmor(EquipmentSlotType.LEGS, Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_LEGGINGS),
                new ItemVulcaniteArmor(EquipmentSlotType.FEET, Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BOOTS),

                //addToolType doesn't work but I think might be given use in future
                new BlockItem(ModBlocks.vulcaniteBlock, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS).addToolType(ToolType.PICKAXE, 2)).setRegistryName(Vulcanite.MOD_ID, Names.VULCANITE_BLOCK),
                new BlockItem(ModBlocks.vulcaniteOre, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS).addToolType(ToolType.PICKAXE, 2)).setRegistryName(Vulcanite.MOD_ID, Names.NETHER_VULCANITE_ORE)
        );
    }
}