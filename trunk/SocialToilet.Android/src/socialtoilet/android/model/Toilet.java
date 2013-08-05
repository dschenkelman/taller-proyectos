package socialtoilet.android.model;

import java.util.UUID;

public class Toilet implements IToilet
{
	private UUID id;
    private String address;
    private String description;
    private Location location;
    private float rating;
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
		return rating;
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
			rating = (rating * userCalificationsCount + calification) / (userCalificationsCount + 1);
			userCalificationsCount++;
		}
		else
		{
			rating = (rating * userCalificationsCount - userCalification) / (float)(userCalificationsCount - 1);
			rating = (rating * userCalificationsCount + calification) / (float)userCalificationsCount;
		}
		userCalification = calification;
	}

	@Override
	public int getUserCalificationsCount()
	{
		return userCalificationsCount;
	}

	@Override
	public void revertUserCalification()
	{
		if(0 != userCalification)
		{
			rating = (rating * userCalificationsCount - userCalification) / (float)(userCalificationsCount - 1);
			userCalification = 0;
			userCalificationsCount--;
		}
	}

	@Override
	public void setRating(IRating rating)
	{
		this.rating = rating.getRating();
		this.userCalificationsCount = rating.getCalificationCount();
	}
	
	@Override
	public void userCalificationRetrieved(int calification)
	{
		userCalification = calification;
	}

	public void setID(UUID id)
	{
		this.id = id;
	}

}
