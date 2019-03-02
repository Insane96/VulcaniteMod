package net.insane96mcp.vulcanite.worldgen;

import java.util.function.Predicate;

import net.insane96mcp.vulcanite.init.ModBlocks;
import net.insane96mcp.vulcanite.init.ModConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRange;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGenerator {
	private static final Predicate<IBlockState> IS_NETHERRACK = state -> state.getBlock() == Blocks.NETHERRACK;
	
	public static void Init() {

		CountRangeConfig placementConfig = new CountRangeConfig(ModConfig.OreGeneration.Nether.veinPerChunk.get(), ModConfig.OreGeneration.Nether.minY.get(), ModConfig.OreGeneration.Nether.minY.get(), ModConfig.OreGeneration.Nether.maxY.get());
		
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome.getCategory().equals(Category.NETHER)) {
				biome.addFeature(
					Decoration.UNDERGROUND_ORES, 
					Biome.createCompositeFeature(
						Feature.MINABLE, 
						new MinableConfig(IS_NETHERRACK, ModBlocks.vulcaniteOre.getDefaultState(), ModConfig.OreGeneration.Nether.orePerVein.get()), 
						new CountRange(), 
						placementConfig
					)
				);
			}
		}
	}
}
