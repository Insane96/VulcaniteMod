package net.insane96mcp.vulcanite.events;

import java.util.List;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.network.PacketBlockBreak;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.registries.RegistryManager;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class HarvestDrops {
	
	private static ItemStack[] validTools = new ItemStack[] {
		new ItemStack(ModItems.vulcanitePickaxe),
		new ItemStack(ModItems.vulcaniteAxe),
		new ItemStack(ModItems.vulcaniteShovel)
	};
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void EventHarvestDrops(HarvestDropsEvent event) {
		if (event.getWorld().isRemote())
			return;
		
		EntityPlayerMP player = (EntityPlayerMP) event.getHarvester();
		
		if (player == null)
			return;
		
		//Returns AIR
		System.out.println(new ItemStack(ModItems.vulcaniteShovel).getDisplayName().getString());
		
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
			System.out.println(RegistryManager.VANILLA.getRegistry(FurnaceRecipe.class).getKeys());
			/*ItemStack smeltingResult = FurnaceRecipes.instance().getSmeltingResult(drops.get(i)).copy();
			if (!smeltingResult.isEmpty()) {
				drops.set(i, smeltingResult);
				experience += FurnaceRecipes.instance().getSmeltingExperience(smeltingResult);
				smelted = true;
			}*/
		}
		
		if (smelted) {
			Vulcanite.channel.send(PacketDistributor.PLAYER.with(() -> player), new PacketBlockBreak(event.getPos()));
			if (player.dimension.getId() != -1)
				player.getHeldItemMainhand().damageItem(1, player);
		}
		
		/*if (!Properties.config.toolsAndWeapons.bonusStats.shouldDropExperience)
			return;*/
		if (experience % 1 != 0) {
			int intXp = (int) experience;
			float decimals = experience - intXp;
			
			if (event.getWorld().getRandom().nextFloat() < decimals)
				experience = (float) Math.ceil(experience);
			else 
				experience = (float) Math.floor(experience);
		}
		
		if (experience > 0) {
			EntityXPOrb xpOrb = new EntityXPOrb(event.getWorld().getWorld(), event.getPos().getX() + .5f, event.getPos().getY() + .5f, event.getPos().getZ() + .5f, (int) experience);
			event.getWorld().spawnEntity(xpOrb);
		}
	}

}
