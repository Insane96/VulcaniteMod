package net.insane96mcp.vulcanite.events;

import java.util.Map;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.ModConfig;
import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class LivingHurt {

	@SubscribeEvent
	public static void OnPlayerHurt(LivingHurtEvent event) {
		if (event.getEntityLiving().world.isRemote)
			return;
		
		if (!(event.getEntityLiving() instanceof EntityPlayerMP))
			return;
		
		EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
		DamageSource source = event.getSource();
		
		float[] materialPerPiece = new float[] { 5, 8, 7, 4 };
		
		DamageSource[] validSources = new DamageSource[] {
			DamageSource.IN_FIRE, 
			DamageSource.ON_FIRE, 
			DamageSource.HOT_FLOOR, 
			DamageSource.LAVA
		};

		ItemStack[] armorList = new ItemStack[] {
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
    				maxReduction = (float) (ModConfig.Armor.damageReductionNether.get() / 100f);
    			else 
    				maxReduction = (float) (ModConfig.Armor.damageReductionOther.get() / 100f);
		    	float reductionPerMaterial = maxReduction / 24f;
		    	float percentageReduction = reductionPerMaterial * materialsUsed;
		    	amount = amount * (1f - percentageReduction);
		        event.setAmount(amount);
		    }
		}
	}

	@SubscribeEvent
	public static void OnPlayerDamageEntity(LivingHurtEvent event) {
		if (event.getEntityLiving().world.isRemote)
			return;
		
		//Check if is not player attacking an entity
		if (!(event.getSource().getTrueSource() instanceof EntityPlayerMP))
			return;
		
		//Check if player is holding a vulcanite weapon
		EntityPlayerMP player = (EntityPlayerMP) event.getSource().getTrueSource();
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
		if (!(target instanceof EntityLivingBase))
			return;
		
		//Check if entity is fire immune
		EntityLivingBase entity = (EntityLivingBase)target;
		if (!entity.isImmuneToFire())
			return;
		
		//Define bonus damage
		float damageDealth = event.getAmount();
		float baseBonus = (float) (ModConfig.ToolsAndWeapons.BonusStats.damage.get() / 100f);
		float fireAspectBonus = 0;
		
		
		//Check if player has fire aspect enchantment
		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(heldItem);
		
		if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.FIRE_ASPECT)) {
			int fireAspectLevel = enchantments.get(Enchantments.FIRE_ASPECT);
			fireAspectBonus = (float) (ModConfig.ToolsAndWeapons.BonusStats.damageFireAspect.get() / 100f * fireAspectLevel);
		}
		
		//Calculate bonus damage
		float bonusDamageDealth = damageDealth * (baseBonus + fireAspectBonus);
		
		//Set new damage
		event.setAmount(damageDealth + bonusDamageDealth);
	}
}