package com.teamwizardry.inhumanresources.common.damage;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSourceIndirect;

public class DamageVoidfire extends EntityDamageSourceIndirect
{	
	public DamageVoidfire(String damageType)
	{
		this(damageType, null, null);
	}

	private DamageVoidfire(String damageType, Entity source, Entity indirectSource)
	{
		super(damageType, source, indirectSource);
		setDamageBypassesArmor();
		setDamageIsAbsolute();
	}
	
	public DamageVoidfire set(Entity source, Entity indirectSource)
	{
		return new DamageVoidfire(damageType, source, indirectSource);
	}
}
