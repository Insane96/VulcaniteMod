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

public class NetherOreGeneration implements IWorldGenerator {

	private final WorldGenMinable worldGenMinable;
	
	public NetherOreGeneration() {
		worldGenMinable = new WorldGenMinable(ModBlocks.netherVulcaniteOre.getDefaultState(), Properties.config.oreGeneration.nether.orePerVein, BlockMatcher.forBlock(Blocks.NETHERRACK));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

		if (world.provider.getDimension() == -1) {
			for (int i = 0; i < Properties.config.oreGeneration.nether.veinPerChunk; i++) {
				worldGenMinable.generate(world, random, chunkPos.add(random.nextInt(16), random.nextInt(Properties.config.oreGeneration.nether.maxY - Properties.config.oreGeneration.nether.minY) + Properties.config.oreGeneration.nether.minY, random.nextInt(16)));
			}
		}
	}

}
