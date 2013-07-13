package socialtoilet.android.service.api;

public class APIService
{
	private static APIService instance;
	
	private String serviceURL;
	
	private APIService()
	{
		serviceURL = "http://Damian-PC:8080/api/";
	}
	
	public static APIService getInstance()
	{
		if(null == instance)
		{
			instance = new APIService();
		}
		return instance;
	}
	
	public String getServiceURL()
	{
		return serviceURL;
	}
	
	public String getAddToiletPostURL()
	{
		return serviceURL + "toilets/";
	}

	public String getRetrieveNearToilets(double latitude, double longitude,
			int distanceInMeters)
	{
		return serviceURL + 
				"toilets/near?lat="+latitude+
				"&long="+longitude+
				"&radiusInMeters="+distanceInMeters;
	}

	public String getRetrieveToiletURL(String id)
	{
		return serviceURL + "toilets/" + id;
	}
}
