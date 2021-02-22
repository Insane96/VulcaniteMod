package insane96mcp.vulcanite.worldgen;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.setup.ModBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class OreGenerator {

	private static final ArrayList<ConfiguredFeature<?, ?>> ores = new ArrayList<ConfiguredFeature<?, ?>>();

	public static void init() {
		final int VEIN_SIZE = 4;

		ores.add(register(
				"deep_vulcanite",
				Feature.ORE.withConfiguration(
						new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.NETHER_VULCANITE_ORE.get().getDefaultState(), VEIN_SIZE)
				)
						.range(32).square()
						.func_242731_b(4)
		));
		ores.add(register(
				"any_height_vulcanite",
				Feature.ORE.withConfiguration(
						new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.NETHER_VULCANITE_ORE.get().getDefaultState(), VEIN_SIZE)
				)
						.range(128).square()
						.func_242731_b(4)
		));
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void gen(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		if (event.getCategory().equals(Biome.Category.NETHER)) {
			for (ConfiguredFeature<?, ?> ore : ores) {
				if (ore != null)
					generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
			}
		}
	}

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Vulcanite.RESOURCE_PREFIX + name, configuredFeature);
	}
}
