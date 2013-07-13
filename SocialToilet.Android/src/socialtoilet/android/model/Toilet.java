package socialtoilet.android.model;

import java.util.UUID;

public class Toilet implements IToilet
{
    private UUID id;
    private String address;
    private String description;
    private Location location;
    
    public static class Location
    {
        public double latitude;
        public double longitude;
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

}
