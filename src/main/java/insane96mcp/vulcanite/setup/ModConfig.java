package insane96mcp.vulcanite.setup;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.item.ItemFlintAndVulcanite;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfig {

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final CommonConfig COMMON;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON = specPair.getLeft();
        COMMON_SPEC = specPair.getRight();
    }

    public static class CommonConfig {

        public final ToolsAndWeapons toolsAndWeapons;
        public final Armor armor;
        public final Misc misc;

        public CommonConfig(final ForgeConfigSpec.Builder builder) {
            toolsAndWeapons = new ToolsAndWeapons(builder);
            armor = new Armor(builder);
            misc = new Misc(builder);
        }

        public static class ToolsAndWeapons {
            public static String name = "tools_and_weapons";

            public final BonusStats bonusStats;
            public final FlintAndVulcanite flintAndVulcanite;

            public ToolsAndWeapons(ForgeConfigSpec.Builder builder) {
                builder.push(name);

                bonusStats = new BonusStats(builder);
                flintAndVulcanite = new FlintAndVulcanite(builder);

                builder.pop();
            }

            public static class BonusStats {
                public static String name = "bonus_stats";

                public static ConfigValue<Double> damage;
                public static ConfigValue<Double> damageFireAspect;
                public static ConfigValue<Boolean> smeltingDropsExperience;
                public static ConfigValue<Double> efficiency;

                public BonusStats(ForgeConfigSpec.Builder builder) {
                    builder.push(name);
                    damage = builder
                            .comment("Bonus damage % dealt to Fire Immune mobs")
                            .defineInRange("damage", 16.0, 0.0, Double.MAX_VALUE);
                    damageFireAspect = builder
                            .comment("Bonus damage % dealt to Fire Immune mobs per Fire Aspect Level")
                            .defineInRange("damage_fire_aspect", 8.0, 0.0, Double.MAX_VALUE);
                    smeltingDropsExperience = builder
                            .comment("If the Smelting property of the tools should make the blocks broken drop experience")
                            .define("smelting_drops_experience", true);
                    efficiency = builder
                            .comment("Bonus Efficency % for tools when in the nether (100.0 means that the tool will be twice as fast in the nether)")
                            .defineInRange("efficiency", 75.0, 0.0, Double.MAX_VALUE);
                    builder.pop();
                }
            }

            public static class FlintAndVulcanite {
                public static String name = "flint_and_vulcanite";

                public static ConfigValue<Boolean> enabled;
                public static ConfigValue<Integer> secondsOnFire;
                public static ConfigValue<Integer> durabilityOnUse;
                public static ConfigValue<Boolean> tntIgnitesFaster;

                public FlintAndVulcanite(ForgeConfigSpec.Builder builder) {
                    builder.push(name);
                    secondsOnFire = builder
                            .comment("The number of seconds an entity will be set on fire when right clicked with Flint and Vulcanite. This is doubled with the Flame enchantment.")
                            .defineInRange("seconds_on_fire", 5, 0, Short.MAX_VALUE);
                    durabilityOnUse = builder
                            .comment("How much uses will be consumed on the flint and vulcanite when you set a mob on fire")
                            .defineInRange("durability_on_use", 2, 1, ItemFlintAndVulcanite.DURABILITY);
                    tntIgnitesFaster = builder
                            .comment("If true TNT will take half the time to explode when ignited with Flint and Vulcanite")
                            .define("tnt_ignites_faster", true);
                    builder.pop();
                }
            }
        }

        public static class Armor {
            public static String name = "armor";

            public static ConfigValue<Double> damageReductionNether;
            public static ConfigValue<Double> damageReductionOther;

            public Armor(ForgeConfigSpec.Builder builder) {
                builder.push(name);
                damageReductionNether = builder
                        .comment("Percentage damage reduction from hot sources with full Vulcanite Armor in the Nether")
                        .defineInRange("damage_reduction_nether", 75.0, 0, 100);
                damageReductionOther = builder
                        .comment("Percentage damage reduction from hot sources with full Vulcanite Armor in non-Nether dimensions")
                        .defineInRange("damage_reduction_other", 40.0, 0, 100);
                builder.pop();
            }
        }

        public static class Misc {
            public static String name = "misc";

            public static ConfigValue<Integer> vulcaniteBlockTimeOnFire;

            public Misc(ForgeConfigSpec.Builder builder) {
                builder.push(name);
                vulcaniteBlockTimeOnFire = builder
                        .comment("How much time will the Vulcanite Block set on fire mobs that are standing on it")
                        .defineInRange("vulcanite_block_time_on_fire", 3, 0, Short.MAX_VALUE);
                builder.pop();
            }
        }
    }
}