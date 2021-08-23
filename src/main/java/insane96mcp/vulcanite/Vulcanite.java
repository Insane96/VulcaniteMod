package insane96mcp.vulcanite;

import com.zimonishim.ziheasymodding.modInit.IModID;
import insane96mcp.vulcanite.network.PacketHandler;
import insane96mcp.vulcanite.setup.ModBlocks;
import insane96mcp.vulcanite.setup.ModItems;
import insane96mcp.vulcanite.setup.ModLootModifiers;
import insane96mcp.vulcanite.worldgen.OreGenerator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Vulcanite.MOD_ID)
public class Vulcanite implements IModID {
    public static final String MOD_ID = "vulcanite";
    public static final String RESOURCE_PREFIX = MOD_ID + ":";

    private static final Logger LOGGER = LogManager.getLogger();

    public Vulcanite() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, insane96mcp.vulcanite.setup.ModConfig.COMMON_SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.init();
        OreGenerator.init();
    }

    @Override
    public String getModID() {
        return MOD_ID;
    }
}