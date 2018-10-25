package net.insane96mcp.vulcanite.item;

import java.util.List;

import javax.annotation.Nullable;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.lib.Properties;
import net.insane96mcp.vulcanite.lib.Strings.Names;
import net.insane96mcp.vulcanite.lib.Strings.Tooltips;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFlintAndVulcanite extends ItemFlintAndSteel{
	public ItemFlintAndVulcanite(String name, ToolMaterial material, CreativeTabs tab) {
		this.setMaxDamage(222);
		this.setCreativeTab(tab);
		setRegistryName(name);
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		return "item." + Vulcanite.RESOURCE_PREFIX + Names.FLINT_AND_VULCANITE;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (GuiScreen.isShiftKeyDown() && Properties.config.showMoreInfo) {
			tooltip.add(I18n.format(Tooltips.FlintAndVulcanite.adv_setOnFire, Properties.config.toolsAndWeapons.flintAndVulcanite.secondsOnFire));
			tooltip.add(I18n.format(Tooltips.FlintAndVulcanite.adv_damageOnUse, Properties.config.toolsAndWeapons.flintAndVulcanite.damageOnUse));
		}
		else {
			tooltip.add(I18n.format(Tooltips.FlintAndVulcanite.base_setOnFire));
			if (Properties.config.showMoreInfo)
				tooltip.add(I18n.format(Tooltips.General.shiftForMore));
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.getBlockState(pos).getBlock() == Blocks.TNT) {
			if (!worldIn.isRemote)
			{
		        EntityTNTPrimed entityTNTPrimed = new EntityTNTPrimed(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), player);
		        if (Properties.config.toolsAndWeapons.flintAndVulcanite.tntIgnitesFaster)
		        	entityTNTPrimed.setFuse(40);
		        worldIn.spawnEntity(entityTNTPrimed);
                worldIn.playSound((EntityPlayer)null, entityTNTPrimed.posX, entityTNTPrimed.posY, entityTNTPrimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
	        }
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            player.getHeldItem(hand).damageItem(1, player);
            player.swingArm(hand);

            return EnumActionResult.SUCCESS;
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	private static ItemStack flintAndVulcanite = new ItemStack(ModItems.flintAndVulcaniteItem);
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target,
			EnumHand hand) {		
		if (!(target instanceof EntityLivingBase))
			return false;
		
		EntityLivingBase entityLivingBase = (EntityLivingBase)target;
		
		if (entityLivingBase instanceof EntityPlayerMP && !Properties.config.toolsAndWeapons.flintAndVulcanite.pvp)
			return false;
		
		if (entityLivingBase.isImmuneToFire())
			return false;
		
		entityLivingBase.setFire(Properties.config.toolsAndWeapons.flintAndVulcanite.secondsOnFire);
		
		if (entityLivingBase instanceof EntityCreeper) {
			NBTTagCompound ignited = new NBTTagCompound();
			ignited.setByte("ignited", (byte)1);
			entityLivingBase.readEntityFromNBT(ignited);
		}
		
		playerIn.swingArm(hand);
		stack.damageItem(Properties.config.toolsAndWeapons.flintAndVulcanite.damageOnUse, playerIn);
		
		playerIn.world.playSound(playerIn, entityLivingBase.getPosition(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0f, playerIn.world.rand.nextFloat() * 0.4F + 0.8F);
		return true;
	}
}
