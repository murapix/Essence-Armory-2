package com.teamwizardry.inhumanresources.init;

import com.teamwizardry.inhumanresources.common.damage.DamageVoidfire;

import net.minecraft.util.DamageSource;

public class DamageRegistry
{
	public static final DamageVoidfire VOIDFIRE = new DamageVoidfire("voidfire");
	public static final DamageSource VOID = new DamageSource("void").setDamageBypassesArmor();
}
