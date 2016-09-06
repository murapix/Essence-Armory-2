package escapee.essencearmory2.common.network;

import escapee.essencearmory2.common.capability.knowledge.KnowledgeCapabilityStorage;
import escapee.essencearmory2.common.capability.knowledge.KnowledgeProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by SirShadow on 18. 08. 2016.
 */
public class KnowledgeUpdateMessage implements IMessage,IMessageHandler<KnowledgeUpdateMessage,IMessage>
{

    private NBTTagCompound tag;

    public KnowledgeUpdateMessage(){}

    public KnowledgeUpdateMessage(NBTTagCompound tag)
    {
        this.tag = tag;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        tag = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeTag(buf,tag);
    }

    @Override
    public IMessage onMessage( final KnowledgeUpdateMessage message, final MessageContext ctx) {
        IThreadListener mainThread = (ctx.side.isClient())? Minecraft.getMinecraft() : (WorldServer) ctx.getServerHandler().playerEntity.worldObj;
        mainThread.addScheduledTask(() -> KnowledgeProvider.get(Minecraft.getMinecraft().thePlayer).loadNBTData(message.tag));
        return null;
    }
}
