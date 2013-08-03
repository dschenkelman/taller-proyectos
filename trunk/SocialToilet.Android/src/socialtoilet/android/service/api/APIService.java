package socialtoilet.android.service.api;

import socialtoilet.android.utils.Settings;

public class APIService
{
	private static APIService instance;
	
	private String serviceURL;
	private String hostURL;
	private int port;
	
	private APIService()
	{
		hostURL = "https://192.168.1.35";
		port = 44300;
		//serviceURL = hostURL + ":" + port + "/api/";
		serviceURL = "https://socialtoilet.apphb.com/api/";
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

	public String getRetrieveToiletCommentsURL(String toiletId)
	{
		return serviceURL + "toilets/" + toiletId + "/comments";
	}

	public String getAddToiletCommentPostURL(String toiletId)
	{
		return serviceURL + "toilets/" + toiletId + "/comments";
	}

	public String getRetrieveToiletGaleryURL(String string)
	{
		// TODO set the get url
		return null;
	}

	public String getRetrieveToiletRatingURL(String toiletId)
	{
		return serviceURL + "toilets/" + toiletId + "/ratings/average";
	}

	public String getCalificateToiletPostURL()
	{
		// TODO set the post url
		return "";
	}

	public String getAuthPostURL()
	{
		return serviceURL + "users/auth";
	}

	public String getRetrieveToiletUserCalificationURL(String toiletId)
	{
		return serviceURL + "toilets/" + toiletId + "/ratings?userId=" +
				Settings.getInstance().getUserId().toString();
	}
}
