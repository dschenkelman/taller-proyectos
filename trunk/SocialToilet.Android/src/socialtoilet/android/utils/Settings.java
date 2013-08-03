package socialtoilet.android.utils;

import java.util.UUID;

import socialtoilet.android.model.LoginUser;

public class Settings
{

	private static Settings instance;
	
	private boolean sessionOn;
	private LoginUser user;
	private int initialRadiusInMeters;
	private boolean servicesDebugMode;
	
	private Settings()
	{
		sessionOn = false;
		initialRadiusInMeters = 10000;
		servicesDebugMode = false;
	}
	
	public static Settings getInstance()
	{
		if( null == instance )
		{
			instance = new Settings();
		}
		return instance;
	}
	
	public boolean isSessionOn()
	{
		return sessionOn;
	}

	public UUID getUserId()
	{
		return user.getUserId();
	}
	
	public String getUser()
	{
		return user.getUser();
	}
	
	public String getPassword()
	{
		return user.getPassword();
	}
	
	public int getInitialRadiusInMeters()
	{
		return initialRadiusInMeters;
	}

	public boolean isServicesDebugMode()
	{
		return servicesDebugMode;
	}
	
	public void retrieveUser()
	{
		// TODO encontrar una manera para guardar el usuario y contraseña y recuperarlo
		sessionOn = false;
	}

	public void setUser(LoginUser user)
	{
		sessionOn = true;
		this.user = user;
		// TODO dave user in disk
	}

	public void setAuthUser(LoginUser user)
	{
		this.user = user;
	}
}
