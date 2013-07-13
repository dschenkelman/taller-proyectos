package socialtoilet.android.utils;

public class Settings
{

	private static Settings instance;
	
	private String user;
	private String password;
	private int initialRadiusInMeters;
	
	private Settings()
	{
		user = "mservetto";
		password = "password";
		initialRadiusInMeters = 10000;
	}
	
	public static Settings getInstance()
	{
		if( null == instance )
		{
			instance = new Settings();
		}
		return instance;
	}
	
	public String getUser()
	{
		return user;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public int getInitialRadiusInMeters()
	{
		return initialRadiusInMeters;
	}
}
