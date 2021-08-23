package insane96mcp.vulcanite.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeBlock;

public class VulcaniteOreBlock extends OreBlock implements IForgeBlock {

    public VulcaniteOreBlock() {
        super(Properties.of(Material.STONE).strength(3f, 3).sound(SoundType.NETHER_ORE));
    }

    @Override
    public void stepOn(Level worldIn, BlockPos pos, BlockState blockState, Entity entityIn) {
        if (!entityIn.fireImmune() && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entityIn)) {
            entityIn.hurt(DamageSource.HOT_FLOOR, 1.0F);
        }
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 2;
    }
}