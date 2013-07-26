package socialtoilet.android.utils;

import java.util.HashMap;

import socialtoilet.android.model.IToilet;

public class StateSaver
{
	private static StateSaver instance;
	
	private HashMap<String, Object> map;
	private HashMap<String, IToilet> toiletMap;
	
	private StateSaver()
	{
		map = new HashMap<String, Object>();
		toiletMap = new HashMap<String, IToilet>();
	}
	
	public static StateSaver getInstance()
	{
		if( null == instance )
		{
			instance = new StateSaver();
		}
		return instance;
	}
	
	public void save(String key, Object object)
	{
		map.put(key, object);
	}

	public Object retrieve(String key)
	{
		Object object = map.get(key);
		map.remove(key);
		return object;
	}
	
	public void saveToilet(String key, IToilet object)
	{
		toiletMap.put(key, object);
	}

	public IToilet retrieveToilet(String key)
	{
		IToilet object = toiletMap.get(key);
		toiletMap.remove(key);
		return object;
	}
}
