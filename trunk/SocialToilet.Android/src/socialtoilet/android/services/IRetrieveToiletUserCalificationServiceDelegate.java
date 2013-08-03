package socialtoilet.android.services;

public interface IRetrieveToiletUserCalificationServiceDelegate
{
	void retrieveToiletUserCalificationServiceFinish(
			IRetrieveToiletUserCalificationService service, int calification);
	void retrieveToiletUserCalificationServiceFinishWithError(
			IRetrieveToiletUserCalificationService service, int errorCode);
}
