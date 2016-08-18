package escapee.essencearmory2.common.capability.knowlage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;

/**
 * Created by SirShadow on 18. 08. 2016.
 */
public interface IKnowledgeCapability
{
    ArrayList<String> getKnowledge();
    boolean hasKnowledge(String name);
    void addKnowledge(EntityPlayer player,String name);
    NBTTagCompound saveKnowledgeData();
    void loadNBTData(NBTTagCompound tag);
    void dataChanged(EntityPlayer player);
    void setKnowledge(ArrayList<String> knowledge);
}
