package net.insane96mcp.vulcanite.init;

import java.util.ArrayList;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.block.BlockVulcanite;
import net.insane96mcp.vulcanite.block.BlockVulcaniteOre;
import net.insane96mcp.vulcanite.lib.Names;
import net.insane96mcp.vulcanite.worldgen.NetherOreGeneration;
import net.insane96mcp.vulcanite.worldgen.OverworldOreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks {
	
	public static BlockVulcanite vulcaniteBlock;
	public static BlockVulcaniteOre vulcaniteOre;
	public static Block netherVulcaniteOre;
	
	public static ArrayList<Block> BLOCKS = new ArrayList<Block>();
	
	public static void PreInit() {
		ResourceLocation location = new ResourceLocation(Vulcanite.MOD_ID, Names.VULCANITE_BLOCK);
		vulcaniteBlock = new BlockVulcanite();
		vulcaniteBlock.setRegistryName(location);
		vulcaniteBlock.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		vulcaniteBlock.setHardness(10.0f);
		vulcaniteBlock.setResistance(20f);
		vulcaniteBlock.setHarvestLevel("pickaxe", 2);
		BLOCKS.add(vulcaniteBlock);

		location = new ResourceLocation(Vulcanite.MOD_ID, Names.VULCANITE_ORE);
		vulcaniteOre = new BlockVulcaniteOre();
		vulcaniteOre.setRegistryName(location);
		vulcaniteOre.setTranslationKey(location.toString());
		vulcaniteOre.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		vulcaniteOre.setHardness(8.0f);
		vulcaniteOre.setResistance(10f);
		vulcaniteOre.setHarvestLevel("pickaxe", 2);
		BLOCKS.add(vulcaniteOre);

		location = new ResourceLocation(Vulcanite.MOD_ID, Names.NETHER_VULCANITE_ORE);
		netherVulcaniteOre = new Block(Material.ROCK);
		netherVulcaniteOre.setRegistryName(location);
		netherVulcaniteOre.setTranslationKey(location.toString());
		netherVulcaniteOre.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		netherVulcaniteOre.setHardness(6.0f);
		netherVulcaniteOre.setResistance(10f);
		netherVulcaniteOre.setHarvestLevel("pickaxe", 2);
		BLOCKS.add(netherVulcaniteOre);

		GameRegistry.registerWorldGenerator(new NetherOreGeneration(), 0);
		GameRegistry.registerWorldGenerator(new OverworldOreGeneration(), 0);
	}
	
	public static void Init() {
		OreDictionary.registerOre("oreVulcanite", vulcaniteOre);
		OreDictionary.registerOre("oreVulcanite", netherVulcaniteOre);
		OreDictionary.registerOre("blockVulcanite", vulcaniteBlock);
	}
}
