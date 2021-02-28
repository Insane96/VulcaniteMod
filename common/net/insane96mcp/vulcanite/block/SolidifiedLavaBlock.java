package net.insane96mcp.vulcanite.block;

import net.insane96mcp.vulcanite.state.properties.BlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ServerWorldEventHandler;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class SolidifiedLavaBlock extends Block {

    public static final PropertyInteger AGE = BlockStateProperties.AGE_0_3;
    protected static final AxisAlignedBB collisionShape = new AxisAlignedBB(0.05D, 0.05D, 0.05D, 15.95D, 16.0D, 15.95D);
    public boolean flowing;

    public SolidifiedLavaBlock(boolean isFlowing) {
        super(Material.ROCK);
        this.setTickRandomly(true);
        this.blockHardness = 1.25f;
        this.blockResistance = 1.25f;
        this.setSoundType(SoundType.STONE);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(AGE, 0)); //This is the right method to call. However, it does direct to the interface instead of the class.
        this.flowing = isFlowing;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!this.tryMelt(state, worldIn, pos)) {
            worldIn.scheduleUpdate(pos, this, MathHelper.getInt(rand, 6, 12));
        }
    }

    //TODO: Test!!!
    private boolean tryMelt(IBlockState state, World worldIn, BlockPos pos) {
        int i = state.getValue(AGE);
        if (i < 3) {
            worldIn.setBlockState(pos, state.withProperty(AGE, i + 1), 2);
            return false;
        }
        else {
            if (!this.flowing)
                worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState());
            else
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            return true;
        }
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.25f);
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        IBlockState state = world.getBlockState(pos);
        if (!(state.getBlock() instanceof SolidifiedLavaBlock))
            return;
        int age = state.getValue(AGE);
        entity.setFire(3 + age);
    }

    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
        int age = state.getValue(AGE);
        entity.setFire(3 + age);
    }

    public static AxisAlignedBB getCollisionShape() {
        return collisionShape;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        int age = state.getValue(AGE);
        return 3 * (age + 1);
    }

    //TODO: Find suitable method for this part of the 1.15 version.
//    @Override
//    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
//        super.fillStateContainer(builder.add(AGE));
//    }
//
}
