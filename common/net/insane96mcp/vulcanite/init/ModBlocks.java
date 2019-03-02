package net.insane96mcp.vulcanite.init;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.block.BlockVulcanite;
import net.insane96mcp.vulcanite.init.Strings.Names;
import net.minecraft.block.BlockOre;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {
	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BLOCK)
	public static BlockVulcanite vulcaniteBlock;
	
	@ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.NETHER_VULCANITE_ORE)
	public static BlockOre vulcaniteOre;
}
