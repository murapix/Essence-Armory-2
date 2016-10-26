package escapee.essencearmory2.common.capability.knowledge;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import escapee.essencearmory2.common.network.EAPacketHandler;
import escapee.essencearmory2.common.network.KnowledgeUpdateMessage;
import escapee.essencearmory2.common.research.ResearchBase;
import escapee.essencearmory2.common.research.ResearchNetwork;

/**
 * Created by SirShadow on 18. 08. 2016.
 */
public class DefaultKnowledgeCapability implements IKnowledgeCapability
{
    public ArrayList<ResearchBase> knowledge = new ArrayList<>();

    @Override
    public ArrayList<ResearchBase> getKnowledge()
    {
        return knowledge;
    }

    @Override
    public boolean hasKnowledge(String name)
    {
        for (int i = 0; i < knowledge.size();i++)
        {
            if(knowledge.get(i).equals(name))
            {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean hasPrerequisites(String name)
    {
    	return knowledge.containsAll(ResearchNetwork.getPrerequisites(name));
    }

    @Override
    public void addKnowledge(EntityPlayer player, String name)
    {
        knowledge.add(ResearchNetwork.getResearch(name));
        if(player != null)
        {
            dataChanged(player);
        }
    }

    @Override
    public NBTTagCompound saveKnowledgeData()
    {
        return (NBTTagCompound)KnowledgeCapabilityStorage.STORAGE.writeNBT(KnowledgeProvider.KnowlageCapability,this,null);
    }

    @Override
    public void loadNBTData(NBTTagCompound tag)
    {
        KnowledgeCapabilityStorage.STORAGE.readNBT(KnowledgeProvider.KnowlageCapability,this,null,tag);
    }

    @Override
    public void dataChanged(EntityPlayer player)
    {
        if (player != null && !player.getEntityWorld().isRemote)
        {
            EAPacketHandler.INSTANCE.sendTo(new KnowledgeUpdateMessage(saveKnowledgeData()),(EntityPlayerMP)player);
        }
    }

    @Override
    public void setKnowledge(ArrayList<ResearchBase> knowledge)
    {
        this.knowledge = knowledge;
    }
}
