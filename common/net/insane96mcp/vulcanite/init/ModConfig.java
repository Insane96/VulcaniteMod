package net.insane96mcp.vulcanite.init;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ModConfig {
	
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	public static void Init(Path file) {
		SPEC.setConfig(CommentedFileConfig.builder(file).build());
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
		
		public static void Init() {
			BUILDER.push(name);
			BonusStats.Init();
			BUILDER.pop();
		}
	}
	
	
	static {
		ToolsAndWeapons.Init();
		
		SPEC = BUILDER.build();
	}
	
	/*@Name("Config")
	public static final ConfigOptions config = new ConfigOptions();
	
	public static class ConfigOptions {
		
		@Name("Tools and Weapons")
		public ToolsAndWeapons toolsAndWeapons = new ToolsAndWeapons();
		
		public static class ToolsAndWeapons {
			
			@Name("Bonus Stats")
			public BonusStats bonusStats = new BonusStats();
			
			public static class BonusStats {
				@Name("Bonus Damage")
				@Comment("")
				public float damage = 15f;
				@Name("Bonus Damage with Fire Aspect")
				@Comment("")
				public float damageFireAspect = 7.5f;
				@Name("Should Drop Experience")
				@Comment("")
				public boolean shouldDropExperience = true;
				@Name("Bonus Efficiency")
				@Comment("")
				public float efficencyInNether = 50f;
			}
			
			@Name("Flint and Vulcanite")
			public FlintAndVulcanite flintAndVulcanite = new FlintAndVulcanite();
			
			public static class FlintAndVulcanite {
				@Name("Seconds on Fire")
				@Comment("The number of seconds an entity will be set on fire when right clicked with Flint and Vulcanite")
				public int secondsOnFire = 4;
				@Name("Damage on set on Fire")
				@Comment("How much uses will be consumed on the flint and vulcanite when you set a mob on fire")
				@RangeInt(min = 0, max = 222)
				public int damageOnUse = 2;
				@Name("TnT Ignites Faster")
				@Comment("If true TNT will take half the time to explode when ignited with Flint and Vulcanite")
				public boolean tntIgnitesFaster = true;
			}
		}
		

		@Name("Armor")
		public Armor armor = new Armor();
		
		public static class Armor {
			@Name("Damage Reduction in Nether")
			@Comment("Percentage damage reduction from hot sources with full Vulcanite Armor in the Nether")
			@RangeDouble(min = 0.0, max = 100.0)
			public float damageReductionNether = 70f;
			@Name("Damage Reduction in Other Dimensions")
			@Comment("Percentage damage reduction from hot sources with full Vulcanite Armor in non-Nether dimensions")
			@RangeDouble(min = 0.0, max = 100.0)
			public float damageReductionOther = 40f;
		}
		
		@Name("Ore Generation")
		public OreGeneration oreGeneration = new OreGeneration();
		
		public static class OreGeneration {
			
			public Nether nether = new Nether();
			
			public static class Nether {
				@Name("Ore Per Vein")
				@Comment("Number of ores generated per vein")
				public int orePerVein = 4;
				@Name("Veins Per Chunk")
				@Comment("Number of veins that have to try to spawn per chunk")
				public int veinPerChunk = 12;
				@Name("Min Y")
				@Comment("The minimum height (Y) to try to generate Veins")
				@RangeInt(min = 0, max = 128)
				public int minY = 0;
				@Name("Max Y")
				@Comment("The maximum height (Y) to try to generate Veins")
				@RangeInt(min = 0, max = 128)
				public int maxY = 32;
			}
			
			
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

		@Name("Vulcanite Block Time on Fire")
		@Comment("How much time will the Vulcanite Block set on fire mobs that are standing on it")
		public int vulcaniteBlockTimeOnFire = 3;
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