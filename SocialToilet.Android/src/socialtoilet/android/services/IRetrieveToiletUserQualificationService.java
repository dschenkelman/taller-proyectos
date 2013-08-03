package socialtoilet.android.services;

public interface IRetrieveToiletUserQualificationService {

	void retrieveToiletUserCalification(
			IRetrieveToiletUserQualificationServiceDelegate delegate, String toiletId);
}
