package insane96mcp.vulcanite.worldgen;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.setup.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class OreGenerator {

	private static final ArrayList<ConfiguredFeature<?, ?>> ores = new ArrayList<>();

	public static void init() {
		final int VEIN_SIZE = 4;

		ores.add(register(
				"deep_vulcanite",
				Feature.ORE.configured(
						new OreConfiguration(OreConfiguration.Predicates.NETHERRACK, ModBlocks.NETHER_VULCANITE_ORE.get().defaultBlockState(), VEIN_SIZE)
				)
						.range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 32)))).squared()
						.count(4)
		);
		ores.add(register(
				"any_height_vulcanite",
				Feature.ORE.configured(
						new OreConfiguration(OreConfiguration.Predicates.NETHERRACK, ModBlocks.NETHER_VULCANITE_ORE.get().defaultBlockState(), VEIN_SIZE)
				)
						.range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 128)))).squared()
						.count(4)
		);
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void gen(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		if (event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
			for (ConfiguredFeature<?, ?> ore : ores) {
				if (ore != null)
					generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ore);
			}
		}
	}

	private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Vulcanite.RESOURCE_PREFIX + name, configuredFeature);
	}
}