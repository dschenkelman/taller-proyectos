package socialtoilet.android.service.api;

public class APIService
{
	private static APIService instance;
	
	private String serviceURL;
	private String hostURL;
	private int port;
	
	private APIService()
	{
		hostURL = "https://192.168.1.250";
		port = 44300;
		serviceURL = hostURL + ":" + port + "/api/";
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

	public String getHost()
	{
		return hostURL;
	}

	public int getPort()
	{
		return port;
	}

	public String getCalificateToiletPostURL()
	{
		// TODO set the post url
		return "";
	}
}
