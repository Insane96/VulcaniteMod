package net.insane96mcp.vulcanite.item;

import java.util.List;

import net.insane96mcp.vulcanite.init.ModConfig;
import net.insane96mcp.vulcanite.init.Strings.Tooltips;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemFlintAndVulcanite extends ItemFlintAndSteel {

	public ItemFlintAndVulcanite(String id) {
		super(new Properties().group(ItemGroup.TOOLS).defaultMaxDamage(222));
		
		setRegistryName(id);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation(Tooltips.FlintAndVulcanite.setOnFire));
	}
	
	@Override
	public EnumActionResult onItemUse(ItemUseContext itemUseContext) {
		World world = itemUseContext.getWorld();
		BlockPos pos = itemUseContext.getPos();
		EntityPlayer player = itemUseContext.getPlayer();
		EnumHand hand = player.getActiveHand();
		
		if (world.getBlockState(pos).getBlock() == Blocks.TNT) {
			if (!world.isRemote)
			{
		        EntityTNTPrimed entityTNTPrimed = new EntityTNTPrimed(world, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), player);
		        if (ModConfig.ToolsAndWeapons.FlintAndVulcanite.tntIgnitesFaster.get())
		        	entityTNTPrimed.setFuse(40);
		        world.spawnEntity(entityTNTPrimed);
		        world.playSound((EntityPlayer)null, entityTNTPrimed.posX, entityTNTPrimed.posY, entityTNTPrimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
	        }
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            player.getHeldItem(hand).damageItem(1, player);
            player.swingArm(hand);

            return EnumActionResult.SUCCESS;
		}
		return super.onItemUse(itemUseContext);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target,
			EnumHand hand) {
		if (!(target instanceof EntityLivingBase))
			return false;
		
		EntityLivingBase entityLivingBase = (EntityLivingBase)target;
		
		if (entityLivingBase instanceof EntityPlayerMP){
			EntityPlayerMP targetPlayer = (EntityPlayerMP) entityLivingBase;
			if (!playerIn.canAttackPlayer(targetPlayer))
				return false;
		}
		
		if (entityLivingBase.isImmuneToFire())
			return false;
		
		int timeOnFire = ModConfig.ToolsAndWeapons.FlintAndVulcanite.secondsOnFire.get();
		
		int flameLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack);
		
		timeOnFire += flameLevel * timeOnFire;
		
		entityLivingBase.setFire(timeOnFire);
		entityLivingBase.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 0.0f);
		
		if (entityLivingBase instanceof EntityCreeper) {
			EntityCreeper creeper = (EntityCreeper) entityLivingBase;
			creeper.ignite();
		}
		
		playerIn.swingArm(hand);
		stack.damageItem(ModConfig.ToolsAndWeapons.FlintAndVulcanite.durabilityOnUse.get(), playerIn);
		
		playerIn.world.playSound(playerIn, entityLivingBase.getPosition(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0f, playerIn.world.rand.nextFloat() * 0.4F + 0.8F);
		return true;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if (enchantment.equals(Enchantments.FLAME)) return true;
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}

}
