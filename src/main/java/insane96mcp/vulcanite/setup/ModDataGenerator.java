package insane96mcp.vulcanite.setup;

import insane96mcp.vulcanite.Vulcanite;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {

    @SubscribeEvent
    public static void data(GatherDataEvent event){
        final DataGenerator generator = event.getGenerator();
        if (event.includeServer()){
            generator.addProvider(new ModBlockTagsProvider(generator, Vulcanite.MOD_ID, event.getExistingFileHelper()));
        }
    }
}