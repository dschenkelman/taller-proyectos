package socialtoilet.android.services;

public interface IRetrieveToiletRatingService
{
	void retrieveToiletRating(String string, 
			IRetrieveToiletRatingServiceDelegate delegate);
}
