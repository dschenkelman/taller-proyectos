package socialtoilet.android.utils;

import java.util.UUID;

import android.content.SharedPreferences;

import socialtoilet.android.model.LoginUser;

public class Settings
{

    public static final String kAccountUser = "kAccountUser";
    public static final String kAccountPassword = "kAccountPassword";
    public static final String kAccountUserId = "kAccountUserId";
    public static final String kAccountIsSessionOn = "kAccountIsSessionOn";
    
    private static Settings instance;
	private SharedPreferences settings;
	
	private boolean sessionOn;
	private LoginUser user;
	private int distanceForNearToiletsInMeters;
	private boolean servicesDebugMode;
	
	private Settings(SharedPreferences settings)
	{
		this.settings = settings;
		sessionOn = false;
		distanceForNearToiletsInMeters = 200;
		servicesDebugMode = false;
	}
	
	public static void initialize(SharedPreferences settings)
	{
		instance = new Settings(settings);
	}
	
	public static Settings getInstance()
	{
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
		return distanceForNearToiletsInMeters;
	}

	public void setDistanceForNearToiletsInMeters(int meters) {
		this.distanceForNearToiletsInMeters = meters;
	}

	public boolean isServicesDebugMode()
	{
		return servicesDebugMode;
	}
	
	public void setServicesDebugMode(boolean debugMode) {
		this.servicesDebugMode = debugMode;
	}

	
	public void retrieveUser()
	{
		sessionOn = settings.getBoolean(kAccountIsSessionOn, false);
		if(sessionOn)
		{
			String userS, passwordS, userIdS;
			userS = settings.getString(kAccountUser, "");
			passwordS = settings.getString(kAccountPassword, "");
			userIdS = settings.getString(kAccountUserId, "");
			
			if(0 == userS.length() || 0 == passwordS.length() || 0 == userIdS.length())
			{
				sessionOn = false;
			}
			else
			{
				this.user = new LoginUser(userS, passwordS);
				this.user.setUserId(UUID.fromString(userIdS));
			}
		}
	}

	public void setUser(LoginUser user)
	{
		sessionOn = true;
		this.user = user;
		
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(kAccountUser, user.getUser());
		editor.putString(kAccountPassword, user.getPassword());
		editor.putString(kAccountUserId, user.getUserId().toString());
		editor.putBoolean(kAccountIsSessionOn, true);
		editor.commit();
	}

	public void setAuthUser(LoginUser user)
	{
		this.user = user;
	}
	
	public void logoutUser()
	{
		sessionOn = false;
		this.user = null;
		
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(kAccountUser, "");
		editor.putString(kAccountPassword, "");
		editor.putString(kAccountUserId, "");
		editor.putBoolean(kAccountIsSessionOn, false);
		editor.commit();
	}
}
