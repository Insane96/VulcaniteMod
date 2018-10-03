package net.insane96mcp.vulcanite.worldgen;

import java.util.Random;

import net.insane96mcp.vulcanite.init.ModBlocks;
import net.insane96mcp.vulcanite.lib.Properties;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OverworldOreGeneration implements IWorldGenerator {

	private final WorldGenMinable worldGenMinable;
	
	public OverworldOreGeneration() {
		worldGenMinable = new WorldGenMinable(ModBlocks.vulcaniteOre.getDefaultState(), Properties.OreGeneration.orePerVein, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

		if (world.provider.getDimension() == 0) {
			for (int i = 0; i < Properties.OreGeneration.veinPerChunk; i++) {
				worldGenMinable.generate(world, random, chunkPos.add(random.nextInt(16), random.nextInt(Properties.OreGeneration.maxY - Properties.OreGeneration.minY) + Properties.OreGeneration.minY, random.nextInt(16)));
			}
		}
	}

}
