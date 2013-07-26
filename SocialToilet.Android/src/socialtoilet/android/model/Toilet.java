package socialtoilet.android.model;

import java.util.UUID;

public class Toilet implements IToilet
{
	private UUID id;
    private String address;
    private String description;
    private Location location;
    
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
	public int getRanking()
	{
		return 0;
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

}
