package insane96mcp.vulcanite.setup;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.lootmodifiers.SmeltingModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModLootModifiers {

	//REPLACED new Deferredregister<> WITH DeferredRegister.create()
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Vulcanite.MOD_ID);

	public static final RegistryObject<GlobalLootModifierSerializer<?>> SMELTING = LOOT_MODIFIERS.register(Strings.RegistryNames.SMELTING, () -> new SmeltingModifier.Serializer());
}
