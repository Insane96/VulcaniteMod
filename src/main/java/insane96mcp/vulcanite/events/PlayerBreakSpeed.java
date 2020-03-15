package insane96mcp.vulcanite.events;


import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.setup.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class PlayerBreakSpeed {

	@SubscribeEvent
	public static void playerBreakSpeedEvent(PlayerEvent.BreakSpeed event) {
		PlayerEntity player = event.getPlayer();

		if (player.dimension.getId() != -1)
			return;

		ItemStack[] validEfficencyBoost = new ItemStack[]{
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
		speed += event.getOriginalSpeed() * 3.25;//ModConfig.COMMON.toolsAndWeapons.bonusStats.efficiency.get() / 100f;
		event.setNewSpeed(speed);
	}
}
