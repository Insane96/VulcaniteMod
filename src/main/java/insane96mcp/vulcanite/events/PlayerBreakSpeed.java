package insane96mcp.vulcanite.events;


import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.setup.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class PlayerBreakSpeed {

	@SubscribeEvent
	public static void playerBreakSpeedEvent(PlayerEvent.BreakSpeed event) {
		PlayerEntity player = event.getPlayer();

		//REPLACED player.dimension.getId() != -1 WITH !player.getEntityWorld().getDimensionKey().equals(World.THE_NETHER)
		if (!player.getEntityWorld().getDimensionKey().equals(World.THE_NETHER))
			return;
		ItemStack heldStack = player.getHeldItemMainhand();
		Block block = event.getState().getBlock();
		Set<ToolType> types = heldStack.getItem().getToolTypes(heldStack);
		boolean isToolEffective = false;
		for (ToolType toolType : types) {
			if (block.isToolEffective(event.getState(), toolType)) {
				isToolEffective = true;
				break;
			}
		}
		//CHANGED Tag<Item> to ITag<Item> NOTE: THIS SEEMS LIKE AN ILLEGAL FIX AND SHOULD BE TESTED
		ITag<Item> moreEfficientTools = ItemTags.getCollection().get(new ResourceLocation(Vulcanite.MOD_ID, "more_efficient_tools"));
		if (moreEfficientTools == null)
			return;
		boolean isInTag = heldStack.getItem().isIn(moreEfficientTools);
		if (isInTag && isToolEffective) {
			float speed = event.getOriginalSpeed();
			speed += event.getOriginalSpeed() * ModConfig.COMMON.toolsAndWeapons.bonusStats.efficiency.get() / 100f;
			event.setNewSpeed(speed);
		}
	}
}
