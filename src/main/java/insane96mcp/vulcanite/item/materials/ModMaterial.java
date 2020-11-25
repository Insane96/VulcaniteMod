package insane96mcp.vulcanite.item.materials;

import insane96mcp.vulcanite.setup.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class ModMaterial {
    public static IItemTier TOOL_VULCANITE = new IItemTier() {

        @Override
        public Ingredient getRepairMaterial() {
            Ingredient repairMaterial = Ingredient.fromItems(ModItems.VULCANITE_INGOT.get());
            return repairMaterial;
        }

        @Override
        public int getMaxUses() {
            return 313;
        }

        @Override
        public int getHarvestLevel() {
            return 3;
        }

        @Override
        public int getEnchantability() {
            return 18;
        }

        @Override
        public float getEfficiency() {
            return 5f;
        }

        @Override
        public float getAttackDamage() {
            return 2f;
        }
    };

    public static IArmorMaterial ARMOR_VULCANITE = new IArmorMaterial() {

        @Override
        public float getToughness() {
            return 0;
        }

        //Had to implement this since the Interface IArmorMaterial uses this now. TODO: SET A VALUE TO getKnockbackResistance()
        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
        }

        @Override
        public Ingredient getRepairMaterial() {
            Ingredient repairMaterial = Ingredient.fromItems(ModItems.VULCANITE_INGOT.get());
            return repairMaterial;
        }

        @Override
        public String getName() {
            return "vulcanite:vulcanite";
        }

        @Override
        public int getEnchantability() {
            return 10;
        }

        @Override
        public int getDurability(EquipmentSlotType arg0) {
            switch (arg0) {
                case HEAD:
                    return 206;

                case CHEST:
                    return 300;

                case LEGS:
                    return 281;

                case FEET:
                    return 244;

                default:
                    break;
            }
            return 0;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType arg0) {

            switch (arg0) {
                case HEAD:
                    return 3;

                case CHEST:
                    return 6;

                case LEGS:
                    return 4;

                case FEET:
                    return 2;

                default:
                    break;
            }
            return 0;
        }
    };
}
