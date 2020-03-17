package insane96mcp.vulcanite.events;


import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.setup.ModConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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

		ItemStack mainHand = player.getHeldItemMainhand();

		System.out.println(mainHand.getItem().getTags());

		if (mainHand.getItem().getTags().contains(new ResourceLocation(Vulcanite.MOD_ID, "more_efficient_tools"))
				&& mainHand.getItem().getDestroySpeed(mainHand, event.getState()) > 1.0f) {
			float speed = event.getOriginalSpeed();
			speed += event.getOriginalSpeed() * ModConfig.COMMON.toolsAndWeapons.bonusStats.efficiency.get() / 100f;
			event.setNewSpeed(speed);
		}
	}
}
