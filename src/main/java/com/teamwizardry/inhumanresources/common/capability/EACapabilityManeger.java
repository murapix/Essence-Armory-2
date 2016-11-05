package com.teamwizardry.inhumanresources.common.capability;

import com.teamwizardry.inhumanresources.common.capability.knowledge.DefaultKnowledgeCapability;
import com.teamwizardry.inhumanresources.common.capability.knowledge.IKnowledgeCapability;
import com.teamwizardry.inhumanresources.common.capability.knowledge.KnowledgeCapabilityStorage;
import com.teamwizardry.inhumanresources.common.capability.knowledge.KnowledgeProvider;
import com.teamwizardry.inhumanresources.common.utils.lib.ModResourceLocations;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by SirShadow on 18. 08. 2016.
 */
public class EACapabilityManeger
{
    public static void registerCapability()
    {
        CapabilityManager.INSTANCE.register(IKnowledgeCapability.class,new KnowledgeCapabilityStorage(), DefaultKnowledgeCapability::new);
    }

    @SubscribeEvent
    public void addCapabilityies(AttachCapabilitiesEvent.Entity event)
    {
        if(event.getEntity() instanceof EntityPlayer)
        {
            if(!event.getEntity().hasCapability(KnowledgeProvider.KnowlageCapability,null))
            {
                event.addCapability(ModResourceLocations.KNOWLEDGE_CAPABILITY, new KnowledgeProvider(new DefaultKnowledgeCapability()));
            }
        }
    }
}
