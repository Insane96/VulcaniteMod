package net.insane96mcp.vulcanite.events;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.lib.Properties;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class LivingHurt {
	
	static ItemStack[] armorList = new ItemStack[] {
		new ItemStack(ModItems.vulcaniteHelmetItem), 
		new ItemStack(ModItems.vulcaniteChestplateItem), 
		new ItemStack(ModItems.vulcaniteLeggingsItem), 
		new ItemStack(ModItems.vulcaniteBootsItem)
	};
	
	static DamageSource[] validSources = new DamageSource[] {
		DamageSource.IN_FIRE, 
		DamageSource.ON_FIRE, 
		DamageSource.HOT_FLOOR, 
		DamageSource.LAVA
	};

	@SubscribeEvent
	public static void OnPlayerHurt(LivingHurtEvent event) {
		if (event.getEntityLiving().world.isRemote)
			return;
		
		if (!(event.getEntityLiving() instanceof EntityPlayerMP))
			return;
		
		EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
		DamageSource source = event.getSource();
		
		float[] materialPerPiece = new float[] { 5, 8, 7, 4 };
		
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
    			if (player.dimension == -1) 
    				maxReduction = Properties.config.armor.damageReductionNether / 100f;
    			else 
    				maxReduction = Properties.config.armor.damageReductionOther / 100f;
		    	float reductionPerMaterial = maxReduction / 24f;
		    	float percentageReduction = reductionPerMaterial * materialsUsed;
		    	amount = amount * (1f - percentageReduction);
		        event.setAmount(amount);
		    }
		}
	}
	
	private static ItemStack[] vulcaniteWeapons = {
		new ItemStack(ModItems.vulcaniteSwordItem), 
		new ItemStack(ModItems.vulcaniteAxeItem)
	};

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
		
		boolean hasVulcaniteWeapon = false;
		for (ItemStack weapon : vulcaniteWeapons) {
			if (ItemStack.areItemsEqualIgnoreDurability(heldItem, weapon)) {
				hasVulcaniteWeapon = true;
				break;
			}
		}
		if (!hasVulcaniteWeapon)
			return;
		
		//Check if entity is fire immune
		Entity target = event.getEntity();
		if (!(target instanceof EntityLivingBase))
			return;
		
		EntityLivingBase entity = (EntityLivingBase)target;
		if (!entity.isImmuneToFire())
			return;
		
		//Define bonus damage
		float damageDealth = event.getAmount();
		float baseBonus = Properties.config.toolsAndWeapons.bonusStats.damage / 100f;
		float fireAspectBonus = 0;
		
		
		//Check if player has fire aspect enchantment
		NBTTagList enchantments = heldItem.getEnchantmentTagList();
		
		if (enchantments != null){
			int fireAspectLevel = 0;
			for (int i = 0; i < enchantments.tagCount(); i++) {
				if (enchantments.getCompoundTagAt(i).getShort("id") == Enchantment.getEnchantmentID(Enchantments.FIRE_ASPECT))
					fireAspectLevel = enchantments.getCompoundTagAt(i).getShort("lvl");
			}
			
			fireAspectBonus = Properties.config.toolsAndWeapons.bonusStats.damageFireAspect / 100f * fireAspectLevel;
		}
		
		//Calculate bonus damage
		float bonusDamageDealth = damageDealth * (baseBonus + fireAspectBonus);
		
		//Set new damage
		event.setAmount(damageDealth + bonusDamageDealth);
	}
}
