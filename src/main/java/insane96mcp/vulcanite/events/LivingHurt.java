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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class LivingHurt {

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
                new ItemStack(ModItems.VULCANITE_SWORD.get()),
                new ItemStack(ModItems.VULCANITE_AXE.get()),
                new ItemStack(ModItems.VULCANITE_PICKAXE.get()),
                new ItemStack(ModItems.VULCANITE_SHOVEL.get())
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