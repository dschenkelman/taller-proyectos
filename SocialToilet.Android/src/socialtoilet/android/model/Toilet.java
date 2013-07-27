package socialtoilet.android.model;

import java.util.UUID;

public class Toilet implements IToilet
{
	private UUID id;
    private String address;
    private String description;
    private Location location;
    private float ranking;
    private int userCalification;
    private int userCalificationsCount;
    
    
    private static class Location
    {
        public double latitude;
        public double longitude;
        
    	public Location(double latitude, double longitude)
    	{
    		this.latitude = latitude;
    		this.longitude = longitude;
    	}
    }
    
	@Override
	public double getLongitude()
	{
		return location.longitude;
	}

	@Override
	public double getLatitude()
	{
		return location.latitude;
	}

	@Override
	public String getMapTitle()
	{
		return address;
	}

	@Override
	public String getMapSnippet()
	{
		return description;
	}

	@Override
	public float getRanking()
	{
		return ranking;
	}

	@Override
	public UUID getID()
	{
		return id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getAddress() {
		return address;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public void setLocation(double latitude, double longitude)
	{
		location = new Location(latitude, longitude);
	}

	public int getUserCalification()
	{
		return userCalification;
	}

	@Override
	public void setUserCalification(int calification)
	{
		if(0 == userCalification)
		{
			ranking = (ranking * userCalificationsCount + calification) / (userCalificationsCount + 1);
			userCalificationsCount++;
		}
		else
		{
			ranking = (ranking * userCalificationsCount - userCalification) / (float)(userCalificationsCount - 1);
			ranking = (ranking * userCalificationsCount + calification) / (float)userCalificationsCount;
		}
		userCalification = calification;
	}

	@Override
	public int getUserCalificationsCount()
	{
		return userCalificationsCount;
	}
}
