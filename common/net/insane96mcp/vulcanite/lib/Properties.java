package net.insane96mcp.vulcanite.lib;

import net.insane96mcp.vulcanite.Vulcanite;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@Config(modid = Vulcanite.MOD_ID, category = "", name = "Vulcanite")
public class Properties {
	
	@Name("Config")
	public static final ConfigOptions config = new ConfigOptions();
	
	public static class ConfigOptions {
		
		@Name("Tools and Weapons")
		public ToolsAndWeapons toolsAndWeapons = new ToolsAndWeapons();
		
		public static class ToolsAndWeapons {
			
			@Name("Bonus Stats")
			public BonusStats bonusStats = new BonusStats();
			
			public static class BonusStats {
				@Name("Bonus Damage")
				@Comment("Bonus damage % dealt to Fire Immune mobs")
				public float damage = 15f;
				@Name("Bonus Damage with Fire Aspect")
				@Comment("Bonus damage % dealt to Fire Immune mobs per Fire Aspect Level (to sum to 'bonus_damage')")
				public float damageFireAspect = 7.5f;
				@Name("Bonus Efficiency")
				@Comment("Bonus Efficency % for tools when in the nether (100.0 means that the tool will be twice as fast in the nether)")
				public float efficencyInNether = 100f;
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
				@Name("PvP")
				@Comment("If true, players will be able to ignite other players")
				public boolean pvp = false;
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
			public float damageReductionNether = 75f;
			@Name("Damage Reduction in Overworld")
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
				public int veinPerChunk = 15;
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
				public int veinPerChunk = 1;
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
		@Name("More Info")
		@Comment("Whenever or not enable more infos")
		public boolean showMoreInfo = false;
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
	    
		@SubscribeEvent
	    public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
	    	if (event.player.world.isRemote)
	    		return;
	    	
	    	
	    }
	}
}
