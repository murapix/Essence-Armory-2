package com.teamwizardry.inhumanresources.common.capability.knowledge;

import java.util.ArrayList;

import com.teamwizardry.inhumanresources.common.research.ResearchBase;
import com.teamwizardry.inhumanresources.init.ResearchRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

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
    	return knowledge.containsAll(ResearchRegistry.getPrerequisites(name));
    }

    @Override
    public void addKnowledge(EntityPlayer player, String name)
    {
        knowledge.add(ResearchRegistry.getResearch(name));
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
    public void setKnowledge(ArrayList<ResearchBase> knowledge)
    {
        this.knowledge = knowledge;
    }

	@Override
	public void dataChanged(EntityPlayer player)
	{
		// TODO Auto-generated method stub
	}
}
