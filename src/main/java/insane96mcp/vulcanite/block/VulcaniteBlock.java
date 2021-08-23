package insane96mcp.vulcanite.block;

import insane96mcp.vulcanite.setup.ModConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeBlock;

public class VulcaniteBlock extends Block implements IForgeBlock {

    public VulcaniteBlock() {
        super(Block.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_ORANGE).strength(5f, 6f).sound(SoundType.METAL).requiresCorrectToolForDrops());
    }

    @Override
    public boolean isFireSource(BlockState state, LevelReader world, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    public void stepOn(Level worldIn, BlockPos pos, BlockState blockState, Entity entityIn) {
        if (ModConfig.COMMON.misc.vulcaniteBlockTimeOnFire.get() == 0)
            return;

        entityIn.setSecondsOnFire(ModConfig.COMMON.misc.vulcaniteBlockTimeOnFire.get());
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 2;
    }
}