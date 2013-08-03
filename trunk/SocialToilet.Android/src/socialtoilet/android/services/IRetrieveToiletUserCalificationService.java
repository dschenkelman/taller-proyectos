package socialtoilet.android.services;

public interface IRetrieveToiletUserCalificationService {

	void retrieveToiletUserCalification(
			IRetrieveToiletUserCalificationServiceDelegate delegate, String toiletId);
}
