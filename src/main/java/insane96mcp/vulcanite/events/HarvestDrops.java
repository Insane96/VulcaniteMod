package insane96mcp.vulcanite.events;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.network.PacketBlockBreak;
import insane96mcp.vulcanite.setup.ModConfig;
import insane96mcp.vulcanite.setup.ModItems;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.Hand;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class HarvestDrops {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void eventHarvestDrops(HarvestDropsEvent event) {
		if (event.getWorld().isRemote())
			return;

		//Cache recipes so

		ServerPlayerEntity player = (ServerPlayerEntity) event.getHarvester();

		if (player == null)
			return;

		ItemStack[] validTools = new ItemStack[]{
				new ItemStack(ModItems.vulcanitePickaxe),
				new ItemStack(ModItems.vulcaniteAxe),
				new ItemStack(ModItems.vulcaniteShovel)
		};

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
		for (ItemStack drop : drops) {
			ItemStack smeltedStack = event.getWorld().getWorld().getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(drop), event.getWorld().getWorld())
					.map(FurnaceRecipe::getRecipeOutput)
					.filter(itemStack -> !itemStack.isEmpty())
					.map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, drop.getCount() * itemStack.getCount()))
					.orElse(drop);

			smelted = drop != smeltedStack;

			if (smelted)
				break;
		}

		if (smelted) {
			Vulcanite.channel.send(PacketDistributor.PLAYER.with(() -> player), new PacketBlockBreak(event.getPos()));
			if (player.dimension.getId() != -1)
				player.getHeldItemMainhand().damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(Hand.MAIN_HAND));
		}

		if (!ModConfig.COMMON.toolsAndWeapons.bonusStats.smeltingDropsExperience.get())
			return;
		if (experience % 1 != 0) {
			int intXp = (int) experience;
			float decimals = experience - intXp;

			if (event.getWorld().getRandom().nextFloat() < decimals)
				experience = (float) Math.ceil(experience);
			else
				experience = (float) Math.floor(experience);
		}

		if (experience > 0) {
			ExperienceOrbEntity xpOrb = new ExperienceOrbEntity(event.getWorld().getWorld(), event.getPos().getX() + .5f, event.getPos().getY() + .5f, event.getPos().getZ() + .5f, (int) experience);
			event.getWorld().addEntity(xpOrb);
		}
	}

}
