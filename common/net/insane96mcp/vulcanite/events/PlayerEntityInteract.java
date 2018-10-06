package net.insane96mcp.vulcanite.events;

import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.lib.Properties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerEntityInteract {	
	private static ItemStack flintAndVulcanite = new ItemStack(ModItems.flintAndVulcaniteItem);
	
	@SubscribeEvent
	public static void PlayerEntityInteractEvent(PlayerInteractEvent.EntityInteract event) {
		EntityPlayer player = event.getEntityPlayer();
		ItemStack mainHand = player.getHeldItemMainhand();
		ItemStack offHand = player.getHeldItemOffhand();
		
		if (!(ItemStack.areItemsEqualIgnoreDurability(mainHand, flintAndVulcanite)) && 
			!(ItemStack.areItemsEqualIgnoreDurability(offHand, flintAndVulcanite)))
			return;
		
		Entity target = event.getTarget();
		
		if (!(target instanceof EntityLivingBase))
			return;
		
		EntityLivingBase entityLivingBase = (EntityLivingBase)target;
		
		if (entityLivingBase instanceof EntityPlayerMP && !Properties.Tools.FlintAndVulcanite.pvp)
			return;
		
		if (entityLivingBase.isImmuneToFire())
			return;
		
		entityLivingBase.setFire(Properties.Tools.FlintAndVulcanite.secondsOnFire);
		
		if (entityLivingBase instanceof EntityCreeper) {
			NBTTagCompound ignited = new NBTTagCompound();
			ignited.setByte("ignited", (byte)1);
			entityLivingBase.readEntityFromNBT(ignited);
		}
		
		if (ItemStack.areItemsEqualIgnoreDurability(mainHand, flintAndVulcanite)) {
			player.swingArm(EnumHand.MAIN_HAND);
			mainHand.damageItem(Properties.Tools.FlintAndVulcanite.damageOnUse, player);
		}
		
		else { 
			player.swingArm(EnumHand.OFF_HAND);
			offHand.damageItem(Properties.Tools.FlintAndVulcanite.damageOnUse, player);
		}
		
		event.getWorld().playSound(player, entityLivingBase.getPosition(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0f, event.getWorld().rand.nextFloat() * 0.4F + 0.8F);
	}
}
