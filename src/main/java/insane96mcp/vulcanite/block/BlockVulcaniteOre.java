package insane96mcp.vulcanite.block;

import insane96mcp.vulcanite.setup.ModConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeBlock;

public class BlockVulcaniteOre extends OreBlock implements IForgeBlock {

    public BlockVulcaniteOre(String id) {
        super(Properties.create(Material.ROCK).hardnessAndResistance(6.0f, 10));

        setRegistryName(id);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (ModConfig.COMMON.misc.vulcaniteBlockTimeOnFire.get() == 0)
            return;

        entityIn.setFire(ModConfig.COMMON.misc.vulcaniteBlockTimeOnFire.get() / 2);
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
