package socialtoilet.android.services.get;

public interface IRetrieveToiletRatingService
{
	void retrieveToiletRating(String string, 
			IRetrieveToiletRatingServiceDelegate delegate);
}
