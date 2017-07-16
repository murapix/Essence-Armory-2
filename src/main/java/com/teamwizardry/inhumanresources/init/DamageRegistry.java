package com.teamwizardry.inhumanresources.init;

import com.teamwizardry.inhumanresources.common.damage.DamageVoidfire;

import net.minecraft.util.DamageSource;

public class DamageRegistry
{
	public static final DamageSource VOIDFIRE = new DamageVoidfire("voidfire").setDamageBypassesArmor().setDamageIsAbsolute();
	public static final DamageSource VOID = new DamageSource("void").setDamageBypassesArmor();
}
