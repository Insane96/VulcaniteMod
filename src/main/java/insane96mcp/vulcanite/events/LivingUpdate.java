package insane96mcp.vulcanite.events;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.block.SolidifiedLavaBlock;
import insane96mcp.vulcanite.setup.ModBlocks;
import insane96mcp.vulcanite.setup.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class LivingUpdate {
	@SubscribeEvent
	public static void onPlayerTick(LivingEvent.LivingUpdateEvent event) {
		if (!(event.getEntity() instanceof PlayerEntity))
			return;
		PlayerEntity player = (PlayerEntity) event.getEntity();
		ItemStack[] armorList = new ItemStack[]{
				new ItemStack(ModItems.VULCANITE_HELMET.get()),
				new ItemStack(ModItems.VULCANITE_CHESTPLATE.get()),
				new ItemStack(ModItems.VULCANITE_LEGGINGS.get()),
				new ItemStack(ModItems.VULCANITE_BOOTS.get())
		};
		int armorPieces = 0;
		Iterable<ItemStack> playerArmor = player.getArmorInventoryList();
		for (ItemStack armorPiece : playerArmor) {
			for (ItemStack itemStack : armorList) {
				if (ItemStack.areItemsEqualIgnoreDurability(armorPiece, itemStack)) {
					armorPieces++;
					break;
				}
			}
		}
		if (armorPieces < 1)
			return;
		BlockPos pos = player.getPosition();
		World world = player.world;
		Random rand = world.rand;
		float extensionBelow = 1.0f;
		if (world.getBlockState(pos).isSolid())
			extensionBelow = 0.0f;
		else if (!world.getBlockState(pos.down()).isSolid())
			extensionBelow = 1.5f;
		//float extensionBelow = world.getBlockState(pos.down()).isSolid() || world.getBlockState(pos).isSolid() ? 1.0f : 1.5f;
		int blocksPlaced = 0;
		for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1, -extensionBelow, -1), pos.add(1, 2, 1))) {
			BlockState currentState = world.getBlockState(blockPos);
			boolean isFull = currentState.getBlock() == Blocks.LAVA && currentState.get(FlowingFluidBlock.LEVEL) == 0;
			Block solidifiedLavaBlock = isFull ? ModBlocks.SOLIDIFIED_LAVA.get() : ModBlocks.SOLIDIFIED_FLOWING_LAVA.get();
			BlockState solidifiedLavaState = solidifiedLavaBlock.getDefaultState().with(SolidifiedLavaBlock.AGE, 4 - armorPieces);

			//CHANGED new net.minecraftforge.common.util.BlockSnapshot(world, blockPos, currentState) to BlockSnapshot.create(world, blockPos) TODO: FIGURE OUT WHY WE DON'T USE currentState ANYMORE AND FIND OUT THE CONSEQUENCES
			if (currentState.getMaterial() == Material.LAVA && solidifiedLavaState.isValidPosition(world, blockPos) && world.placedBlockCollides(solidifiedLavaState, blockPos, ISelectionContext.dummy()) && !ForgeEventFactory.onBlockPlace(player, BlockSnapshot.create(world.getDimensionKey(), world, blockPos), Direction.UP)) {
				world.setBlockState(blockPos, solidifiedLavaState);
				if (isFull) {
					world.getPendingBlockTicks().scheduleTick(blockPos, ModBlocks.SOLIDIFIED_LAVA.get(), MathHelper.nextInt(world.rand, 8, 15));
				}
				else {
					world.getPendingBlockTicks().scheduleTick(blockPos, ModBlocks.SOLIDIFIED_FLOWING_LAVA.get(), MathHelper.nextInt(world.rand, 8, 15));
				}
				blocksPlaced++;
				world.playSound(player, blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.11f, 0.6f);
			}
		}


		List<ItemStack> damageableArmorPiece = new ArrayList<>();
		for (ItemStack armorPiece : playerArmor) {
			for (ItemStack vulcaniteArmorPiece : armorList) {
				if (ItemStack.areItemsEqualIgnoreDurability(armorPiece, vulcaniteArmorPiece)) {
					damageableArmorPiece.add(armorPiece);
				}
			}
		}
		for (int b = 0; b < blocksPlaced; b++) {
			ItemStack armorToDamage = damageableArmorPiece.get(rand.nextInt(damageableArmorPiece.size()));
			armorToDamage.damageItem(1, player, p -> p.sendBreakAnimation(MobEntity.getSlotForItemStack(armorToDamage)));
		}
	}
}
