package net.insane96mcp.vulcanite.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class BlockBreak implements IMessage {

	public BlockBreak() { }

	public BlockPos pos;
	
	public BlockBreak(BlockPos pos) {
		this.pos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.pos.getX());
		buf.writeInt(this.pos.getY());
		buf.writeInt(this.pos.getZ());
	}

	public static class Handler implements IMessageHandler<BlockBreak, IMessage>{

		@Override
		public IMessage onMessage(BlockBreak message, MessageContext ctx) {
			IThreadListener iThreadListener = Minecraft.getMinecraft();
			iThreadListener.addScheduledTask(new Runnable() {
				
				@Override
				public void run() {
					World world = Minecraft.getMinecraft().world;
					for (int i = 0; i < 20; i++) {
						double x = (double)((float)message.pos.getX() + world.rand.nextFloat());
		                double y = (double)((float)message.pos.getY() + world.rand.nextFloat());
		                double z = (double)((float)message.pos.getZ() + world.rand.nextFloat());
						world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0);
					}
				}
			});
			return null;
		}
		
	}
	
}
