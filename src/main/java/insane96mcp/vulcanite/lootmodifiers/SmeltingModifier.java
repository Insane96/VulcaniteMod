package insane96mcp.vulcanite.lootmodifiers;


import com.google.gson.JsonObject;
import insane96mcp.vulcanite.network.PacketBlockBreak;
import insane96mcp.vulcanite.network.PacketHandler;
import insane96mcp.vulcanite.setup.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.world.World;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SmeltingModifier extends LootModifier {
    public SmeltingModifier(ILootCondition[] conditions) {
        super(conditions);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        ArrayList<ItemStack> newLoot = new ArrayList<>();

        generatedLoot.forEach(stack -> newLoot.add(smelt(stack, context)));

        if (newLoot.equals(generatedLoot))
            return generatedLoot;

        Entity entity = context.get(LootParameters.THIS_ENTITY);

        if (!(entity instanceof ServerPlayerEntity))
            throw new ClassCastException("Hope this isn't called other than the player breaking blocks " + entity.toString());

        ServerPlayerEntity player = (ServerPlayerEntity) entity;
        BlockPos pos = new BlockPos(context.get(LootParameters.field_237457_g_));
        PacketHandler.sendToClient(PacketDistributor.PLAYER.with(() -> player), new PacketBlockBreak(pos));

        //REPLACED player.dimension.getId() != -1 WITH !player.getEntityWorld().getDimensionKey().equals(World.THE_NETHER)
        if (!player.getEntityWorld().getDimensionKey().equals(World.THE_NETHER))
            player.getHeldItemMainhand().damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(Hand.MAIN_HAND));

        if (ModConfig.COMMON.toolsAndWeapons.bonusStats.smeltingDropsExperience.get()) {
            float experience = 0;
            for (ItemStack stack : generatedLoot) {
                experience += getExperience(stack, context);
            }

            //If experience is not an integer
            if (experience % 1 != 0) {
                int intXp = (int) experience;
                float decimals = experience - intXp;

                if (context.getRandom().nextFloat() < decimals)
                    experience = (float) Math.ceil(experience);
                else
                    experience = (float) Math.floor(experience);
            }

            if (experience > 0) {
                ExperienceOrbEntity xpOrb = new ExperienceOrbEntity(context.getWorld(), pos.getX() + .5f, pos.getY() + .5f, pos.getZ() + .5f, (int) experience);
                context.getWorld().addEntity(xpOrb);
            }
        }
        return newLoot;

    }

    private static ItemStack smelt(ItemStack stack, LootContext context) {
        return context.getWorld().getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(stack), context.getWorld())
                .map(FurnaceRecipe::getRecipeOutput)
                .filter(itemStack -> !itemStack.isEmpty())
                .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                .orElse(stack);
    }

    private static float getExperience(ItemStack stack, LootContext context) {
        return context.getWorld().getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(stack), context.getWorld())
                .map(FurnaceRecipe::getExperience)
                .orElse(0f);
    }

    public static class Serializer extends GlobalLootModifierSerializer<SmeltingModifier> {

        @Override
        public SmeltingModifier read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
            return new SmeltingModifier(lootConditions);
        }

        @Override
        public JsonObject write(SmeltingModifier instance) {
            return null;
        }
    }
}