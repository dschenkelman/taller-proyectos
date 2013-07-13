package socialtoilet.android.services;

public interface IRetrieveNearToiletsService
{
	void retrieveNearToilets(double latitude, double longitude,
			int distanceInMeters, IRetrieveNearToiletsServiceDelegate delegate);
}
