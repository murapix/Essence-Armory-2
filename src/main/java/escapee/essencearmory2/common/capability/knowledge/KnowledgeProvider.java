package escapee.essencearmory2.common.capability.knowledge;

import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by SirShadow on 18. 08. 2016.
 */
public class KnowledgeProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{
	private IKnowledgeCapability knowlageCapability = null;

	public KnowledgeProvider()
	{
		knowlageCapability = new DefaultKnowledgeCapability();
	}

	public KnowledgeProvider(IKnowledgeCapability c)
	{
		this.knowlageCapability = c;
	}

	@CapabilityInject(IKnowledgeCapability.class)
	public static final Capability<IKnowledgeCapability> KnowlageCapability = null;

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability == KnowlageCapability;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
	{
		if (KnowlageCapability != null && capability == KnowlageCapability)
			return (T) knowlageCapability;
		return null;
	}

	public static IKnowledgeCapability get(EntityPlayer player)
	{
		return player != null && player.hasCapability(KnowlageCapability, null) ? player.getCapability(KnowlageCapability, null) : null;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		return knowlageCapability.saveKnowledgeData();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		knowlageCapability.loadNBTData(nbt);
	}
}
