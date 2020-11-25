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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
		World worldIn = player.world;
		float extensionBelow = worldIn.getBlockState(pos.down()).isSolid() ? 1.0f : 1.5f;
		int blocksPlaced = 0;
		for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1, -extensionBelow, -1), pos.add(1, 2, 1))) {
			BlockState currentState = worldIn.getBlockState(blockPos);
			boolean isFull = currentState.getBlock() == Blocks.LAVA && currentState.get(FlowingFluidBlock.LEVEL) == 0;
			Block solidifiedLavaBlock = isFull ? ModBlocks.SOLIDIFIED_LAVA.get() : ModBlocks.SOLIDIFIED_FLOWING_LAVA.get();
			BlockState solidifiedLavaState = solidifiedLavaBlock.getDefaultState().with(SolidifiedLavaBlock.AGE, 4 - armorPieces);

			//CHANGED worldIn.placedBlockWouldCollide to func_226663_a_
			//CHANGED new net.minecraftforge.common.util.BlockSnapshot(worldIn, blockPos, currentState) to BlockSnapshot.create(worldIn, blockPos) TODO: FIGURE OUT WHY WE DON'T USE currentState ANYMORE AND FIND OUT THE CONSEQUENCES
			if (currentState.getMaterial() == Material.LAVA && solidifiedLavaState.isValidPosition(worldIn, blockPos) && worldIn.func_226663_a_(solidifiedLavaState, blockPos, ISelectionContext.dummy()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(player, BlockSnapshot.create(worldIn, blockPos), net.minecraft.util.Direction.UP)) {
				worldIn.setBlockState(blockPos, solidifiedLavaState);
				if (isFull) {
					worldIn.getPendingBlockTicks().scheduleTick(blockPos, ModBlocks.SOLIDIFIED_LAVA.get(), MathHelper.nextInt(worldIn.rand, 8, 15));
				}
				else {
					worldIn.getPendingBlockTicks().scheduleTick(blockPos, ModBlocks.SOLIDIFIED_FLOWING_LAVA.get(), MathHelper.nextInt(worldIn.rand, 8, 15));
				}
				blocksPlaced++;
				worldIn.playSound(player, blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.11f, 0.6f);
			}
		}
		for (ItemStack armorPiece : playerArmor) {
			armorPiece.damageItem(blocksPlaced / 4, player, p -> p.sendBreakAnimation(MobEntity.getSlotForItemStack(armorPiece)));
		}
	}
}
