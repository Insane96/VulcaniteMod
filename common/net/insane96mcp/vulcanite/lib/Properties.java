package net.insane96mcp.vulcanite.lib;

public class Properties {
	
	public static void Init() {
		Tools.Init();
		Armor.Init();
		OreGeneration.Init();
		General.Init();
	}
	
	public static class Tools{
		public static final String CATEGORY = "tools_and_weapons";
		
		public static void Init() {			
			Base.Init();
			Bonus.Init();
			FlintAndVulcanite.Init();
		}
		
		public static class Base{
			public static final String SUBCATEGORY = CATEGORY + ".base_properties";
			
			public static int harvestLevel;
			public static int maxUses;
			public static float efficency;
			public static float damage;
			public static int enchantability;
			
			public static void Init() {
				harvestLevel = Config.LoadIntProperty(SUBCATEGORY, "harvest_level", "Harvest level for Vulcanite Tools\n(0 for wood, 1 for stone, 2 for iron, 3 for diamond)\n", 3);
				maxUses = Config.LoadIntProperty(SUBCATEGORY, "max_uses", "Durability for Vulcanite Tools and Sword", 859);
				efficency = Config.LoadFloatProperty(SUBCATEGORY, "efficency", "Efficency for Vulcanite Tools", 7f);
				damage = Config.LoadFloatProperty(SUBCATEGORY, "base_damage", "Base Damage for Vulcanite Tools and Sword", 2.5f);
				enchantability = Config.LoadIntProperty(SUBCATEGORY, "enchantability", "Enchantability for Vulcanite Tools and Sword", 16);
			}
		}
	
		public static class Bonus{
			public static final String SUBCATEGORY = CATEGORY + ".bonuses";

			public static float damage;
			public static float damageFireAspect;
			public static float efficency;
			
			public static void Init() {
				damage = Config.LoadFloatProperty(SUBCATEGORY, "bonus_damage", "Bonus damage % dealt to Fire Immune mobs", 15f);
				damageFireAspect = Config.LoadFloatProperty(SUBCATEGORY, "bonus_damage_fire_aspect", "Bonus damage % dealt to Fire Immune mobs per Fire Aspect Level (to sum to 'bonus_damage')", 7.5f);
				efficency = Config.LoadFloatProperty(SUBCATEGORY, "bonus_efficency_in_nether", "Bonus Efficency % for tools when in the nether (100.0 means that the tool will be twice as fast in the nether)", 100f);
			}
		}
	
		public static class FlintAndVulcanite{
			public static final String SUBCATEGORY = CATEGORY + ".flint_and_vulcanite";
			
			public static int secondsOnFire;
			public static int maxUses;
			public static int damageOnUse;
			
			public static boolean pvp;
			public static boolean tntIgnitesFaster;
			
			public static void Init() {
				secondsOnFire = Config.LoadIntProperty(SUBCATEGORY, "ignite_seconds", "The number of seconds an entity will be set on fire when right clicked with Flint and Vulcanite", 4);
				maxUses = Config.LoadIntProperty(SUBCATEGORY, "max_uses", "The durability of Flint and Vulcanite", 222);
				damageOnUse = Config.LoadIntProperty(SUBCATEGORY, "damage_on_use", "How much the flint and vulcanite will be damaged when you set a mob on fire", 2);
				pvp = Config.LoadBoolProperty(SUBCATEGORY, "pvp", "If true, players will be able to ignite other players", false);
				tntIgnitesFaster = Config.LoadBoolProperty(SUBCATEGORY, "tnt_ignites_faster", "If true TNT will take half the time to explode when ignited with Flint and Vulcanite", true);
			}
		}
	}
	
	public static class Armor{
		public static final String CATEGORY = "armor";
		
		public static int baseDurability;
		public static int[] armorPoints;
		public static int enchantability;
		public static float toughness;
		public static float damageReductionNether;
		public static float damageReductionOther;
		
		public static void Init() {
			baseDurability = Config.LoadIntProperty(CATEGORY, "base_durability", "Base durability for Vulcanite Armor\n(this value is multiplied by [11, 16, 15, 13] respectively from helmet to boots)\n", 22);
			armorPoints = Config.LoadIntListProperty(CATEGORY, "armor_points", "Armor Points given by Vulcanite Armor", new int[] {2, 5, 4, 2});
			enchantability = Config.LoadIntProperty(CATEGORY, "enchantability", "Enchantability for Vulcanite Armor", 16);
			toughness = Config.LoadFloatProperty(CATEGORY, "toughness", "Toughness for Vulcanite Armor", 0f);
			damageReductionNether = Config.LoadFloatProperty(CATEGORY, "damage_reduction", "Percentage damage reduction from hot sources with full Vulcanite Armor in the Nether", 75f);
			damageReductionOther = Config.LoadFloatProperty(CATEGORY, "damage_reduction_other_dimensions", "Percentage damage reduction from hot sources with full Vulcanite Armor in non-Nether dimensions", 40f);
		}
	}
	
	public static class OreGeneration{
		public static final String CATEGORY = "ore_generation";
		
		public static void Init() {
			Nether.Init();
			Overworld.Init();
		}
		
		public static class Nether{
			public static final String SUBCATEGORY = CATEGORY + ".nether";
			
			public static int orePerVein;
			public static int veinPerChunk;
			public static int minY;
			public static int maxY;
			
			public static void Init() {			
				orePerVein = Config.LoadIntProperty(SUBCATEGORY, "block_per_vein", "Number of ores generated per vein", 3);
				veinPerChunk = Config.LoadIntProperty(SUBCATEGORY, "vein_per_chunk", "Number of veins that have to try to spawn per chunk", 22);
				minY = Config.LoadIntProperty(SUBCATEGORY, "min_Y", "The minimum height (Y) to try to generate Veins", 0);
				maxY = Config.LoadIntProperty(SUBCATEGORY, "max_Y", "The maximum height (Y) to try to generate Veins", 32);
			}
		}
		
		public static class Overworld{
			public static final String SUBCATEGORY = CATEGORY + ".overworld";
			
			public static int veinPerChunk;
			public static int orePerVein;
			public static int minY;
			public static int maxY;
			public static int minLavaRequired;
			
			public static void Init() {
				veinPerChunk = Config.LoadIntProperty(SUBCATEGORY, "vein_per_chunk", "Number of veins that have to try to spawn per chunk", 1);
				orePerVein = Config.LoadIntProperty(SUBCATEGORY, "ore_per_vein", "Number of ores per vein", 15);
				minY = Config.LoadIntProperty(SUBCATEGORY, "min_Y", "The minimum height (Y) to try to generate Veins", 10);
				maxY = Config.LoadIntProperty(SUBCATEGORY, "max_Y", "The maximum height (Y) to try to generate Veins", 14);
				minLavaRequired = Config.LoadIntProperty(SUBCATEGORY, "min_lava_required", "How many lava blocks should be near vulcanite to be able to spawn?", 5);
			}
		}
	}
	
	public static class General{
		public static int vulcaniteBlockTimeOnFire;
		
		public static void Init() {
			vulcaniteBlockTimeOnFire = Config.LoadIntProperty("general", "vulcanite_block_seconds_on_fire", "How much time will the Vulcanite Block set on fire mobs that are standing on it", 3);
		}
	}
}
