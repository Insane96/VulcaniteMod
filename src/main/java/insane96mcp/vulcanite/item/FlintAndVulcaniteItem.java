package insane96mcp.vulcanite.item;

import insane96mcp.vulcanite.setup.ModConfig;
import insane96mcp.vulcanite.setup.Strings;
import insane96mcp.vulcanite.util.KeyboardUtil;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class FlintAndVulcaniteItem extends FlintAndSteelItem {

	public static final int DURABILITY = 80;

	public FlintAndVulcaniteItem() {
		super(new Properties().group(ItemGroup.TOOLS).defaultMaxDamage(DURABILITY).isImmuneToFire());
	}

    @Override
    public ActionResultType onItemUse(ItemUseContext itemUseContext) {
        World world = itemUseContext.getWorld();
        BlockPos pos = itemUseContext.getPos();
        PlayerEntity player = itemUseContext.getPlayer();
        Hand hand = itemUseContext.getHand();

        if (world.getBlockState(pos).getBlock() == Blocks.TNT) {
            if (!world.isRemote) {
                TNTEntity entityTNTPrimed = new TNTEntity(world, (float) pos.getX() + 0.5F, pos.getY(), (float) pos.getZ() + 0.5F, player);
                if (ModConfig.COMMON.toolsAndWeapons.flintAndVulcanite.tntIgnitesFaster.get())
                    entityTNTPrimed.setFuse(40);
                world.addEntity(entityTNTPrimed);
                world.playSound(null, entityTNTPrimed.getPosX(), entityTNTPrimed.getPosY(), entityTNTPrimed.getPosZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            player.getHeldItem(hand).damageItem(1, player, (playerEntity) -> playerEntity.sendBreakAnimation(hand));
            player.swingArm(hand);

            return ActionResultType.SUCCESS;
        }

        return super.onItemUse(itemUseContext);
    }

    //Changed this from a boolean to an ActionResultType. Compare this to the 1.15.2 version to see all changes.
    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target,
                                            Hand hand) {
        if (target.isImmuneToFire())
            //Changed return false to ActionResultType.FAIL
            return ActionResultType.FAIL;

        if (target instanceof ServerPlayerEntity) {
            ServerPlayerEntity targetPlayer = (ServerPlayerEntity) target;
            if (!playerIn.canAttackPlayer(targetPlayer))
                //Changed return false to ActionResultType.FAIL
                return ActionResultType.FAIL;
        }

        int timeOnFire = ModConfig.COMMON.toolsAndWeapons.flintAndVulcanite.secondsOnFire.get();

        int flameLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack);

        timeOnFire += flameLevel * timeOnFire;

        target.setFire(timeOnFire);
        target.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 0.0f);

        if (target instanceof CreeperEntity) {
            CreeperEntity creeper = (CreeperEntity) target;
            creeper.ignite();
        }

        playerIn.swingArm(hand);
        stack.damageItem(ModConfig.COMMON.toolsAndWeapons.flintAndVulcanite.durabilityOnUse.get(), playerIn, playerEntity -> playerEntity.sendBreakAnimation(hand));

        playerIn.world.playSound(playerIn, target.getPosition(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0f, playerIn.world.rand.nextFloat() * 0.4F + 0.8F);
        //Changed return true to ActionResultType.SUCCES
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.equals(Enchantments.FLAME))
            return true;
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(book);

        if (enchantments.containsKey(Enchantments.FLAME))
            return true;

        return super.isBookEnchantable(stack, book);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
	    if (KeyboardUtil.isHoldingShift()){
            tooltip.add(new StringTextComponent(I18n.format(Strings.Tooltips.FlintAndVulcanite.setOnFire)));
        } else {
	        tooltip.add(new StringTextComponent(I18n.format(Strings.Tooltips.HoldShiftForMoreInfo.holdShiftForMoreInfo)));
        }
    }
}
