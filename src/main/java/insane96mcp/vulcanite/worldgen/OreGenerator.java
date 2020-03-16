package insane96mcp.vulcanite.worldgen;

import insane96mcp.vulcanite.setup.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGenerator {

    public static void init() {
        final CountRangeConfig DEPTH_COUNT_RANGE = new CountRangeConfig(12, 4, 0, 32);
        final CountRangeConfig ANY_HEIGHT_COUNT_RANGE = new CountRangeConfig(2, 4, 0, 124);
        final int VEIN_SIZE = 4;

        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (biome.getCategory().equals(Biome.Category.NETHER)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                        Biome.createDecoratedFeature(Feature.ORE,
                                new OreFeatureConfig(
                                        OreFeatureConfig.FillerBlockType.NETHERRACK,
                                        ModBlocks.vulcaniteOre.getDefaultState(),
                                        VEIN_SIZE
                                ),
                                Placement.COUNT_RANGE, DEPTH_COUNT_RANGE
                        )
                );
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                        Biome.createDecoratedFeature(Feature.ORE,
                                new OreFeatureConfig(
                                        OreFeatureConfig.FillerBlockType.NETHERRACK,
                                        ModBlocks.vulcaniteOre.getDefaultState(),
                                        VEIN_SIZE
                                ),
                                Placement.COUNT_RANGE, ANY_HEIGHT_COUNT_RANGE
                        )
                );
            }
        }
    }
}
