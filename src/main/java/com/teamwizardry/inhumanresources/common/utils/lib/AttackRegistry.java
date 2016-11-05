package com.teamwizardry.inhumanresources.common.utils.lib;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class AttackRegistry
{
	public static HashMap<String, Class<? extends BossAttack>> attacks = new HashMap<>();
	
	public static void init()
	{
	}

	public static Constructor<? extends BossAttack> getAttack(String name)
	{
		try
		{
			return attacks.get(name).getConstructor(EntityBossBase.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
