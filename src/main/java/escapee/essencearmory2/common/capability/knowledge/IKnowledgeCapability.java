package escapee.essencearmory2.common.capability.knowledge;

import java.util.ArrayList;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by SirShadow on 18. 08. 2016.
 */
public interface IKnowledgeCapability
{
    ArrayList<String> getKnowledge();
    boolean hasKnowledge(String name);
    boolean hasKnowledge(Class<? extends IMob> cls);
    void addKnowledge(EntityPlayer player,String name);
    NBTTagCompound saveKnowledgeData();
    void loadNBTData(NBTTagCompound tag);
    void dataChanged(EntityPlayer player);
    void setKnowledge(ArrayList<String> knowledge);
}
