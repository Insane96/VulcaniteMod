package insane96mcp.vulcanite.events;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.setup.ModConfig;
import insane96mcp.vulcanite.setup.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class LivingHurt {

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (event.getEntityLiving().world.isRemote)
            return;

        if (!(event.getEntityLiving() instanceof ServerPlayerEntity))
            return;

        ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
        DamageSource source = event.getSource();

        float[] materialPerPiece = new float[]{5, 8, 7, 4};

        DamageSource[] validSources = new DamageSource[]{
                DamageSource.IN_FIRE,
                DamageSource.ON_FIRE,
                DamageSource.HOT_FLOOR,
                DamageSource.LAVA
        };

        ItemStack[] armorList = new ItemStack[]{
                new ItemStack(ModItems.vulcaniteHelmet),
                new ItemStack(ModItems.vulcaniteChestplate),
                new ItemStack(ModItems.vulcaniteLeggings),
                new ItemStack(ModItems.vulcaniteBoots)
        };

        for (DamageSource damageSource : validSources) {
            if (source != damageSource)
                continue;

            float amount = event.getAmount();

            int materialsUsed = 0;
            Iterable<ItemStack> playerArmor = player.getArmorInventoryList();
            for (ItemStack armorPiece : playerArmor) {
                for (int i = 0; i < armorList.length; i++) {
                    if (ItemStack.areItemsEqualIgnoreDurability(armorPiece, armorList[i])) {
                        materialsUsed += materialPerPiece[i];
                        break;
                    }
                }
            }

            if (materialsUsed >= 1) {
                float maxReduction;
                if (player.dimension.getId() == -1)
                    maxReduction = (float) (ModConfig.COMMON.armor.damageReductionNether.get() / 100f);
                else
                    maxReduction = (float) (ModConfig.COMMON.armor.damageReductionOther.get() / 100f);
                float reductionPerMaterial = maxReduction / 24f;
                float percentageReduction = reductionPerMaterial * materialsUsed;
                amount = amount * (1f - percentageReduction);
                event.setAmount(amount);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDamageEntity(LivingHurtEvent event) {
        if (event.getEntityLiving().world.isRemote)
            return;

        //Check if is not player attacking an entity
        if (!(event.getSource().getTrueSource() instanceof ServerPlayerEntity))
            return;

        //Check if player is holding a vulcanite weapon
        ServerPlayerEntity player = (ServerPlayerEntity) event.getSource().getTrueSource();
        ItemStack heldItem = player.getHeldItemMainhand();


        ItemStack[] vulcaniteWeapons = {
                new ItemStack(ModItems.vulcaniteSword),
                new ItemStack(ModItems.vulcaniteAxe),
                new ItemStack(ModItems.vulcanitePickaxe),
                new ItemStack(ModItems.vulcaniteShovel)

        };

        boolean hasVulcaniteWeapon = false;
        for (ItemStack weapon : vulcaniteWeapons) {
            if (ItemStack.areItemsEqualIgnoreDurability(heldItem, weapon)) {
                hasVulcaniteWeapon = true;
                break;
            }
        }
        if (!hasVulcaniteWeapon)
            return;

        Entity target = event.getEntity();
        if (!(target instanceof LivingEntity))
            return;
        //Check if entity is fire immune or if has fire restistance
        LivingEntity entity = (LivingEntity) target;
        if (!entity.isImmuneToFire() && !entity.isPotionActive(Effects.FIRE_RESISTANCE))
            return;

        //Define bonus damage
        float damageDealth = event.getAmount();
        float baseBonus = (float) (ModConfig.COMMON.toolsAndWeapons.bonusStats.damage.get() / 100f);
        float fireAspectBonus = 0;


        //Check if player has fire aspect enchantment
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(heldItem);

        if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.FIRE_ASPECT)) {
            int fireAspectLevel = enchantments.get(Enchantments.FIRE_ASPECT);
            fireAspectBonus = (float) (ModConfig.COMMON.toolsAndWeapons.bonusStats.damageFireAspect.get() / 100f * fireAspectLevel);
        }

        //Calculate bonus damage
        float bonusDamageDealth = damageDealth * (baseBonus + fireAspectBonus);

        //Set new damage
        event.setAmount(damageDealth + bonusDamageDealth);
    }
}