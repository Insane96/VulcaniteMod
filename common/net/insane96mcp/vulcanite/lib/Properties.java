package net.insane96mcp.vulcanite.lib;

import net.insane96mcp.vulcanite.Vulcanite;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@Config(modid = Vulcanite.MOD_ID, category = "", name = "Vulcanite")
public class Properties {
	
	@Name("Config")
	public static final ConfigOptions config = new ConfigOptions();
	
	public static class ConfigOptions {
		
		public ToolsAndWeapons toolsAndWeapons = new ToolsAndWeapons();
		
		public static class ToolsAndWeapons {
			
			
			public BonusStats bonusStats = new BonusStats();
			
			public static class BonusStats {
				@Comment("Bonus damage % dealt to Fire Immune mobs")
				public static float damage = 15f;
				@Comment("Bonus damage % dealt to Fire Immune mobs per Fire Aspect Level (to sum to 'bonus_damage')")
				public static float damageFireAspect = 7.5f;
				@Comment("Bonus Efficency % for tools when in the nether (100.0 means that the tool will be twice as fast in the nether)")
				public static float efficencyInNether = 100f;
			}
			
			
			public FlintAndVulcanite flintAndVulcanite = new FlintAndVulcanite();
			
			public static class FlintAndVulcanite {
				@Comment("The number of seconds an entity will be set on fire when right clicked with Flint and Vulcanite")
				public static int secondsOnFire = 4;
				@Comment("How much uses will be consumed on the flint and vulcanite when you set a mob on fire")
				public static int damageOnUse = 2;
				@Comment("If true, players will be able to ignite other players")
				public static boolean pvp = false;
				@Comment("If true TNT will take half the time to explode when ignited with Flint and Vulcanite")
				public static boolean tntIgnitesFaster = true;
			}
		}
		
		
		public Armor armor = new Armor();
		
		public static class Armor {
			@Comment("Percentage damage reduction from hot sources with full Vulcanite Armor in the Nether")
			public static float damageReductionNether = 75f;
			@Comment("Percentage damage reduction from hot sources with full Vulcanite Armor in non-Nether dimensions")
			public static float damageReductionOther = 40f;
		}
		
		
		public OreGeneration oreGeneration = new OreGeneration();
		
		public static class OreGeneration {
			
			public Nether nether = new Nether();
			
			public static class Nether {
				@Comment("Number of ores generated per vein")
				public static int orePerVein = 4;
				@Comment("Number of veins that have to try to spawn per chunk")
				public static int veinPerChunk = 22;
				@Comment("The minimum height (Y) to try to generate Veins")
				public static int minY = 0;
				@Comment("The maximum height (Y) to try to generate Veins")
				public static int maxY = 32;
			}
			
			
			public Overworld overworld = new Overworld();
			
			public static class Overworld {
				@Comment("Number of veins that have to try to spawn per chunk")
				public static int veinPerChunk = 1;
				@Comment("Number of ores per vein")
				public static int orePerVein = 15; 
				@Comment("The minimum height (Y) to try to generate Veins")
				public static int minY = 11;
				@Comment("The maximum height (Y) to try to generate Veins")
				public static int maxY = 14;
				@Comment("How many lava blocks should be near vulcanite to be able to spawn?")
				public static int minLavaRequired = 5;

				@Comment("Minimum amount of nuggets that Vulcanite ore drops")
				public static int minNuggetDrop = 1;
				@Comment("Maximum amount of nuggets that Vulcanite ore drops without fortune")
				public static int maxNuggetDrop = 2;
			}
		}
		
		@Comment("How much time will the Vulcanite Block set on fire mobs that are standing on it")
		public static int vulcaniteBlockTimeOnFire = 3;
		@Comment("Whenever or not enable \"Press SHIFT to show more infos\"")
		public static boolean showMoreInfo = false;
	}
	
	
	@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
	private static class EventHandler{
	    public static void onConfigChangedEvent(OnConfigChangedEvent event)
	    {
	        if (event.getModID().equals(Vulcanite.MOD_ID))
	        {
	            ConfigManager.sync(Vulcanite.MOD_ID, Type.INSTANCE);
	        }
	    }
	    
	    public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
	    	if (event.player.world.isRemote)
	    		return;
	    	
	    	
	    }
	}
}
