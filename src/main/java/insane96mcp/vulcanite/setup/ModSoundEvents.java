package insane96mcp.vulcanite.setup;

import insane96mcp.vulcanite.Vulcanite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Vulcanite.MOD_ID);

    //Vulcanite Ore
    public static final RegistryObject<SoundEvent> VULCANITE_ORE_BREAK = SOUNDS.register(
            "block.vulcanite_ore_break", () -> new SoundEvent(new ResourceLocation(Vulcanite.MOD_ID, "block.vulcanite_ore_break")));


    //Vulcanite Block
    public static final RegistryObject<SoundEvent> VULCANITE_BLOCK_STEP = SOUNDS.register(
            "block.vulcanite_block_step", () -> new SoundEvent(new ResourceLocation(Vulcanite.MOD_ID, "block.vulcanite_block_step")));

}
