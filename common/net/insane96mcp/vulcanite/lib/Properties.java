package net.insane96mcp.vulcanite.lib;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class Properties {
	
	public static void Init() {
		Tools.Init();
		FlintAndVulcanite.Init();
		Armor.Init();
		OreGeneration.Init();
		General.Init();
	}
	
	public static class Tools{
		public static int harvestLevel;
		public static int maxUses;
		public static float efficency;
		public static float baseDamage;
		public static int enchantability;

		public static float bonusDamage;
		public static float bonusDamageFireAspect;
		public static float bonusEfficency;
		
		public static void Init() {
			harvestLevel = Config.LoadIntProperty("tools_and_weapon", "harvest_level", "Harvest level for Vulcanite Tools\n(0 for wood, 1 for stone, 2 for iron, 3 for diamond)\n", 3);
			maxUses = Config.LoadIntProperty("tools_and_weapon", "max_uses", "Durability for Vulcanite Tools and Sword", 859);
			efficency = Config.LoadFloatProperty("tools_and_weapon", "efficency", "Efficency for Vulcanite Tools", 7f);
			baseDamage = Config.LoadFloatProperty("tools_and_weapon", "base_damage", "Base Damage for Vulcanite Tools and Sword", 2.5f);
			enchantability = Config.LoadIntProperty("tools_and_weapon", "enchantability", "Enchantability for Vulcanite Tools and Sword", 16);
			bonusDamage = Config.LoadFloatProperty("tools_and_weapon", "bonus_damage", "Bonus damage % dealt to Fire Immune mobs", 15f);
			bonusDamageFireAspect = Config.LoadFloatProperty("tools_and_weapon", "bonus_damage_fire_aspect", "Bonus damage % dealt to Fire Immune mobs per Fire Aspect Level (to sum to 'bonus_damage')", 7.5f);
			bonusEfficency = Config.LoadFloatProperty("tools_and_weapon", "bonus_efficency_in_nether", "Bonus Efficency % for tools when in the nether (100.0 means that the tool will be twice as fast in the nether)", 100f);
		}
	}
	
	public static class Armor{
		public static int baseDurability;
		public static int[] armorPoints;
		public static int enchantability;
		public static float toughness;
		public static float damageReductionNether;
		public static float damageReductionOther;
		
		public static void Init() {
			baseDurability = Config.LoadIntProperty("armor", "base_durability", "Base durability for Vulcanite Armor\n(this value is multiplied by [11, 16, 15, 13] respectively from helmet to boots)\n", 24);
			armorPoints = Config.LoadIntListProperty("armor", "armor_points", "Armor Points given by Vulcanite Armor", new int[] {2, 5, 4, 2});
			enchantability = Config.LoadIntProperty("armor", "enchantability", "Enchantability for Vulcanite Armor", 17);
			toughness = Config.LoadFloatProperty("armor", "toughness", "Toughness for Vulcanite Armor", 0f);
			damageReductionNether = Config.LoadFloatProperty("armor", "damage_reduction", "Percentage damage reduction from hot sources with Vulcanite Armor in the Nether", 75f);
			damageReductionOther = Config.LoadFloatProperty("armor", "damage_reduction_other_dimensions", "Percentage damage reduction from hot sources with Vulcanite Armor in non-Nether dimensions", 40f);
		}
	}
	
	public static class FlintAndVulcanite {
		public static int secondsOnFire;
		public static int maxUses;
		public static int damageOnUse;
		
		public static boolean pvp;
		
		public static void Init() {
			secondsOnFire = Config.LoadIntProperty("flint_and_vulcanite", "ignite_seconds", "The number of seconds an entity will be set on fire when right clicked with Flint and Vulcanite", 4);
			maxUses = Config.LoadIntProperty("flint_and_vulcanite", "max_uses", "The durability of Flint and Vulcanite", 222);
			damageOnUse = Config.LoadIntProperty("flint_and_vulcanite", "damage_on_use", "How much the flint and vulcanite will be damaged when you set a mob on fire", 2);
			pvp = Config.LoadBoolProperty("flint_and_vulcanite", "pvp", "If true, players will be able to ignite other players", false);
		}
	}
	
	public static class OreGeneration{
		public static int orePerVein;
		public static int veinPerChunk;
		public static int minY;
		public static int maxY;
		
		public static void Init() {			
			orePerVein = Config.LoadIntProperty("ore_generation", "block_per_vein", "Number of ores generated per vein", 3);
			veinPerChunk = Config.LoadIntProperty("ore_generation", "vein_per_chunk", "Number of veins that have to try to spawn per chunk", 25);
			minY = Config.LoadIntProperty("ore_generation", "min_Y", "The minimum height (Y) to try to generate Veins", 0);
			maxY = Config.LoadIntProperty("ore_generation", "max_Y", "The maximum height (Y) to try to generate Veins", 32);
		}
	}
	
	public static class General{
		public static int vulcaniteBlockTimeOnFire;
		
		public static void Init() {
			vulcaniteBlockTimeOnFire = Config.LoadIntProperty("general", "vulcanite_block_seconds_on_fire", "How much time will the Vulcanite Block set on fire mobs that are standing on it", 3);
		}
	}
}
