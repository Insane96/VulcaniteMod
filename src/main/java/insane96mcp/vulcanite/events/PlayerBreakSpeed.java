package insane96mcp.vulcanite.events;


import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.setup.ModConfig;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.Tag;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class PlayerBreakSpeed {

	//TODO: Rewrite.

	@SubscribeEvent
	public static void playerBreakSpeedEvent(PlayerEvent.BreakSpeed event) {
		Player player = event.getPlayer();

		//REPLACED player.dimension.getId() != -1 WITH !player.getEntityWorld().getDimensionKey().equals(World.THE_NETHER)
		if (!player.getCommandSenderWorld().dimension().equals(Level.NETHER))
			return;
		ItemStack heldStack = player.getMainHandItem();
		Block block = event.getState().getBlock();
		Set<ToolType> types = heldStack.getItem().getToolTypes(heldStack);
		boolean isToolEffective = false;
		for (ToolType toolType : types) {
			if (block.isToolEffective(event.getState(), toolType)) {
				isToolEffective = true;
				break;
			}
		}

		Tag<Item> moreEfficientTools = ItemTags.getAllTags().getTag(new ResourceLocation(Vulcanite.MOD_ID, "more_efficient_tools"));
		if (moreEfficientTools == null)
			return;
		boolean isInTag = heldStack.getItem().is(moreEfficientTools);
		if (isInTag && isToolEffective) {
			float speed = event.getOriginalSpeed();
			speed += event.getOriginalSpeed() * ModConfig.COMMON.toolsAndWeapons.bonusStats.efficiency.get() / 100f;
			event.setNewSpeed(speed);
		}
	}
}
