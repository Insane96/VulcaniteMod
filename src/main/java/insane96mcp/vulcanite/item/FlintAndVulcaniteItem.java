package insane96mcp.vulcanite.item;

import com.zimonishim.ziheasymodding.util.KeyBoardHandler;
import insane96mcp.vulcanite.setup.ModConfig;
import insane96mcp.vulcanite.setup.Strings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import net.minecraft.world.item.Item.Properties;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;

public class FlintAndVulcaniteItem extends FlintAndSteelItem {

	public static final int DURABILITY = 80;

	public FlintAndVulcaniteItem() {
		super(new Properties().tab(CreativeModeTab.TAB_TOOLS).defaultDurability(DURABILITY).fireResistant());
	}

    @Override
    public InteractionResult useOn(UseOnContext itemUseContext) {
        Level world = itemUseContext.getLevel();
        BlockPos pos = itemUseContext.getClickedPos();
        Player player = itemUseContext.getPlayer();
        InteractionHand hand = itemUseContext.getHand();

        if (world.getBlockState(pos).getBlock() == Blocks.TNT) {
            if (!world.isClientSide) {
                PrimedTnt entityTNTPrimed = new PrimedTnt(world, (float) pos.getX() + 0.5F, pos.getY(), (float) pos.getZ() + 0.5F, player);
                if (ModConfig.COMMON.toolsAndWeapons.flintAndVulcanite.tntIgnitesFaster.get())
                    entityTNTPrimed.setFuse(40);
                world.addFreshEntity(entityTNTPrimed);
                world.playSound(null, entityTNTPrimed.getX(), entityTNTPrimed.getY(), entityTNTPrimed.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            player.getItemInHand(hand).hurtAndBreak(1, player, (playerEntity) -> playerEntity.broadcastBreakEvent(hand));
            player.swing(hand);

            return InteractionResult.SUCCESS;
        }

        return super.useOn(itemUseContext);
    }

    //Changed this from a boolean to an ActionResultType. Compare this to the 1.15.2 version to see all changes.
    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target,
                                            InteractionHand hand) {
        if (target.fireImmune())
            //Changed return false to ActionResultType.FAIL
            return InteractionResult.FAIL;

        if (target instanceof ServerPlayer) {
            ServerPlayer targetPlayer = (ServerPlayer) target;
            if (!playerIn.canHarmPlayer(targetPlayer))
                //Changed return false to ActionResultType.FAIL
                return InteractionResult.FAIL;
        }

        int timeOnFire = ModConfig.COMMON.toolsAndWeapons.flintAndVulcanite.secondsOnFire.get();

        int flameLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack);

        timeOnFire += flameLevel * timeOnFire;

        target.setSecondsOnFire(timeOnFire);
        target.hurt(DamageSource.playerAttack(playerIn), 0.0f);

        if (target instanceof Creeper) {
            Creeper creeper = (Creeper) target;
            creeper.ignite();
        }

        playerIn.swing(hand);
        stack.hurtAndBreak(ModConfig.COMMON.toolsAndWeapons.flintAndVulcanite.durabilityOnUse.get(), playerIn, playerEntity -> playerEntity.broadcastBreakEvent(hand));

        playerIn.level.playSound(playerIn, target.blockPosition(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS, 1.0f, playerIn.level.random.nextFloat() * 0.4F + 0.8F);
        //Changed return true to ActionResultType.SUCCES
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.equals(Enchantments.FLAMING_ARROWS))
            return true;
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(book);

        if (enchantments.containsKey(Enchantments.FLAMING_ARROWS))
            return true;

        return super.isBookEnchantable(stack, book);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
	    if (KeyBoardHandler.isHoldingShift()){
            tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.FlintAndVulcanite.setOnFire)));
        } else {
	        tooltip.add(new TextComponent(I18n.get(Strings.Tooltips.HoldShiftForMoreInfo.holdShiftForMoreInfo)));
        }
    }
}
