package insane96mcp.vulcanite.setup;

import insane96mcp.vulcanite.Vulcanite;
import insane96mcp.vulcanite.block.BlockVulcanite;
import insane96mcp.vulcanite.setup.Strings.Names;
import net.minecraft.block.OreBlock;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {
    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BLOCK)
    public static BlockVulcanite vulcaniteBlock;

    @ObjectHolder(Vulcanite.RESOURCE_PREFIX + Names.NETHER_VULCANITE_ORE)
    public static OreBlock vulcaniteOre;
}
