package socialtoilet.android.services.get;

public interface IRetrieveNearToiletsService
{
	void retrieveNearToilets(double latitude, double longitude,
			int distanceInMeters, IRetrieveNearToiletsServiceDelegate delegate);
}
