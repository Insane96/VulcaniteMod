package insane96mcp.vulcanite;

import insane96mcp.vulcanite.network.PacketBlockBreak;
import insane96mcp.vulcanite.worldgen.OreGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Vulcanite.MOD_ID)
public class Vulcanite {
    public static final String MOD_ID = "vulcanite";
    public static final String RESOURCE_PREFIX = MOD_ID + ":";

    public static SimpleChannel channel = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, "simple_channel"), () -> "1.0", "1.0"::equals, "1.0"::equals);

    private static final Logger LOGGER = LogManager.getLogger();

    public Vulcanite() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, insane96mcp.vulcanite.setup.ModConfig.COMMON_SPEC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        int discriminator = 0;

        channel.registerMessage(discriminator++, PacketBlockBreak.class, PacketBlockBreak::encode, PacketBlockBreak::decode, PacketBlockBreak::onMessage);

        OreGenerator.init();
    }
}
