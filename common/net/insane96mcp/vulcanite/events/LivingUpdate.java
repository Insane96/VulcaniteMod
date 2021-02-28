package net.insane96mcp.vulcanite.events;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.block.SolidifiedLavaBlock;
import net.insane96mcp.vulcanite.init.ModBlocks;
import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class LivingUpdate {
    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingUpdateEvent event) {
        if (!(event.getEntity() instanceof EntityPlayer))
            return;

        EntityPlayer player = (EntityPlayer) event.getEntity();
        ItemStack[] armorList = new ItemStack[]{
                new ItemStack(ModItems.vulcaniteHelmetItem),
                new ItemStack(ModItems.vulcaniteChestplateItem),
                new ItemStack(ModItems.vulcaniteLeggingsItem),
                new ItemStack(ModItems.vulcaniteBootsItem)
        };

        int armorPieces = 0;
        Iterable<ItemStack> playerArmor = player.getArmorInventoryList();
        for (ItemStack armorPiece : playerArmor){
            for (ItemStack itemStack : armorList){
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

        //TODO: Fix this
        float extensionBelow = worldIn.getBlockState(pos.down()).isSideSolid() ? 1.0f : 1.5f;

        int blocksPlaced = 0; BlockStateContainer

        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1, -extensionBelow, -1), pos.add(1, 2, 1))){
            IBlockState currentState = worldIn.getBlockState(blockPos);
            //TODO: BlockFluidBase is probably not the right class. :(
            boolean isFull = currentState.getBlock() == Blocks.LAVA && currentState.getValue(BlockFluidBase.LEVEL) == 0;
            Block solidifiedLavaBlock = isFull ? ModBlocks.solidifiedLavaBlock : ModBlocks.solidifiedFlowingLavaBlock;
            IBlockState solidifiedLavaState = solidifiedLavaBlock.getDefaultState().withProperty(SolidifiedLavaBlock.AGE, 4 - armorPieces);
            //TODO: Make sure it doesn't use IBlockState, but a proper class.
            //TODO: Fix this statement/
            if (currentState.getMaterial() == Material.LAVA && solidifiedLavaState.isValidPosition(worldIn, blockPos) && worldIn.checkBlockCollision(solidifiedLavaState, blockPos, ISelectionContext.dummy()) && !(net.minecraftforge.event.ForgeEventFactory.onPlayerBlockPlace(player, new net.minecraftforge.common.util.BlockSnapshot(worldIn, blockPos, currentState), EnumFacing.UP, EnumHand.MAIN_HAND))) {
                worldIn.setBlockState(blockPos, solidifiedLavaState);
                if (isFull) {
                    worldIn.scheduleUpdate(blockPos, ModBlocks.solidifiedLavaBlock, MathHelper.getInt(worldIn.rand, 8, 15));
                }
                else {
                    worldIn.scheduleUpdate(blockPos, ModBlocks.solidifiedFlowingLavaBlock, MathHelper.getInt(worldIn.rand, 8, 15));
                }
                blocksPlaced++;
                worldIn.playSound(player, blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.11f, 0.6f);
            }
        }

        //TODO: Figure this out.
        for (ItemStack armorPiece : playerArmor) {
            armorPiece.damageItem(blocksPlaced / 4, player, p -> p.sendBreakAnimation(EntityMob.getSlotForItemStack(armorPiece)));
        }
    }
}
