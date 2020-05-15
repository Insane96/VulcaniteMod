package insane96mcp.vulcanite.integration;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.events.AnvilUpdate;
import insane96mcp.vulcanite.setup.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

	private static final ResourceLocation UID = new ResourceLocation(Vulcanite.MOD_ID, Vulcanite.MOD_ID);

	@Override
	public ResourceLocation getPluginUid() {
		return UID;
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		final IVanillaRecipeFactory factory = registration.getVanillaRecipeFactory();

		registerVulcaniteToolForging(registration, factory);
	}

	private void registerVulcaniteToolForging(IRecipeRegistration registration, IVanillaRecipeFactory factory) {
		List<Object> recipes = new ArrayList<>();
		for (AnvilUpdate.EquipmentUpgrade upgrade : AnvilUpdate.EQUIPMENT_UPGRADES) {
			ItemStack equipment = new ItemStack(upgrade.item);
			ItemStack nuggets = new ItemStack(ModItems.VULCANITE_NUGGET.get(), upgrade.materialAmount);
			String itemName = upgrade.item.getRegistryName().getPath();
			ResourceLocation vulcaniteName = new ResourceLocation(Vulcanite.MOD_ID, itemName.replace("iron", "vulcanite"));
			if (upgrade.item.equals(Items.FLINT_AND_STEEL))
				vulcaniteName = new ResourceLocation(Vulcanite.MOD_ID, "flint_and_vulcanite");
			ItemStack output = new ItemStack(ForgeRegistries.ITEMS.getValue(vulcaniteName));

			recipes.add(factory.createAnvilRecipe(equipment,
					Collections.singletonList(nuggets),
					Collections.singletonList(output)
			));
		}

		registration.addRecipes(recipes, VanillaRecipeCategoryUid.ANVIL);
	}
}
