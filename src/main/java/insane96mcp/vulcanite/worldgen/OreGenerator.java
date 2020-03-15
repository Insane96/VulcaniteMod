package insane96mcp.vulcanite.worldgen;

import insane96mcp.vulcanite.setup.ModBlocks;
import insane96mcp.vulcanite.setup.ModConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Predicate;

public class OreGenerator {

    private static final Predicate<BlockState> IS_NETHERRACK = state -> state.getBlock() == Blocks.NETHERRACK;

    public static void init() {
        final CountRangeConfig COUNT_RANGE_CONFIG = new CountRangeConfig(ModConfig.COMMON.oreGeneration.veinPerChunk.get(), ModConfig.COMMON.oreGeneration.minY.get(), 0, ModConfig.COMMON.oreGeneration.maxY.get());
        final int VEIN_SIZE = ModConfig.COMMON.oreGeneration.orePerVein.get();

        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (biome.getCategory().equals(Biome.Category.NETHER)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                        Biome.createDecoratedFeature(Feature.ORE,
                                new OreFeatureConfig(
                                        OreFeatureConfig.FillerBlockType.NETHERRACK,
                                        ModBlocks.vulcaniteOre.getDefaultState(),
                                        VEIN_SIZE
                                ),
                                Placement.COUNT_RANGE, COUNT_RANGE_CONFIG
                        )
                );
            }
        }
    }
}
