package socialtoilet.android.services.post;

public interface IQualificateToiletServiceDelegate
{
	void calificateToiletFinish(IQualificateToiletService service);
	void calificateToiletFinishWithError(IQualificateToiletService service, int errorCode);
}
