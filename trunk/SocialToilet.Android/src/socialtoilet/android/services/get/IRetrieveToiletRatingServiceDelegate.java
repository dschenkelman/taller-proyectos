package socialtoilet.android.services.get;

import socialtoilet.android.model.IRating;

public interface IRetrieveToiletRatingServiceDelegate
{
	void retrieveToiletRatingServiceFinish(
			IRetrieveToiletRatingService service, IRating rating);
	void retrieveToiletRatingServiceFinishWithError(
			IRetrieveToiletRatingService service, String errorCode);
}
