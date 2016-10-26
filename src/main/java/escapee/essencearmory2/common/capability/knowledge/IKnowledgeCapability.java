package escapee.essencearmory2.common.capability.knowledge;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import escapee.essencearmory2.common.research.ResearchBase;

/**
 * Created by SirShadow on 18. 08. 2016.
 */
public interface IKnowledgeCapability
{
    ArrayList<ResearchBase> getKnowledge();
    boolean hasKnowledge(String name);
    boolean hasPrerequisites(String name);
    void addKnowledge(EntityPlayer player, String name);
    NBTTagCompound saveKnowledgeData();
    void loadNBTData(NBTTagCompound tag);
    void dataChanged(EntityPlayer player);
    void setKnowledge(ArrayList<ResearchBase> knowledge);
}
