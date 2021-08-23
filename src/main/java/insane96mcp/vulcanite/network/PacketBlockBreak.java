package insane96mcp.vulcanite.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketBlockBreak {

    public PacketBlockBreak() {
    }

    public BlockPos pos;

    public PacketBlockBreak(BlockPos pos) {
        this.pos = pos;
    }

    public void fromBytes(FriendlyByteBuf buf) {
        this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.pos.getX());
        buf.writeInt(this.pos.getY());
        buf.writeInt(this.pos.getZ());
    }

    public static void encode(PacketBlockBreak message, FriendlyByteBuf packet) {
        message.toBytes(packet);
    }

    public static PacketBlockBreak decode(FriendlyByteBuf packet) {
        PacketBlockBreak message = new PacketBlockBreak();
        message.fromBytes(packet);
        return message;
    }

    public static void onMessage(PacketBlockBreak message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
            handleClient(message, ctx);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void handleClient(PacketBlockBreak message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().setPacketHandled(true);
        ctx.get().enqueueWork(() -> {
            Level world = Minecraft.getInstance().level;
            for (int i = 0; i < 20; i++) {
                double x = ((float) message.pos.getX() + world.random.nextFloat());
                double y = ((float) message.pos.getY() + world.random.nextFloat());
                double z = ((float) message.pos.getZ() + world.random.nextFloat());
                world.addParticle(ParticleTypes.FLAME, x, y, z, 0, 0, 0);
            }
        });
    }

}