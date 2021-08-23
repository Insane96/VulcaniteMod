package insane96mcp.vulcanite.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class SolidifiedLavaBlock extends Block {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	protected static final VoxelShape collisionShape = Block.box(0.05D, 0.05D, 0.05D, 15.95D, 16.0D, 15.95D);
	public boolean flowing;

	public SolidifiedLavaBlock(boolean isFlowing) {
		super(Block.Properties.of(Material.STONE).randomTicks().strength(1.25f).sound(SoundType.STONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
		this.flowing = isFlowing;
	}

	@SuppressWarnings("deprecation")
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (!this.tryMelt(state, worldIn, pos)) {
			worldIn.getBlockTicks().scheduleTick(pos, this, Mth.nextInt(rand, 6, 12));
		}
	}

	private boolean tryMelt(BlockState state, Level worldIn, BlockPos pos) {
		int i = state.getValue(AGE);
		if (i < 3) {
			worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
			return false;
		}
		else {
			if (!this.flowing)
				worldIn.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
			else
				worldIn.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
			return true;
		}
	}

	@Override
	public void fallOn(Level worldIn, BlockState blockState, BlockPos pos, Entity entityIn, float fallDistance) {
		super.fallOn(worldIn, blockState, pos, entityIn, fallDistance * 0.25F);
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState blockState, Entity entity) {
		BlockState state = world.getBlockState(pos);
		if (!(state.getBlock() instanceof SolidifiedLavaBlock))
			return;
		int age = state.getValue(AGE);
		entity.setSecondsOnFire(3 + age);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void entityInside(BlockState state, Level world, BlockPos blockPos, Entity entity) {
		int age = state.getValue(AGE);
		entity.setSecondsOnFire(3 + age);
	}

	@Override
	@SuppressWarnings("deprecation")
	public VoxelShape getCollisionShape(BlockState p_220071_1_, BlockGetter p_220071_2_, BlockPos p_220071_3_, CollisionContext p_220071_4_) {
		return collisionShape;
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
		int age = state.getValue(AGE);
		return 3 * (age + 1);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder.add(AGE));
	}
}