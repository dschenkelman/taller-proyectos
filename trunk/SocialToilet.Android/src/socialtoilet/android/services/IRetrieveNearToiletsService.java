package socialtoilet.android.services;

public interface IRetrieveNearToiletsService
{
	void retrieveNearToilets(double latitude, double longitude, IRetrieveNearToiletsServiceDelegate delegate);
}
