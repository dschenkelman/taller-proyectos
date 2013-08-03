package socialtoilet.android.services;

public interface IQualificateToiletServiceDelegate
{
	void calificateToiletFinish(IQualificateToiletService service);
	void calificateToiletFinishWithError(IQualificateToiletService service, int errorCode);
}
