package insane96mcp.vulcanite.network;

import insane96mcp.vulcanite.Vulcanite;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.PacketDistributor;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

public class PacketHandler {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(Vulcanite.MOD_ID, "main"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
	);

	public static void init() {
		int discriminator = 0;
		INSTANCE.registerMessage(discriminator++, PacketBlockBreak.class, PacketBlockBreak::encode, PacketBlockBreak::decode, PacketBlockBreak::onMessage);
	}

	public static void sendToClient(PacketDistributor.PacketTarget target, Object message) {
		INSTANCE.send(target, message);
	}
}
