package escapee.essencearmory2.common.tile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.ArrayList;

/**
 * Created by SirShadow on 17. 08. 2016.
 */
public class TEMobTrap extends TileEntity implements ITickable
{

    @Override
    public void update()
    {
        ArrayList<EntityLivingBase> zombies = (ArrayList<EntityLivingBase>) worldObj.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.getX() - 1, this.getPos().getY() - 1, this.getPos().getZ() - 1, this.getPos().getX() + 2, this.getPos().getY() + 2, this.getPos().getZ() + 2));
        for (EntityLivingBase zomby : zombies) {
            if (!(zomby instanceof EntityPlayer)) {
                zomby.setPosition(zomby.prevPosX,zomby.prevPosY,zomby.prevPosZ);
                worldObj.spawnParticle(EnumParticleTypes.DRAGON_BREATH,pos.getX() - 1, this.getPos().getY() - 1, this.getPos().getZ() - 1, this.getPos().getX() + 2, this.getPos().getY() + 2, this.getPos().getZ() + 2);
            }
        }
    }
}