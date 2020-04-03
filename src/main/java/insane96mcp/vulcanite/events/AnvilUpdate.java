package insane96mcp.vulcanite.events;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.setup.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class AnvilUpdate {

    final static ArrayList<EquipmentUpgrade> validInputs = new ArrayList<>(Arrays.asList(
            new EquipmentUpgrade(Items.IRON_AXE, 3),
            new EquipmentUpgrade(Items.IRON_SHOVEL, 1),
            new EquipmentUpgrade(Items.IRON_PICKAXE, 3),
            new EquipmentUpgrade(Items.IRON_SWORD, 2),
            new EquipmentUpgrade(Items.IRON_HOE, 2),
            new EquipmentUpgrade(Items.IRON_HELMET, 5),
            new EquipmentUpgrade(Items.IRON_CHESTPLATE, 8),
            new EquipmentUpgrade(Items.IRON_LEGGINGS, 7),
            new EquipmentUpgrade(Items.IRON_BOOTS, 4),
            new EquipmentUpgrade(Items.FLINT_AND_STEEL, 1)
    ));

    @SubscribeEvent
    public static void eventAnvilOutput(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (!right.getItem().equals(ModItems.VULCANITE_NUGGET.get()))
            return;

        for (EquipmentUpgrade validInput : validInputs) {
            if (!left.getItem().equals(validInput.item))
                continue;

            if (right.getCount() < Math.round(validInput.materialAmount * 9 * .25f))
                continue;

            int nuggetCost = Math.round(validInput.materialAmount * 9 * .25f);

            String itemName = left.getItem().getRegistryName().getPath();
            ResourceLocation vulcaniteName = new ResourceLocation(Vulcanite.MOD_ID, itemName.replace("iron", "vulcanite"));
            if (left.getItem().equals(Items.FLINT_AND_STEEL))
                vulcaniteName = new ResourceLocation(Vulcanite.MOD_ID, "flint_and_vulcanite");
            ItemStack output = new ItemStack(ForgeRegistries.ITEMS.getValue(vulcaniteName));
            CompoundNBT tags = left.getTag();
            output.setTag(tags);
            event.setOutput(output);
            event.setMaterialCost(nuggetCost);

            int minCost = ModList.get().isLoaded("charm") ? 0 : 1;

            int cost = minCost;

            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
            for (Enchantment enchantment : enchantments.keySet()) {
                int lvl = enchantments.get(enchantment);
                int baseCost = 0;
                switch (enchantment.getRarity()) {
                    case COMMON:
                        baseCost = 1;
                        break;
                    case UNCOMMON:
                        baseCost = 2;
                        break;
                    case RARE:
                        baseCost = 4;
                        break;
                    case VERY_RARE:
                        baseCost = 8;
                }
                cost += baseCost * lvl;
            }
            cost *= 0.25f;

            event.setCost(MathHelper.clamp(cost, minCost, 39));

            //ServerLifecycleHooks.getCurrentServer().getRecipeManager().getRecipes(IRecipeType.CRAFTING);
        }
    }

    private static class EquipmentUpgrade {
        public Item item;
        public int materialAmount;

        public EquipmentUpgrade(Item item, int materialAmount) {
            this.item = item;
            this.materialAmount = materialAmount;
        }
    }
}
