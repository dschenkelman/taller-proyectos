package socialtoilet.android.services.post;

public interface IQualificateToiletServiceDelegate
{
	void qualificateToiletFinish(IQualificateToiletService service);
	void qualificateToiletFinishWithError(IQualificateToiletService service, int errorCode);
}
