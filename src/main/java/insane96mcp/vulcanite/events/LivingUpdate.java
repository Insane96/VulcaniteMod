package insane96mcp.vulcanite.events;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.block.SolidifiedLavaBlock;
import insane96mcp.vulcanite.setup.ModBlocks;
import insane96mcp.vulcanite.setup.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
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
		if (!(event.getEntity() instanceof Player))
			return;
		Player player = (Player) event.getEntity();
		ItemStack[] armorList = new ItemStack[]{
				new ItemStack(ModItems.VULCANITE_HELMET.get()),
				new ItemStack(ModItems.VULCANITE_CHESTPLATE.get()),
				new ItemStack(ModItems.VULCANITE_LEGGINGS.get()),
				new ItemStack(ModItems.VULCANITE_BOOTS.get())
		};
		int armorPieces = 0;
		Iterable<ItemStack> playerArmor = player.getArmorSlots();
		for (ItemStack armorPiece : playerArmor) {
			for (ItemStack itemStack : armorList) {
				if (ItemStack.isSameIgnoreDurability(armorPiece, itemStack)) {
					armorPieces++;
					break;
				}
			}
		}
		if (armorPieces < 1)
			return;
		BlockPos pos = player.blockPosition();
		Level world = player.level;
		Random rand = world.random;
		float extensionBelow = 1.0f;
		if (world.getBlockState(pos).canOcclude())
			extensionBelow = 0.0f;
		else if (!world.getBlockState(pos.below()).canOcclude())
			extensionBelow = 1.5f;
		//float extensionBelow = world.getBlockState(pos.down()).isSolid() || world.getBlockState(pos).isSolid() ? 1.0f : 1.5f;
		int blocksPlaced = 0;
		for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, -extensionBelow, -1), pos.offset(1, 2, 1))) {
			BlockState currentState = world.getBlockState(blockPos);
			boolean isFull = currentState.getBlock() == Blocks.LAVA && currentState.getValue(LiquidBlock.LEVEL) == 0;
			Block solidifiedLavaBlock = isFull ? ModBlocks.SOLIDIFIED_LAVA.get() : ModBlocks.SOLIDIFIED_FLOWING_LAVA.get();
			BlockState solidifiedLavaState = solidifiedLavaBlock.defaultBlockState().setValue(SolidifiedLavaBlock.AGE, 4 - armorPieces);

			//CHANGED new net.minecraftforge.common.util.BlockSnapshot(world, blockPos, currentState) to BlockSnapshot.create(world, blockPos) TODO: FIGURE OUT WHY WE DON'T USE currentState ANYMORE AND FIND OUT THE CONSEQUENCES
			if (currentState.getMaterial() == Material.LAVA &&
					solidifiedLavaState.canSurvive(world, blockPos) &&
					world.isUnobstructed(solidifiedLavaState, blockPos, CollisionContext.empty()) &&
					!ForgeEventFactory.onBlockPlace(player, BlockSnapshot.create(world.dimension(), world, blockPos), Direction.UP) &&
					player.getAbilities().mayBuild //Spectator mode check - Checks whether the player is allowed to edit the world.
			) {

				world.setBlockAndUpdate(blockPos, solidifiedLavaState);

				if (isFull) {
					world.getBlockTicks().scheduleTick(blockPos, ModBlocks.SOLIDIFIED_LAVA.get(), Mth.nextInt(world.random, 8, 15));
				} else {
					world.getBlockTicks().scheduleTick(blockPos, ModBlocks.SOLIDIFIED_FLOWING_LAVA.get(), Mth.nextInt(world.random, 8, 15));
				}

				blocksPlaced++;
				world.playSound(player, blockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.11f, 0.6f);
			}
		}

		List<ItemStack> damageableArmorPiece = new ArrayList<>();
		for (ItemStack armorPiece : playerArmor) {
			for (ItemStack vulcaniteArmorPiece : armorList) {
				if (ItemStack.isSameIgnoreDurability(armorPiece, vulcaniteArmorPiece)) {
					damageableArmorPiece.add(armorPiece);
				}
			}
		}
		for (int b = 0; b < blocksPlaced; b++) {
			ItemStack armorToDamage = damageableArmorPiece.get(rand.nextInt(damageableArmorPiece.size()));
			armorToDamage.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(Mob.getEquipmentSlotForItem(armorToDamage)));
		}
	}
}