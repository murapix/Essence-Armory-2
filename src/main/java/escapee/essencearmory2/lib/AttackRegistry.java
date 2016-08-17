package escapee.essencearmory2.lib;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class AttackRegistry
{
	public static HashMap<String, Class> attacks = new HashMap<String, Class>();
	
	public static void init()
	{
	}

	public static Constructor getAttack(String name)
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
