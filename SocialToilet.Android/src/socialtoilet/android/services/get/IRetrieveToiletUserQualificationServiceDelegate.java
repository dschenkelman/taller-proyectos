package socialtoilet.android.services.get;

public interface IRetrieveToiletUserQualificationServiceDelegate
{
	void retrieveToiletUserQualificationServiceFinish(
			IRetrieveToiletUserQualificationService service, double calification);
	void retrieveToiletUserQualificationServiceFinishWithError(
			IRetrieveToiletUserQualificationService service, int errorCode);
}
