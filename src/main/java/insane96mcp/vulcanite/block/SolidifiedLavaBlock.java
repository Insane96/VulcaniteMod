package insane96mcp.vulcanite.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SolidifiedLavaBlock extends Block {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
	protected static final VoxelShape collisionShape = Block.makeCuboidShape(0.05D, 0.05D, 0.05D, 15.95D, 16.0D, 15.95D);
	public boolean flowing;

	public SolidifiedLavaBlock(boolean isFlowing) {
		super(Block.Properties.create(Material.ROCK).tickRandomly().hardnessAndResistance(1.25f).sound(SoundType.STONE));
		this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
		this.flowing = isFlowing;
	}

	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!this.tryMelt(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, MathHelper.nextInt(rand, 6, 12));
		}
	}

	private boolean tryMelt(BlockState state, World worldIn, BlockPos pos) {
		int i = state.get(AGE);
		if (i < 3) {
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
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
		super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.25F);
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		int age = world.getBlockState(pos).get(AGE);
		entity.setFire(3 + age);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos blockPos, Entity entity) {
		int age = state.get(AGE);
		entity.setFire(3 + age);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
		return collisionShape;
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		int age = state.get(AGE);
		return 3 * (age + 1);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder.add(AGE));
	}
}
