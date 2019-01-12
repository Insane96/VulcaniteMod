package net.insane96mcp.vulcanite.events;

import java.util.List;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.lib.Properties;
import net.insane96mcp.vulcanite.network.BlockBreak;
import net.insane96mcp.vulcanite.network.PacketHandler;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class HarvestDrops {
	
	private static ItemStack[] validTools = new ItemStack[] {
			new ItemStack(ModItems.vulcanitePickaxeItem),
			new ItemStack(ModItems.vulcaniteAxeItem),
			new ItemStack(ModItems.vulcaniteShovelItem),
	};
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void EventHarvestDrops(HarvestDropsEvent event) {
		EntityPlayerMP player = (EntityPlayerMP) event.getHarvester();
		
		if (player == null)
			return;
		
		ItemStack hand = player.getHeldItemMainhand();
		
		boolean hasVulcaniteTool = false;
		for (ItemStack tool : validTools) {
			if (ItemStack.areItemsEqualIgnoreDurability(hand, tool))
				hasVulcaniteTool = true;
		}
		
		if (!hasVulcaniteTool)
			return;
		
		float experience = 0f;
		boolean smelted = false;
		
		List<ItemStack> drops = event.getDrops();
		for (int i = 0; i < drops.size(); i++) {
			ItemStack smeltingResult = FurnaceRecipes.instance().getSmeltingResult(drops.get(i)).copy();
			if (!smeltingResult.isEmpty()) {
				drops.set(i, smeltingResult);
				experience += FurnaceRecipes.instance().getSmeltingExperience(smeltingResult);
				smelted = true;
			}
		}
		
		if (smelted) {
			PacketHandler.SendToClient(new BlockBreak(event.getPos()), player);
			if (player.dimension != -1)
				player.getHeldItemMainhand().damageItem(1, player);
		}
		
		if (!Properties.config.toolsAndWeapons.bonusStats.shouldDropExperience)
			return;
		if (experience % 1 != 0) {
			int intXp = (int) experience;
			float decimals = experience - intXp;
			
			if (event.getWorld().rand.nextFloat() < decimals)
				experience = (float) Math.ceil(experience);
			else 
				experience = (float) Math.floor(experience);
		}
		
		if (experience > 0) {
			EntityXPOrb xpOrb = new EntityXPOrb(event.getWorld(), event.getPos().getX() + .5f, event.getPos().getY() + .5f, event.getPos().getZ() + .5f, (int) experience);
			event.getWorld().spawnEntity(xpOrb);
		}
	}

}
