package net.insane96mcp.vulcanite.events;

import java.util.List;

import net.insane96mcp.vulcanite.Vulcanite;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.fixes.TileEntityId;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegistryMissingMappings {

	@SubscribeEvent
	public static void MissingItemMappingsEvent(RegistryEvent.MissingMappings<Item> event) {
		List<Mapping<Item>> list = event.getMappings();
		
		for (Mapping<Item> item : list) {
			ResourceLocation oldLoc = item.key;
			item.remap(Item.REGISTRY.getObjectById(item.id));
			System.out.format("Remapped item: {} -> {}", oldLoc, item.key);
		}
	}
	
	@SubscribeEvent
	public static void MissingBlockMappingsEvent(RegistryEvent.MissingMappings<Block> event) {
		List<Mapping<Block>> list = event.getMappings();
		
		for (Mapping<Block> block : list) {
			ResourceLocation oldLoc = block.key;
			block.remap(Block.REGISTRY.getObjectById(block.id));
			System.out.format("Remapped block: {} -> {}", oldLoc, block.key);
		}
	}
}
