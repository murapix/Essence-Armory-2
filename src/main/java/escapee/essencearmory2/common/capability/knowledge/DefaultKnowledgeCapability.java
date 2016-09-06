package escapee.essencearmory2.common.capability.knowledge;

import java.util.ArrayList;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import escapee.essencearmory2.common.network.EAPacketHandler;
import escapee.essencearmory2.common.network.KnowledgeUpdateMessage;
import escapee.essencearmory2.lib.MobKnowledge;

/**
 * Created by SirShadow on 18. 08. 2016.
 */
public class DefaultKnowledgeCapability implements IKnowledgeCapability
{

    public ArrayList<String> knowledge = new ArrayList<>();

    @Override
    public ArrayList<String> getKnowledge()
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
    public boolean hasKnowledge(Class<? extends IMob> cls)
    {
    	return knowledge.containsAll(MobKnowledge.validKnowledge.get(cls));
    }

    @Override
    public void addKnowledge(EntityPlayer player, String name)
    {
        knowledge.add(name);
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
    public void setKnowledge(ArrayList<String> knowledge)
    {
        this.knowledge = knowledge;
    }
}
