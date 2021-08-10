package insane96mcp.vulcanite.block;

import insane96mcp.vulcanite.setup.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeBlock;

public class VulcaniteBlock extends Block implements IForgeBlock {

    public VulcaniteBlock() {
        super(Block.Properties.create(Material.IRON, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(5f, 6f).sound(SoundType.METAL));
    }

    @Override
    public boolean isFireSource(BlockState state, IWorldReader world, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (ModConfig.COMMON.misc.vulcaniteBlockTimeOnFire.get() == 0)
            return;

        entityIn.setFire(ModConfig.COMMON.misc.vulcaniteBlockTimeOnFire.get());
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 2;
    }

    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.PICKAXE;
    }
}