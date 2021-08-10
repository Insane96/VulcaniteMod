package insane96mcp.vulcanite.integration;

import insane96mcp.vulcanite.Vulcanite;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

	//TODO: Delete if we do not need this anymore in the next version (after ZIHEasyModding update).

	private static final ResourceLocation UID = new ResourceLocation(Vulcanite.MOD_ID, Vulcanite.MOD_ID);

	@Override
	public ResourceLocation getPluginUid() {
		return UID;
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		final IVanillaRecipeFactory factory = registration.getVanillaRecipeFactory();
	}
}