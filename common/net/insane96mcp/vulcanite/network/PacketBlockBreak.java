package net.insane96mcp.vulcanite.network;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Particles;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

public class PacketBlockBreak {

	public PacketBlockBreak() { }

	public BlockPos pos;
	
	public PacketBlockBreak(BlockPos pos) {
		this.pos = pos;
	}
	
	public void fromBytes(PacketBuffer buf) {
		this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}

	public void toBytes(PacketBuffer buf) {
		buf.writeInt(this.pos.getX());
		buf.writeInt(this.pos.getY());
		buf.writeInt(this.pos.getZ());
	}
	
	public static void encode(PacketBlockBreak message, PacketBuffer packet)
	{
		message.toBytes(packet);
	}

	public static PacketBlockBreak decode(PacketBuffer packet)
	{
		PacketBlockBreak message = new PacketBlockBreak();

		message.fromBytes(packet);
		return message;
	}

	public static void onMessage(PacketBlockBreak message, Supplier<NetworkEvent.Context> ctx) {
		World world = Minecraft.getInstance().world;
		for (int i = 0; i < 20; i++) {
			double x = (double)((float)message.pos.getX() + world.rand.nextFloat());
            double y = (double)((float)message.pos.getY() + world.rand.nextFloat());
            double z = (double)((float)message.pos.getZ() + world.rand.nextFloat());
			world.spawnParticle(Particles.FLAME, x, y, z, 0, 0, 0);
		}
	}
	
}