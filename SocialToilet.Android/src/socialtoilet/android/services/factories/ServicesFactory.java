package socialtoilet.android.services.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import socialtoilet.android.location.GPSTracker;
import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.Toilet;
import socialtoilet.android.services.AddToiletService;
import socialtoilet.android.services.IAddToiletService;
import socialtoilet.android.services.IAddToiletServiceDelegate;
import socialtoilet.android.services.IRetrieveNearToiletsService;
import socialtoilet.android.services.IRetrieveNearToiletsServiceDelegate;
import socialtoilet.android.services.IRetrieveToiletService;
import socialtoilet.android.services.IRetrieveToiletServiceDelegate;
import socialtoilet.android.services.RetrieveNearToiletsService;
import socialtoilet.android.services.RetrieveToiletService;
import socialtoilet.android.utils.Settings;

public class ServicesFactory
{

	private ServicesFactory() {}
	
	public static IAddToiletService createAddToiletService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IAddToiletService()
			{
				@Override
				public void addToilet(Toilet toilet, IAddToiletServiceDelegate delegate)
				{
					delegate.addToiletFinish(this);
				}
			};
		}
		return new AddToiletService();
	}
	
	public static IRetrieveNearToiletsService createRetrieveNearToiletsService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IRetrieveNearToiletsService()
			{
				@Override
				public void retrieveNearToilets(double latitude, double longitude,
						int distanceInMeters, IRetrieveNearToiletsServiceDelegate delegate)
				{
					Collection<IToilet> toilets = new ArrayList<IToilet>();
					toilets.add(new IToilet()
						{
							@Override
							public String getMapTitle() { return "La Roma"; }
							@Override
							public String getMapSnippet() { return "También venden pizza.";}
							@Override
							public double getLongitude() { return GPSTracker.getInstance().getLongitude() + 0.001; }
							@Override
							public double getLatitude() { return GPSTracker.getInstance().getLatitude() - 0.002; }
							@Override
							public int getRanking() { return 1; }
							@Override
							public UUID getID() { return UUID.randomUUID(); }
							@Override
							public String getDescription() { return "Es un buen baño"; }
							@Override
							public String getAddress() { return "Av. Yrigoyen 1565, El Talar"; }
						});
					toilets.add(new IToilet()
						{
							@Override
							public String getMapTitle() { return "Eso"; }
							@Override
							public String getMapSnippet() { return "Siempre cerrado.";}
							@Override
							public double getLongitude() { return GPSTracker.getInstance().getLongitude() + 0.003; }
							@Override
							public double getLatitude() { return GPSTracker.getInstance().getLatitude() - 0.005; }
							@Override
							public int getRanking() { return 2; }
							@Override
							public UUID getID() { return UUID.randomUUID(); }
							@Override
							public String getDescription() { return "Esta re cuidado"; }
							@Override
							public String getAddress() { return "Av. Yrigoyen 1355, El Talar"; }
						});
					
					delegate.retrieveNearToiletsFinish(this, toilets);
				}
			};
		}
		return new RetrieveNearToiletsService();
	}
	
	public static IRetrieveToiletService createRetrieveToiletService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IRetrieveToiletService()
			{
				@Override
				public void retrieveToilet(UUID toiletId,
						IRetrieveToiletServiceDelegate detailToiletActivity)
				{
					final UUID id = toiletId;
					detailToiletActivity.retrieveToiletFinish(this, new IToilet()
					{
						@Override
						public String getMapTitle() { return "La Roma"; }
						@Override
						public String getMapSnippet() { return "También venden pizza.";}
						@Override
						public double getLongitude() { return -34.465849; }
						@Override
						public double getLatitude() { return -58.645337; }
						@Override
						public int getRanking() { return 1; }
						@Override
						public UUID getID() { return id; }
						@Override
						public String getDescription() { return "Es un buen baño"; }
						@Override
						public String getAddress() { return "Av. Yrigoyen 1565, El Talar"; }
					});
				}
			};
		}
		return new RetrieveToiletService();
	}
}
