package net.insane96mcp.vulcanite.events;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.ModConfig;
import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class PlayerBreakSpeed {

	@SubscribeEvent
	public static void PlayerBreakSpeedEvent(PlayerEvent.BreakSpeed event) {
		EntityPlayer player = event.getEntityPlayer();
		
		if (player.dimension.getId() != -1)
			return;

		ItemStack[] validEfficencyBoost = new ItemStack[] {
			new ItemStack(ModItems.vulcanitePickaxe),
			new ItemStack(ModItems.vulcaniteAxe),
			new ItemStack(ModItems.vulcaniteShovel),
		};
		
		ItemStack mainHand = player.getHeldItemMainhand();
		boolean isValid = false;
		
		for (ItemStack itemStack : validEfficencyBoost) {
			if (!ItemStack.areItemsEqualIgnoreDurability(mainHand, itemStack))
				continue;

			if (itemStack.getItem().getDestroySpeed(itemStack, event.getState()) > 1.0f) {
				isValid = true;
				break;
			}
		}
		
		if (!isValid)
			return;
		
		float speed = event.getOriginalSpeed();
		speed += event.getOriginalSpeed() * ModConfig.ToolsAndWeapons.BonusStats.efficiency.get() / 100f;
		event.setNewSpeed(speed);
	}
}
