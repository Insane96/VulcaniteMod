package insane96mcp.vulcanite.setup;

import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;

public class ModSoundTypes {

    public static final ForgeSoundType VULCANITE_ORE = new ForgeSoundType(1.0F, 1.0F, ModSoundEvents.VULCANITE_ORE_BREAK, SoundEvents.BLOCK_NETHER_ORE_STEP.delegate, SoundEvents.BLOCK_NETHER_ORE_PLACE.delegate, SoundEvents.BLOCK_NETHER_ORE_HIT.delegate, SoundEvents.BLOCK_NETHER_ORE_FALL.delegate);
    public static final ForgeSoundType VULCANITE_BLOCK = new ForgeSoundType(1.0F, 1.0F, SoundEvents.BLOCK_METAL_BREAK.delegate, ModSoundEvents.VULCANITE_BLOCK_STEP, SoundEvents.BLOCK_METAL_PLACE.delegate, SoundEvents.BLOCK_METAL_HIT.delegate, SoundEvents.BLOCK_METAL_FALL.delegate);
}
