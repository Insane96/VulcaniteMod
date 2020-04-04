package insane96mcp.vulcanite.setup;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.block.SolidifiedLavaBlock;
import insane96mcp.vulcanite.block.VulcaniteBlock;
import insane96mcp.vulcanite.block.VulcaniteOreBlock;
import insane96mcp.vulcanite.setup.Strings.RegistryNames;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Vulcanite.MOD_ID);

	public static final RegistryObject<VulcaniteBlock> VULCANITE_BLOCK = BLOCKS.register(RegistryNames.VULCANITE_BLOCK, VulcaniteBlock::new);

	public static final RegistryObject<OreBlock> NETHER_VULCANITE_ORE = BLOCKS.register(RegistryNames.NETHER_VULCANITE_ORE, VulcaniteOreBlock::new);

	public static final RegistryObject<SolidifiedLavaBlock> SOLIDIFIED_LAVA = BLOCKS.register(RegistryNames.SOLIDIFIED_LAVA, () -> new SolidifiedLavaBlock(false));
	public static final RegistryObject<SolidifiedLavaBlock> SOLIDIFIED_FLOWING_LAVA = BLOCKS.register(RegistryNames.SOLIDIFIED_FLOWING_LAVA, () -> new SolidifiedLavaBlock(true));
}
