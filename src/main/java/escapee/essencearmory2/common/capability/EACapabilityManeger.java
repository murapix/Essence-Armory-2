package escapee.essencearmory2.common.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import escapee.essencearmory2.common.capability.knowlage.DefaultKnowledgeCapability;
import escapee.essencearmory2.common.capability.knowlage.IKnowledgeCapability;
import escapee.essencearmory2.common.capability.knowlage.KnowledgeCapabilityStorage;
import escapee.essencearmory2.common.capability.knowlage.KnowledgeProvider;
import escapee.essencearmory2.lib.ModResourceLocations;

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
