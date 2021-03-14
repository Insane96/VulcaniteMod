package insane96mcp.vulcanite.setup;

import insane96mcp.vulcanite.block.SolidifiedLavaBlock;
import insane96mcp.vulcanite.block.VulcaniteBlock;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generatorIn, String modId, ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void registerTags(){
        getOrCreateBuilder(BlockTags.STRIDER_WARM_BLOCKS).addItemEntry(ModBlocks.SOLIDIFIED_FLOWING_LAVA.get());
        getOrCreateBuilder(BlockTags.STRIDER_WARM_BLOCKS).addItemEntry(ModBlocks.SOLIDIFIED_LAVA.get());
    }
}
