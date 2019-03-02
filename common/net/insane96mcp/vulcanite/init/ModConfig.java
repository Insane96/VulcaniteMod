package net.insane96mcp.vulcanite.init;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ModConfig {
	
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static ForgeConfigSpec SPEC;
	
	public static void Init(Path file) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(file)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        SPEC.setConfig(configData);
	}
	
	public static class ToolsAndWeapons {
		public static String name = "tools_and_weapons";
		

		public static class BonusStats {
			public static String name = "bonus_stats";
			
			public static ConfigValue<Double> damage;
			public static ConfigValue<Double> damageFireAspect;
			public static ConfigValue<Boolean> shouldDropExperience;
			public static ConfigValue<Double> efficiency;
			
			public static void Init() {
				BUILDER.push(name);
				damage = BUILDER
					.comment("Bonus damage % dealt to Fire Immune mobs")
					.defineInRange("damage", 15.0, 0.0, Double.MAX_VALUE);
				damageFireAspect = BUILDER
					.comment("Bonus damage % dealt to Fire Immune mobs per Fire Aspect Level")
					.defineInRange("damage_fire_aspect", 7.5, 0.0, Double.MAX_VALUE);
				shouldDropExperience = BUILDER
					.comment("If the smelting property of the tools should make the blocks broken drop experience")
					.define("should_drop_experience", true);
				efficiency = BUILDER
					.comment("Bonus Efficency % for tools when in the nether (100.0 means that the tool will be twice as fast in the nether)")
					.defineInRange("efficiency", 50.0, 0.0, Double.MAX_VALUE);
				BUILDER.pop();
			}
		}
		
		public static class FlintAndVulcanite {
			public static String name = "flint_and_vulcanite";
			
			public static ConfigValue<Boolean> enabled;
			public static ConfigValue<Integer> secondsOnFire;
			public static ConfigValue<Integer> durabilityOnUse;
			public static ConfigValue<Boolean> tntIgnitesFaster;
			
			public static void Init() {
				BUILDER.push(name);
				secondsOnFire = BUILDER
					.comment("The number of seconds an entity will be set on fire when right clicked with Flint and Vulcanite")
					.defineInRange("seconds_on_fire", 15, 0, Short.MAX_VALUE);
				durabilityOnUse = BUILDER
					.comment("How much uses will be consumed on the flint and vulcanite when you set a mob on fire")
					.defineInRange("durability_on_use", 2, 1, 222);
				tntIgnitesFaster = BUILDER
					.comment("If true TNT will take half the time to explode when ignited with Flint and Vulcanite")
					.define("tnt_ignites_faster", true);
				/*enabled = BUILDER
					.comment("Enable / Disable Flint and Vulcanite")
					.define("enabled", true);*/
				BUILDER.pop();
			}
		}
		
		public static void Init() {
			BUILDER.push(name);
			
			BonusStats.Init();
			FlintAndVulcanite.Init();
			
			BUILDER.pop();
		}
	}
	
	public static class Armor {
		public static String name = "armor";

		public static ConfigValue<Double> damageReductionNether;
		public static ConfigValue<Double> damageReductionOther;
		
		public static void Init() {
			BUILDER.push(name);
			damageReductionNether = BUILDER
					.comment("Percentage damage reduction from hot sources with full Vulcanite Armor in the Nether")
					.defineInRange("damage_reduction_nether", 75.0, 0, 100);
			damageReductionOther= BUILDER
					.comment("Percentage damage reduction from hot sources with full Vulcanite Armor in non-Nether dimensions")
					.defineInRange("damage_reduction_other", 40.0, 0, 100);
			BUILDER.pop();
		}
	}
	
	public static class OreGeneration {
		public static String name = "ore_generation";
		
		public static class Nether {
			public static String name = "nether";
			
			public static ConfigValue<Integer> orePerVein;
			public static ConfigValue<Integer> veinPerChunk;
			public static ConfigValue<Integer> minY;
			public static ConfigValue<Integer> maxY;
			
			public static void Init() {
				BUILDER.push(name);
				orePerVein = BUILDER
					.comment("Number of ores generated per vein")
					.defineInRange("ore_per_vein", 4, 1, 255);
				veinPerChunk = BUILDER
					.comment("Number of veins that have to try to spawn per chunk. Set to 0 to disable Nether Vulcanite generation")
					.defineInRange("vein_per_chunk", 13, 0, 255);
				minY = BUILDER
					.comment("The minimum height (Y) to try to generate Veins")
					.defineInRange("min_y", 0, 0, 128);
				maxY = BUILDER
					.comment("The maximum height (Y) to try to generate Veins")
					.defineInRange("max_y", 32, 0, 128);
				BUILDER.pop();
			}
		}
		
		/*public static class Overworld {
			public static String name = "overworld";
			
			public static void Init() {
				BUILDER.push(name);
				
				BUILDER.pop();
			}
		}*/
		
		public static void Init() {
			BUILDER.push(name);
			Nether.Init();
			//Overworld.Init();
			BUILDER.pop();
		}
	}
	
	public static class Misc {
		public static String name = "misc";
		
		public static ConfigValue<Integer> vulcaniteBlockTimeOnFire;
		
		public static void Init() {
			BUILDER.push(name);
			vulcaniteBlockTimeOnFire = BUILDER
				.comment("How much time will the Vulcanite Block set on fire mobs that are standing on it")
				.defineInRange("vulcanite_block_time_on_fire", 3, 0, Short.MAX_VALUE);
			BUILDER.pop();
		}
	}
	
	static {
		ToolsAndWeapons.Init();
		Armor.Init();
		OreGeneration.Init();
		Misc.Init();
		
		SPEC = BUILDER.build();
	}
	/*		
		@Name("Ore Generation")
		public OreGeneration oreGeneration = new OreGeneration();
		
		public static class OreGeneration {
			public Overworld overworld = new Overworld();
			
			public static class Overworld {
				@Name("Veins Per Chunk")
				@Comment("Number of veins that have to try to spawn per chunk")
				public int veinPerChunk = 0;
				@Name("Ore Per Vein")
				@Comment("Number of ores per vein")
				public int orePerVein = 15; 
				@Name("Min Y")
				@Comment("The minimum height (Y) to try to generate Veins")
				@RangeInt(min = 0, max = 128)
				public int minY = 11;
				@Name("Max Y")
				@Comment("The maximum height (Y) to try to generate Veins")
				@RangeInt(min = 0, max = 128)
				public int maxY = 14;
				@Name("Minimum Lava Required")
				@Comment("How many lava blocks should be near vulcanite to be able to spawn?")
				public int minLavaRequired = 5;

				@Name("Min Nugget Drop")
				@Comment("Minimum amount of nuggets that Vulcanite ore drops")
				public int minNuggetDrop = 1;
				@Name("Max Nugger Drop")
				@Comment("Maximum amount of nuggets that Vulcanite ore drops without fortune")
				public int maxNuggetDrop = 2;
			}
		}
	}
	
	
	@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
	private static class EventHandler{
		@SubscribeEvent
	    public static void onConfigChangedEvent(OnConfigChangedEvent event)
	    {
	        if (event.getModID().equals(Vulcanite.MOD_ID))
	        {
	            ConfigManager.sync(Vulcanite.MOD_ID, Type.INSTANCE);
	        }
	    }
	}*/
}