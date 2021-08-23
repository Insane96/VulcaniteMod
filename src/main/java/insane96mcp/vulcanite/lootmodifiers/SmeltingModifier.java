package insane96mcp.vulcanite.lootmodifiers;


import com.google.gson.JsonObject;
import insane96mcp.vulcanite.network.PacketBlockBreak;
import insane96mcp.vulcanite.network.PacketHandler;
import insane96mcp.vulcanite.setup.ModConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.fmllegacy.network.PacketDistributor;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SmeltingModifier extends LootModifier {
    public SmeltingModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        ArrayList<ItemStack> newLoot = new ArrayList<>();

        generatedLoot.forEach(stack -> newLoot.add(smelt(stack, context)));

        if (newLoot.equals(generatedLoot))
            return generatedLoot;

        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

        if (!(entity instanceof ServerPlayer))
            throw new ClassCastException("Hope this isn't called other than the player breaking blocks " + entity.toString());

        ServerPlayer player = (ServerPlayer) entity;
        BlockPos pos = new BlockPos(context.getParamOrNull(LootContextParams.ORIGIN));
        PacketHandler.sendToClient(PacketDistributor.PLAYER.with(() -> player), new PacketBlockBreak(pos));

        //REPLACED player.dimension.getId() != -1 WITH !player.getEntityWorld().getDimensionKey().equals(World.THE_NETHER)
        if (!player.getCommandSenderWorld().dimension().equals(Level.NETHER))
            player.getMainHandItem().hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(InteractionHand.MAIN_HAND));

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
                ExperienceOrb xpOrb = new ExperienceOrb(context.getLevel(), pos.getX() + .5f, pos.getY() + .5f, pos.getZ() + .5f, (int) experience);
                context.getLevel().addFreshEntity(xpOrb);
            }
        }
        return newLoot;

    }

    private static ItemStack smelt(ItemStack stack, LootContext context) {
        return context.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel())
                .map(SmeltingRecipe::getResultItem)
                .filter(itemStack -> !itemStack.isEmpty())
                .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                .orElse(stack);
    }

    private static float getExperience(ItemStack stack, LootContext context) {
        return context.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel())
                .map(SmeltingRecipe::getExperience)
                .orElse(0f);
    }

    public static class Serializer extends GlobalLootModifierSerializer<SmeltingModifier> {

        @Override
        public SmeltingModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] lootConditions) {
            return new SmeltingModifier(lootConditions);
        }

        @Override
        public JsonObject write(SmeltingModifier instance) {
            return null;
        }
    }
}