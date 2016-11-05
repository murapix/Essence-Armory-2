package com.teamwizardry.inhumanresources.common.capability.knowledge;

import java.util.ArrayList;
import com.teamwizardry.inhumanresources.common.research.ResearchBase;
import com.teamwizardry.inhumanresources.common.research.ResearchNetwork;
import com.teamwizardry.inhumanresources.common.utils.lib.NBTTags;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.util.Constants;

/**
 * Created by SirShadow on 18. 08. 2016.
 */
public class KnowledgeCapabilityStorage implements IStorage<IKnowledgeCapability>
{

    public static final KnowledgeCapabilityStorage STORAGE = new KnowledgeCapabilityStorage();

    @Override
    public NBTBase writeNBT(Capability<IKnowledgeCapability> capability, IKnowledgeCapability instance, EnumFacing side)
    {
        NBTTagCompound tagCompound = new NBTTagCompound();
        NBTTagList list = new NBTTagList();
        ArrayList<ResearchBase> knowledge = instance.getKnowledge();
        for (int i = 0; i < knowledge.size(); i ++){
            list.appendTag(new NBTTagString(knowledge.get(i).getName()));
        }
        tagCompound.setTag(NBTTags.KNOWLEDGE_TAG, list);
        return tagCompound;
    }

    @Override
    public void readNBT(Capability<IKnowledgeCapability> capability, IKnowledgeCapability instance, EnumFacing side, NBTBase nbt)
    {
        NBTTagCompound tag =(NBTTagCompound)nbt;
        NBTTagList list = tag.getTagList(NBTTags.KNOWLEDGE_TAG, Constants.NBT.TAG_STRING);
        ArrayList<ResearchBase> knowledge = new ArrayList<>();
        for (int i = 0; i < list.tagCount(); i ++){
            knowledge.add(ResearchNetwork.getResearch(list.getStringTagAt(i)));
        }
        instance.setKnowledge(knowledge);
    }
}
