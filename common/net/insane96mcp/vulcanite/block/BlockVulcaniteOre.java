package net.insane96mcp.vulcanite.block;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockVulcaniteOre extends Block{
	
	public BlockVulcaniteOre() {
		super(Material.ROCK);
		
	}
	
	@Override
	public String getTranslationKey() {
		return "tile." + Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_ORE;
	}
}
