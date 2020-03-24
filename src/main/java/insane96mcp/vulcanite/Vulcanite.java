package insane96mcp.vulcanite;

import insane96mcp.vulcanite.network.PacketHandler;
import insane96mcp.vulcanite.worldgen.OreGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Vulcanite.MOD_ID)
public class Vulcanite {
    public static final String MOD_ID = "vulcanite";
    public static final String RESOURCE_PREFIX = MOD_ID + ":";

    private static final Logger LOGGER = LogManager.getLogger();

    public Vulcanite() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, insane96mcp.vulcanite.setup.ModConfig.COMMON_SPEC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.init();

        OreGenerator.init();
    }
}
