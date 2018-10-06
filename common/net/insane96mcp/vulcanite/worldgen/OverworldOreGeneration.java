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
import net.minecraftforge.fml.common.eventhandler.IGenericEvent;

public class OverworldOreGeneration implements IWorldGenerator {

	private final OverworldGenMinable worldGenMinable;
	
	public OverworldOreGeneration() {
		worldGenMinable = new OverworldGenMinable(ModBlocks.vulcaniteOre.getDefaultState(), Properties.OreGeneration.Overworld.orePerVein, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

		if (world.provider.getDimension() == 0) {
			for (int i = 0; i < Properties.OreGeneration.Overworld.veinPerChunk; i++) {
				worldGenMinable.generate(world, random, chunkPos.add(random.nextInt(16), random.nextInt(Properties.OreGeneration.Overworld.maxY - Properties.OreGeneration.Overworld.minY) + Properties.OreGeneration.Overworld.minY, random.nextInt(16)));
			}
		}
	}

}
