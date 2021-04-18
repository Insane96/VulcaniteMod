package insane96mcp.vulcanite.block;

import insane96mcp.vulcanite.setup.ModSoundTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeBlock;

public class VulcaniteOreBlock extends OreBlock implements IForgeBlock {

    public VulcaniteOreBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(3f, 3).sound(ModSoundTypes.VULCANITE_ORE));
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (!entityIn.isImmuneToFire() && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entityIn)) {
            entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
        }
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
