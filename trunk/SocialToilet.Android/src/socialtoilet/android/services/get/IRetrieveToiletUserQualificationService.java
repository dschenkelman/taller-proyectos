package socialtoilet.android.services.get;

public interface IRetrieveToiletUserQualificationService {

	void retrieveToiletUserCalification(
			IRetrieveToiletUserQualificationServiceDelegate delegate, String toiletId);
}
